#!/bin/sh
npm install
hey build
docker rmi denisolek/rentar-web:1.0.0
docker build -t denisolek/rentar-web:1.0.0 .
docker push denisolek/rentar-web:1.0.0
