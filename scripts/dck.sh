#!/bin/sh

cmd=docker-compose

if ! command -v $cmd &> /dev/null
then
    cmd='docker compose'
fi

docker container rm $(docker ps --filter status=exited -q) 2> /dev/null

if [ "$1" == 'clean' ]
then
  docker image rm $(docker image ls -q)
fi

# basically you need only these 3 lines
$cmd rm -f 2> /dev/null
$cmd build --no-cache
$cmd up
