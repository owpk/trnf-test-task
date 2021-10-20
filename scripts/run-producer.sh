#!/bin/bash
./gradlew :message-producer:build
docker compose build producer --no-cache
docker compose up producer
