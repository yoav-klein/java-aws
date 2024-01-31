# Running in EKS using IRSA
---

What we want to demonstrate here is that our Java application can utilize IRSA for permissions.

IRSA - IAM Roles for Service Accounts, is a set up that allows you to annotate a Service Account
with a role name, and have that role's credentials in your pod, so that you can access AWS resources
from within your pod easily and securely.

In this directory, we have Terraform code that provisions the following infrastructure:
* VPC
* EKS cluster and nodes
* S3 Bucket
* IAM role which has access to this bucket
* IRSA related configuration, so that our pod can assume that role.



## Usage
---


### Run Terraform
Edit the `environment` file and source it, and run the terraform code: `terrform apply -auto-approve`

After creating this infrastructure, go to AWS SES in the console and create an identity (yoavklein25@gmail.com).

now source the `functions.sh` file, and run `setup`
```
$ source functions.sh
$ setup
```

This will configure kubeconfig and create the `pod.yaml` from the template.

### Test
Run the pod:
```
$ kubectl apply -f pod.yaml
```

You should receive an email, which indicates that it works.

