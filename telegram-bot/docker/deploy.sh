#!/bin/bash

##################################
##### ENVIRONMENT VARIABLES ######
##################################
APP_IMAGE_NAME=telegram-bot
APP_SERVICE_NAME=telegram-bot


##################################
####### CLEAN ENVIRONMENT ########
##################################
docker rm -vf ${APP_SERVICE_NAME}
docker rmi -f ${APP_IMAGE_NAME}

# IF -c THAN EXIT SCRIPT
if [ "$1" = "-c" ];
then
    exit 1
fi

##################################
############ CREATE IMAGE ########
##################################
docker load -i telegram-bot.file

##################################
############ RUN APP #############
##################################
docker run \
    -i \
    --name ${APP_SERVICE_NAME} \
    -p 8080:8080 \
    ${APP_IMAGE_NAME}
