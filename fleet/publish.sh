#!/bin/sh
./gradlew clean bootJar
docker rmi denisolek/rentar-fleet:1.0.6
docker build -t denisolek/rentar-fleet:1.0.6 .
docker push denisolek/rentar-fleet:1.0.6