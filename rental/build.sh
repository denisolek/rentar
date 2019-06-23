#!/bin/sh
./gradlew bootJar
docker rmi denisolek/rentar-rental:1.0.1
docker build -t denisolek/rentar-rental:1.0.1 .