#!/bin/bash

# CLEAN ENVIRONMENT
docker rm -vf kicker
docker rm -vf postgres
docker rmi -f kicker
docker network rm kicker-net