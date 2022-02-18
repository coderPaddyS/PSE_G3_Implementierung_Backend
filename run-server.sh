#!/bin/bash

git pull
cd backend && ./mvnw package -DskipTests
docker build -t kit-finder .
cd /opt/docker/kit-finder || exit
docker-compose up -d