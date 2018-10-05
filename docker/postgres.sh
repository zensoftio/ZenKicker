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

VC_POSTGRES_SERVICE_NAME=vc_${POSTGRES_SERVICE_NAME}
VC_POSTGRES_SERVICE_EXIST=`docker ps -aq -f name=${VC_POSTGRES_SERVICE_NAME}`
VOLUME_FOLDER=/var/lib/postgresql/data/pgdata


# CREATE VOLUME CONTAINER FOR DATABASE
if [ "${VC_POSTGRES_SERVICE_EXIST}" = "" ];
then
    docker create \
        --name ${VC_POSTGRES_SERVICE_NAME} \
        -v ${VOLUME_FOLDER} \
        busybox
fi

# RUN POSTGRES
docker run \
    -d \
    --name ${POSTGRES_SERVICE_NAME} \
    --restart=always \
    --volumes-from ${VC_POSTGRES_SERVICE_NAME} \
    -e PGDATA=${VOLUME_FOLDER} \
    -e POSTGRES_DB=${POSTGRES_DB} \
    -e POSTGRES_USER=${POSTGRES_USER} \
    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    -p ${OUTER_PORT}:5432 \
    postgres

