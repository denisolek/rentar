#!/bin/sh
./gradlew clean bootJar
docker rmi denisolek/rentar-fleet:1.0.5
docker build -t denisolek/rentar-fleet:1.0.5 .
docker push denisolek/rentar-fleet:1.0.5