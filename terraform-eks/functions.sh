#!/bin/bash


setup() {
    aws eks update-kubeconfig --region us-east-1 --name my-cluster

    bucket_name=$(terraform output -raw s3_bucket_name)
    account_id=$(aws sts get-caller-identity --query Account --output text)

    aws s3 cp content.txt s3://${bucket_name}/content.txt

    sed "s/<account_id>/$account_id/" pod.yaml.template | sed "s/<s3_bucket>/$bucket_name/" \
       | sed "s/<s3_key>/content.txt/" > pod.yaml
    kubectl apply -f pod.yaml
}

test() {
    kubectl logs aws-java -c reader
}
