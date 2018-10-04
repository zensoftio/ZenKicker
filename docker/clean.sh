#!/bin/bash

if [ "$#" -ne 4 ];
then
    echo 'Enter parameter: app image name, app service name, postgres service name, network services name'
    exit 1
fi

# ENVIRONMENT VARIABLES
APP_IMAGE_NAME=$1
APP_SERVICE_NAME=$2
POSTGRES_SERVICE_NAME=$3
NETWORK_SERVICES_NAME=$4

# CLEAN ENVIRONMENT
docker rm -vf ${APP_SERVICE_NAME}
docker rm -vf ${POSTGRES_SERVICE_NAME}
docker rmi -f ${APP_IMAGE_NAME}
docker network rm ${NETWORK_SERVICES_NAME}