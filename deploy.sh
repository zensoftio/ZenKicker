#!/bin/bash

# ENVIRONMENT VARIABLES
POSTGRES_HOST=postgres
POSTGRES_DB=kicker
POSTGRES_USER=postgres
POSTGRES_PASSWORD=123


# CLEAN ENVIRONMENT
sh docker/clean.sh

# BUILD APP
./gradlew clean assemble

# CREATE IMAGE APP
docker build -t kicker:latest -f docker/Dockerfile .

# CREATE NETWORK
docker network create kicker-net

# RUN POSTGRES
docker run \
    -d \
    --name postgres \
    --restart=always \
    -e POSTGRES_DB=${POSTGRES_DB} \
    -e POSTGRES_USER=${POSTGRES_USER} \
    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    -p 5440:5432 \
    postgres

# CREATE ALIAS FOR HOSTNAME
docker network connect --alias ${POSTGRES_HOST} kicker-net postgres

# RUN APP
docker run \
    -i \
    --rm \
    --name kicker \
    --network kicker-net \
    -e POSTGRES_HOST=${POSTGRES_HOST} \
    -e POSTGRES_DB=${POSTGRES_DB} \
    -e POSTGRES_USER=${POSTGRES_USER} \
    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    -p 8080:8080 \
    kicker:latest