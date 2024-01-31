# SES
---

This example use `SesClient` to send an Email using IRSA.

## How to Use

First, build the application:
```
$ gradle dockerBuildImage
```

This will create a docker image with the application. Then tag the image and push it:
```
$ docker tag ses/app:1.0 yoavklein3/java-aws-ses:latest
$ docker push yoavklein3/java-aws-ses:latest
```

Then, create the Terraform infrastructure. 
