#!/bin/sh
./gradlew bootJar
docker rmi denisolek/rentar-rental:1.0.3
docker build -t denisolek/rentar-rental:1.0.3 .