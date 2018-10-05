#!/bin/bash

if [ "$#" -lt 1 ];
then
    echo 'Enter parameter: volume images path'
    echo 'You also can enter optional parameters: nginx name, nginx.conf path'
    exit 1
fi

VOLUME_IMAGES_NAME=$1
NGINX_SERVICE_NAME=${2:-nginx}
NGINX_CONF_FILE_PATH=${3:-nginx.conf}

# CREATE NGINX CONTAINER
docker create \
    --name ${NGINX_SERVICE_NAME} \
    --network host \
    --restart=always \
    -v ${VOLUME_IMAGES_NAME}:/www/data/images:ro \
    nginx:latest

# COPY CONFIG FOR NGINX TO CONTAINER
docker cp ${NGINX_CONF_FILE_PATH} ${NGINX_SERVICE_NAME}:/etc/nginx/conf.d/

# RUN CONTAINER
docker start nginx