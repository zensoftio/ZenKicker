#!/bin/bash

# ENVIRONMENT VARIABLES
APP_IMAGE_NAME=kicker
APP_SERVICE_NAME=kicker
NETWORK_SERVICES_NAME=kicker-net

POSTGRES_SERVICE_NAME=postgres_kicker
POSTGRES_HOST=postgres
POSTGRES_DB=kicker
POSTGRES_USER=postgres
POSTGRES_PASSWORD=123
OUTER_PORT=5440

STATIC_FOLDERS_IMAGES=images
VOLUME_IMAGES_NAME=images_volume

NGINX_SERVICE_NAME=nginx
NGINX_CONF_FILE_PATH=docker/nginx/nginx.conf


# CLEAN ENVIRONMENT
sh docker/clean.sh ${APP_IMAGE_NAME} \
                    ${APP_SERVICE_NAME} \
                    ${POSTGRES_SERVICE_NAME} \
                    ${NETWORK_SERVICES_NAME} \
                    ${NGINX_SERVICE_NAME} \
                    ${VOLUME_IMAGES_NAME}

if [ "$1" = "clean" ];
then
    exit 1
fi

# BUILD APP
./gradlew clean assemble

# CREATE IMAGE APP
docker build -t ${APP_IMAGE_NAME}:latest -f docker/Dockerfile .

# CREATE NETWORK
docker network create ${NETWORK_SERVICES_NAME}

# RUN POSTGRES
sh docker/postgres.sh ${POSTGRES_SERVICE_NAME} \
                        ${POSTGRES_DB} \
                        ${POSTGRES_USER} \
                        ${POSTGRES_PASSWORD} \
                        ${OUTER_PORT}

# CREATE ALIAS FOR HOSTNAME
docker network connect --alias ${POSTGRES_HOST} \
                                ${NETWORK_SERVICES_NAME} \
                                ${POSTGRES_SERVICE_NAME}

# CREATE VOLUME BETWEEN CONTAINERS
docker volume create --name ${VOLUME_IMAGES_NAME}

# CREATE APP
docker create \
    --name ${APP_SERVICE_NAME} \
    --network ${NETWORK_SERVICES_NAME} \
    -v ${VOLUME_IMAGES_NAME}:/root/${STATIC_FOLDERS_IMAGES} \
    -e POSTGRES_HOST=${POSTGRES_HOST} \
    -e POSTGRES_DB=${POSTGRES_DB} \
    -e POSTGRES_USER=${POSTGRES_USER} \
    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    -e STATIC_FOLDERS_IMAGES=${STATIC_FOLDERS_IMAGES} \
    -p 8080:8080 \
    ${APP_IMAGE_NAME}

# RUN NGINX
sh docker/nginx/nginx.sh ${VOLUME_IMAGES_NAME} \
                            ${NGINX_SERVICE_NAME} \
                            ${NGINX_CONF_FILE_PATH}

# RUN APP
docker start -i ${APP_SERVICE_NAME}