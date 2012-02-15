package cpi.oauth;

import org.scribe.model.Token;
import org.scribe.model.Verb;

/**
 * 
 * <pre>
 *  OAuthService service = new ServiceBuilder()
 *      .provider(CpiApi.class)
 *      .apiKey("Api_Consumer_Key")
 *      .apiSecret("Api_Consumer_Secret")
 *      .build();
 * </pre>
 */
public class CpiApi 
    extends org.scribe.builder.api.DefaultApi10a
{

    public CpiApi(){
        super();
    }

  
    @Override
    public String getAccessTokenEndpoint()
    {
        return "https://cpi.cognitiveprofile.com/_ah/OAuthGetAccessToken"; 
    }

    @Override
    public String getRequestTokenEndpoint()
    {
        return "https://cpi.cognitiveprofile.com/_ah/OAuthGetRequestToken";
    }

    @Override
    public Verb getAccessTokenVerb()
    {
        return Verb.GET;
    }

    @Override
    public Verb getRequestTokenVerb()
    {
        return Verb.GET;
    }
  
    @Override
    public String getAuthorizationUrl(Token requestToken)
    {
        return  "https://cpi.cognitiveprofile.com/_ah/OAuthAuthorizeToken?oauth_token="+requestToken.getToken();
    }
}
