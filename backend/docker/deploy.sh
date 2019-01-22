#!/bin/bash

##################################
##### ENVIRONMENT VARIABLES ######
##################################
APP_IMAGE_NAME=kicker
APP_SERVICE_NAME=kicker

NETWORK_SERVICES_NAME=kicker-net

POSTGRES_SERVICE_NAME=postgres_kicker
POSTGRES_HOST=postgres
POSTGRES_DB=kicker
POSTGRES_USER=postgres
POSTGRES_PASSWORD=123
OUTER_PORT=5440
POSTGRES_SERVICE_EXIST=`docker ps -aq -f name=${POSTGRES_SERVICE_NAME}`

SERVER_HOST=http://new-kicker.zensoft.by

##################################
####### CLEAN ENVIRONMENT ########
##################################
docker rm -vf ${APP_SERVICE_NAME}
docker rmi -f ${APP_IMAGE_NAME}

# IF -r THAN EXIT SCRIPT
if [ "$1" = "-r" ];
then
    docker rm -vf ${POSTGRES_SERVICE_NAME}
    docker network rm ${NETWORK_SERVICES_NAME}
    docker rm -vf vc_${POSTGRES_SERVICE_NAME}
    exit 1
fi

if [ "${POSTGRES_SERVICE_EXIST}" = "" ];
then
    ##################################
    ######### CREATE NETWORK #########
    ##################################
    docker network create ${NETWORK_SERVICES_NAME}

    ##################################
    ######### RUN POSTGRES ###########
    ##################################
    docker run \
        -d \
        --name ${POSTGRES_SERVICE_NAME} \
        --restart=always \
        -e POSTGRES_DB=${POSTGRES_DB} \
        -e POSTGRES_USER=${POSTGRES_USER} \
        -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
        -p ${OUTER_PORT}:5432 \
        postgres:9.6.11

    ##################################
    ### CREATE ALIAS FOR HOSTNAME ####
    ##################################
    docker network connect --alias ${POSTGRES_HOST} \
                                    ${NETWORK_SERVICES_NAME} \
                                    ${POSTGRES_SERVICE_NAME}
fi

##################################
############ CREATE IMAGE ########
##################################
docker load -i kicker.file

##################################
############ RUN APP #############
##################################
docker run \
    -i \
    --name ${APP_SERVICE_NAME} \
    --network ${NETWORK_SERVICES_NAME} \
    -v /home/${USER}/data:/root/data \
    -e POSTGRES_HOST=${POSTGRES_HOST} \
    -e POSTGRES_DB=${POSTGRES_DB} \
    -e POSTGRES_USER=${POSTGRES_USER} \
    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    -e SERVER_HOST=${SERVER_HOST} \
    -p 8080:8080 \
    ${APP_IMAGE_NAME}
