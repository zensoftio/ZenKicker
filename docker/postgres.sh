#!/bin/bash

if [ "$#" -lt 1 ];
then
    echo 'Enter mandatory parameter: postgres service name'
    echo 'You also can enter optional parameters: db name, db user, db password, outer port'
    exit 1
fi

# ENVIRONMENT VARIABLES
POSTGRES_SERVICE_NAME=$1
POSTGRES_DB=${2:-kicker}
POSTGRES_USER=${3:-postgres}
POSTGRES_PASSWORD=${4:-123}
OUTER_PORT=${5:-5432}

# RUN POSTGRES
docker run \
    -d \
    --name ${POSTGRES_SERVICE_NAME} \
    --restart=always \
    -e POSTGRES_DB=${POSTGRES_DB} \
    -e POSTGRES_USER=${POSTGRES_USER} \
    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    -p ${OUTER_PORT}:5432 \
    postgres

