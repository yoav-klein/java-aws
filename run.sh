#!/bin/bash

docker run -d -e AWS_ACCESS_KEY_ID=AKIASGYP3JJPXDELSBAF \
    -e AWS_SECRET_ACCESS_KEY=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 \
    -e S3_BUCKET=yoav-s3-test \
    -e S3_KEY=my-file \
    aws-java:latest
