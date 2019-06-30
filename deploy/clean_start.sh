#!/bin/bash
microk8s.kubectl delete -f kafka-cluster/01-zookeeper/
microk8s.kubectl delete -f kafka-cluster/02-kafka/
microk8s.kubectl delete -f kafka-cluster/03-yahoo-kafka-manager/
microk8s.kubectl delete -f rentar-deployment/
echo 'waiting 15 seconds to start services again'
sleep 15
microk8s.kubectl apply -f kafka-cluster/01-zookeeper/
microk8s.kubectl apply -f kafka-cluster/02-kafka/
microk8s.kubectl apply -f kafka-cluster/03-yahoo-kafka-manager/
microk8s.kubectl apply -f rentar-deployment/

