import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityRequest;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityResult;

public class HelloWorld {
    public static void main(String[] args) {
        // Create STS client
        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .build();

        // Get caller identity
        GetCallerIdentityRequest request = new GetCallerIdentityRequest();
        GetCallerIdentityResult result = stsClient.getCallerIdentity(request);

        // Print caller identity
        while(true) {
            System.out.println("User ID: " + result.getUserId());
            System.out.println("Account ID: " + result.getAccount());
            System.out.println("Arn: " + result.getArn());

            Thread.sleep(10000);
        }
    }
}
