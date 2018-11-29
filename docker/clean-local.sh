#!/bin/bash

# ENVIRONMENT VARIABLES
POSTGRES_SERVICE_NAME=$1
NGINX_SERVICES_NAME=$2

# CLEAN ENVIRONMENT
docker rm -vf ${POSTGRES_SERVICE_NAME}
docker rm -vf ${NGINX_SERVICES_NAME}
# Uncomment when need to delete volume for postgres
#docker rm -vf vc_${POSTGRES_SERVICE_NAME}