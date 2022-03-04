#!/bin/bash

git fetch
git pull
if [ "$1" ]
then git checkout "$1"
else git checkout main
fi
cd backend || exit && /mvnw package -DskipTests
cd .. || exit 
docker build -t kit-finder .
cd /opt/docker/kit-finder || exit
docker-compose up -d