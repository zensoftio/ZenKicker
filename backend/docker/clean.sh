#!/bin/bash

# ENVIRONMENT VARIABLES
APP_IMAGE_NAME=$1
APP_SERVICE_NAME=$2
POSTGRES_SERVICE_NAME=$3
NETWORK_SERVICES_NAME=$4
NGINX_SERVICES_NAME=$5
VOLUME_IMAGES_NAME=$6

# CLEAN ENVIRONMENT
docker rm -vf ${APP_SERVICE_NAME}
docker rm -vf ${POSTGRES_SERVICE_NAME}
docker rmi -f ${APP_IMAGE_NAME}
docker network rm ${NETWORK_SERVICES_NAME}
docker rm -vf ${NGINX_SERVICES_NAME}
# Uncomment when need to delete icons
#docker volume rm -f ${VOLUME_IMAGES_NAME}
# Uncomment when need to delete volume for postgres
#docker rm -vf vc_${POSTGRES_SERVICE_NAME}