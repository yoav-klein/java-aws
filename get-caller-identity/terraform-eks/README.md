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

Then, we'll run a pod in the EKS cluster with 2 containers:
* One container runs our Java application, which reads the content from the S3 bucket object, and
  outputs it to a file
* Second container just reads the file and outputs it to stdout


## Usage
---

### Run Terraform
Edit the `environment` file and source it, and run the terraform code: `terrform apply -auto-approve`


### Upload content to S3, and apply a pod
source the `functions.sh` file, and run `setup`
```
$ source functions.sh
$ setup
```

This uploads a file to the S3 bucket, and creates the pod with our Java application
with the ServiceAccount who will have permissions to the bucket.

### Test
Wait a minute for the pod to start, and then
Run
```
$ test
```

If you get the content of the file that was uploaded to S3, this is SUCCESS!
