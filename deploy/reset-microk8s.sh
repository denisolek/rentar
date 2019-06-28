#!/bin/bash
rm ngrok.log
kill $(ps aux | grep 'ngrok' | awk '{print $2}')
microk8s.reset
sleep 5
microk8s.enable dns ingress
microk8s.kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/master/aio/deploy/recommended/kubernetes-dashboard.yaml
microk8s.kubectl apply -f monitoring/prometheus/
microk8s.kubectl apply -f monitoring/grafana/
microk8s.kubectl apply -f kafka-cluster/01-zookeeper/
microk8s.kubectl apply -f kafka-cluster/02-kafka/
microk8s.kubectl apply -f kafka-cluster/03-yahoo-kafka-manager/
microk8s.kubectl apply -f rentar-deployment/
token=$(microk8s.kubectl -n kube-system get secret | grep default-token | cut -d " " -f1)
microk8s.kubectl -n kube-system describe secret $token
./ngrok http https://$(microk8s.kubectl get all --all-namespaces |grep kubernetes-dashboard | grep ClusterIP | awk '{print $4}'):443 --log=stdout > ngrok.log &
sleep 5
cat ngrok.log | grep 'url=https://' | awk '{print $8}'

