#!/bin/bash

git pull
docker build -t kit-finder .
cd /opt/docker/kit-finder || exit
docker-compose up -d