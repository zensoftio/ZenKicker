#!/bin/bash

if [ "$#" -ne 2 ];
then
    echo 'Enter parameters: postgres service name, nginx'
    exit 1
fi

# ENVIRONMENT VARIABLES
POSTGRES_SERVICE_NAME=$1
NGINX_SERVICES_NAME=$2

# CLEAN ENVIRONMENT
docker rm -vf ${POSTGRES_SERVICE_NAME}
docker rm -vf ${NGINX_SERVICES_NAME}