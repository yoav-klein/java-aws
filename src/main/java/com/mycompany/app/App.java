package com.mycompany.app;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class App 
{

    public static void
        getObjectBytes (S3Client s3, String bucketName, String keyName, String path) {

        try {
            GetObjectRequest objectRequest = GetObjectRequest
                .builder()
                .key(keyName)
                .bucket(bucketName)
                .build();
            
            
            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();

            // Write the data to a local file.
            File myFile = new File(path);
            OutputStream os = new FileOutputStream(myFile);
            os.write(data);
            System.out.println("Successfully obtained bytes from an S3 object");
            os.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }


    public static void main(String[] args)
    {   
        String bucket = System.getenv("S3_BUCKET");
        String key = System.getenv("S3_KEY");

        if((bucket == null) || (key == null)) {
            System.out.println("Define S3_BUCKET and S3_KEY");
            System.exit(1);
        }

        String region = System.getenv("AWS_REGION") == null ? "us-east-1" : System.getenv("AWS_REGION");
        S3Client client = S3Client.builder()
                       .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                       .region(Region.of(region))
                       //.httpClientBuilder(UrlConnectionHttpClient.builder())
                       .build();
        
        getObjectBytes(client, bucket, key, "some-file.txt");

        System.out.println("Done!");
    }
}
