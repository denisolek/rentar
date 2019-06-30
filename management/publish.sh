#!/bin/sh
./gradlew bootJar
docker rmi denisolek/rentar-management:1.0.3
docker build -t denisolek/rentar-management:1.0.3 .
docker push denisolek/rentar-management:1.0.3