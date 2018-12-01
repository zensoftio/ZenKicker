#!/bin/bash

# ENVIRONMENT VARIABLES
POSTGRES_SERVICE_NAME=local_postgres_kicker
POSTGRES_HOST=postgres
POSTGRES_DB=kicker
POSTGRES_USER=postgres
POSTGRES_PASSWORD=123
OUTER_PORT=5441
VC_POSTGRES_SERVICE_NAME=vc_${POSTGRES_SERVICE_NAME}
VC_POSTGRES_SERVICE_EXIST=`docker ps -aq -f name=${VC_POSTGRES_SERVICE_NAME}`
VOLUME_FOLDER=/var/lib/postgresql/data/pgdata

NGINX_SERVICE_NAME=nginx

DIRECTORY=`dirname $0`

##################################
####### CLEAN ENVIRONMENT ########
##################################
docker rm -vf ${POSTGRES_SERVICE_NAME}
docker rm -vf ${NGINX_SERVICE_NAME}
# To uncomment if no needed to remove vc
#docker rm -vf vc_${POSTGRES_SERVICE_NAME}
# To uncomment if no needed to remove folder images
#rm -r ${DIRECTORY}/../../images

# IF -c THAN EXIT SCRIPT
if [ "$1" = "-c" ];
then
    exit 1
fi

##################################
# CREATE VOLUME CONTAINER FOR DATABASE
##################################
if [ "${VC_POSTGRES_SERVICE_EXIST}" = "" ];
then
    docker create \
        --name ${VC_POSTGRES_SERVICE_NAME} \
        -v ${VOLUME_FOLDER} \
        busybox
fi

##################################
######### RUN POSTGRES ###########
##################################
docker run \
    -d \
    --name ${POSTGRES_SERVICE_NAME} \
    --restart=always \
    --volumes-from ${VC_POSTGRES_SERVICE_NAME} \
    -e PGDATA=${VOLUME_FOLDER} \
    -e POSTGRES_DB=${POSTGRES_DB} \
    -e POSTGRES_USER=${POSTGRES_USER} \
    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    -p ${OUTER_PORT}:5432 \
    postgres:9.6.11

##################################
# CREATE DIRECTORY FOR STATIC IMAGES
##################################
RELATIVE_IMAGE_PATH=${DIRECTORY}/../../images
if [ ! -d ${RELATIVE_IMAGE_PATH} ]; then
    mkdir ${RELATIVE_IMAGE_PATH}
fi
ABSOLUTE_IMAGE_PATH="$( cd ${RELATIVE_IMAGE_PATH} ; pwd -P )"

##################################
########### RUN NGINX ############
##################################
docker create \
    --name ${NGINX_SERVICE_NAME} \
    --network host \
    --restart=always \
    -v ${ABSOLUTE_IMAGE_PATH}:/www/data/images:ro \
    nginx:1.15.7

# COPY CONFIG FOR NGINX TO CONTAINER
docker cp ${DIRECTORY}/nginx.conf ${NGINX_SERVICE_NAME}:/etc/nginx/conf.d/

docker start ${NGINX_SERVICE_NAME}