#!/bin/bash

# ENVIRONMENT VARIABLES
POSTGRES_SERVICE_NAME=local_postgres_kicker
POSTGRES_HOST=postgres
POSTGRES_DB=kicker
POSTGRES_USER=postgres
POSTGRES_PASSWORD=123
OUTER_PORT=5441

NGINX_SERVICE_NAME=nginx

# CLEAN ENVIRONMENT
sh backend/docker/clean-local.sh ${POSTGRES_SERVICE_NAME} \
                    ${NGINX_SERVICE_NAME}

if [ "$1" = "-c" ];
then
    exit 1
fi

# BUILD APP
./gradlew clean assemble

# RUN POSTGRES
sh backend/docker/postgres.sh ${POSTGRES_SERVICE_NAME} \
                        ${POSTGRES_DB} \
                        ${POSTGRES_USER} \
                        ${POSTGRES_PASSWORD} \
                        ${OUTER_PORT}

# CREATE DIRECTORY FOR STATIC IMAGES
mkdir images

# RUN NGINX
sh backend/docker/nginx/nginx.sh ${NGINX_SERVICE_NAME} \
                            ${PWD}/images