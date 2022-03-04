#!/bin/bash

git fetch
if [ "$1" ]
then git checkout "$1"
else git checkout main
fi
git pull
cd backend || exit && ./mvnw package -DskipTests
docker build -t kit-finder .
cd /opt/docker/kit-finder || exit
docker-compose up -d