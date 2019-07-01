#!/bin/sh
./gradlew bootJar
docker rmi denisolek/rentar-management:1.0.4
docker build -t denisolek/rentar-management:1.0.4 .
docker push denisolek/rentar-management:1.0.4