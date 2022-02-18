#!/bin/bash

git pull
docker build -t kit-finder .
docker run kit-finder