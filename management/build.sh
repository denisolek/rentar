#!/bin/sh
./gradlew bootJar
docker rmi denisolek/rentar-management:1.0.2
docker build -t denisolek/rentar-management:1.0.2 .