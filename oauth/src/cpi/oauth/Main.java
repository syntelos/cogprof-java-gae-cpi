package cpi.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;

public class Main {

    private final static java.io.Console Console = System.console();


    public static void main(String[] argv){

        final boolean debug = (0 < argv.length && argv[0].endsWith("debug"));

        Console.printf("Enter API Consumer Key> ");
        final String consumerKey = Console.readLine();

        if (null == consumerKey)
            System.exit(1);
        else {

            Console.printf("Enter API Consumer Secret> ");
            final String consumerSecret = Console.readLine();

            if (null == consumerSecret)
                System.exit(1);
            else {

                OAuthService service;
                {
                    ServiceBuilder builder = new ServiceBuilder();
                    builder.provider(CpiApi.class)
                        .apiKey(consumerKey)
                        .apiSecret(consumerSecret)
                        .signatureTypeQuery();

                    if (debug)
                        builder.debugStream(System.err);

                    service = builder.build();
                }

                Token requestToken = service.getRequestToken();

                Console.printf("Authenticate in browser: %s%n",service.getAuthorizationUrl(requestToken));

                Console.printf("Enter authentication verification code> ");

                Verifier verifier = new Verifier(Console.readLine());
            
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
        }
    }
}
