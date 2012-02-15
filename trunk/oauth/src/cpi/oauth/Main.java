package cpi.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;

public class Main {

    static java.io.Console Console = System.console();

    public static void main(String[] argv){
        try {
            Console.printf("Enter API Consumer Key> ");
            final char[] apiKey = Console.readPassword();

            if (null == apiKey)
                System.exit(1);

            Console.printf("Enter API Consumer Secret> ");
            final char[] apiSec = Console.readPassword();

            if (null == apiSec)
                System.exit(1);

            OAuthService service = new ServiceBuilder()
                .provider(CpiApi.class)
                .apiKey(apiKey)
                .apiSecret(apiSec)
                .build();

            Token requestToken = service.getRequestToken();

            Console.printf("Authenticate in browser: %s%n",service.getAuthorizationUrl(requestToken));

            Console.printf("Enter authentication verification code> ");

            Verifier verifier = new Verifier(Console.readPassword());
            
            Token accessToken = service.getAccessToken(requestToken,verifier);

            Console.printf("Enter CPI Group Identifier> ");

            final String groupId = Console.readLine();

            if (null == groupId)
                System.exit(1);

            final String requestUrl = String.format("http://cpi.cognitiveprofile.com/groups/%s/data.json",groupId);

            OAuthRequest request = new OAuthRequest(Verb.GET,requestUrl);

            service.signRequest(accessToken,request);

            Response response = request.send();

            if (response.isSuccessful()){
                Console.printf("OK%n%s%n",response.getBody());
                System.exit(0);
            }
            else {
                Console.printf("Error%n%d %s%n",response.getCode(),response.getStatus());
                System.exit(0);
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
            System.exit(1);
        }
    }
}
