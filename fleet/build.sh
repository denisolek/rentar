#!/bin/sh
./gradlew clean bootJar
docker rmi denisolek/rentar-fleet:1.0.8
docker build -t denisolek/rentar-fleet:1.0.8 .