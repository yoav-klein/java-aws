#!/bin/bash


setup() {
   aws eks update-kubeconfig --region us-east-1 --name my-cluster
   account_id=$(aws sts get-caller-identity --query Account --output text)

    sed "s/<account_id>/$account_id/" pod.yaml.template > pod.yaml
    kubectl apply -f pod.yaml
}

