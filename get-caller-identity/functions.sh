#!/bin/bash

build_push() {
    docker build -t yoavklein3/aws-java:latest .
    docker push yoavklein3/aws-java-sts:latest
}

run_docker() {
    docker run -d -e AWS_ACCESS_KEY_ID=AKIASGYP3JJPXDELSBAF \
    -e AWS_SECRET_ACCESS_KEY=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 \
    aws-java:latest
}
