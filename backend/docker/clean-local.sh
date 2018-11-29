#!/bin/bash

# ENVIRONMENT VARIABLES
POSTGRES_SERVICE_NAME=$1
NGINX_SERVICES_NAME=$2

# CLEAN ENVIRONMENT
docker rm -vf ${POSTGRES_SERVICE_NAME}
docker rm -vf ${NGINX_SERVICES_NAME}
# To comment if no needed to remove vc
docker rm -vf vc_${POSTGRES_SERVICE_NAME}