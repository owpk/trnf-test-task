#!/usr/bin/bash
docker compose stop producer
docker compose build producer --no-cache
docker compose up producer