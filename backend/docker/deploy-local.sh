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


##################################
####### CLEAN ENVIRONMENT ########
##################################
docker rm -vf ${POSTGRES_SERVICE_NAME}
# To uncomment if no needed to remove vc
#docker rm -vf vc_${POSTGRES_SERVICE_NAME}

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