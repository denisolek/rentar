#!/bin/sh
./gradlew bootJar
docker rmi denisolek/rentar-rental:1.0.2
docker build -t denisolek/rentar-rental:1.0.2 .
docker push denisolek/rentar-rental:1.0.2