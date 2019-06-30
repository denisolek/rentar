#!/bin/sh
./gradlew clean bootJar
docker rmi denisolek/rentar-fleet:1.0.7
docker build -t denisolek/rentar-fleet:1.0.7 .
docker push denisolek/rentar-fleet:1.0.7