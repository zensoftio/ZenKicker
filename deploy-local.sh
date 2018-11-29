#!/bin/bash

# ENVIRONMENT VARIABLES
POSTGRES_SERVICE_NAME=local_postgres_kicker
POSTGRES_HOST=postgres
POSTGRES_DB=kicker
POSTGRES_USER=postgres
POSTGRES_PASSWORD=123
OUTER_PORT=5441

STATIC_FOLDERS_IMAGES=images

NGINX_SERVICE_NAME=nginx
NGINX_CONF_FILE_PATH=docker/nginx/nginx.conf


# CLEAN ENVIRONMENT
sh docker/clean-local.sh ${POSTGRES_SERVICE_NAME} \
                    ${NGINX_SERVICE_NAME}

if [ "$1" = "-c" ];
then
    exit 1
fi

# BUILD APP
./gradlew clean assemble

# RUN POSTGRES
sh docker/postgres.sh ${POSTGRES_SERVICE_NAME} \
                        ${POSTGRES_DB} \
                        ${POSTGRES_USER} \
                        ${POSTGRES_PASSWORD} \
                        ${OUTER_PORT}

# CREATE DIRECTORY FOR STATIC IMAGES
mkdir ${STATIC_FOLDERS_IMAGES}

# RUN NGINX
sh docker/nginx/nginx.sh ${NGINX_SERVICE_NAME} \
                            ${PWD}/${STATIC_FOLDERS_IMAGES} \
                            ${NGINX_CONF_FILE_PATH}