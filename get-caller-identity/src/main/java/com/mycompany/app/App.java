//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityRequest;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityResponse;
import software.amazon.awssdk.regions.Region;

//import com.amazonaws.services.sts.model.GetCallerIdentityResult;

public class App {
    public static void main(String[] args) {
        // Create STS client

        String region = System.getenv("AWS_REGION") == null ? "us-east-1" : System.getenv("AWS_REGION");
        StsClient client = StsClient.builder()
            .region(Region.of(region))
            .build();
        
        GetCallerIdentityResponse response = client.getCallerIdentity();
        // Get caller identity
        //GetCallerIdentityRequest request = new GetCallerIdentityRequest();
        
        //GetCallerIdentityResult result = stsClient.getCallerIdentity(request);

        // Print caller identity
        while(true) {
            try {
                System.out.println("User ID: " + response.userId());
                System.out.println("Account ID: " + response.account());
                System.out.println("Arn: " + response.arn());

                Thread.sleep(10000);

            } catch(InterruptedException e) {
                System.out.println("Interrupted..");
            }
        }
    }
}
