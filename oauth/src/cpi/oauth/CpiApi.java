package cpi.oauth;

import org.scribe.model.Token;
import org.scribe.model.Verb;

/**
 * 
 * <pre>
 * 
 *  OAuthService service = new ServiceBuilder()
 *      .provider(CpiApi.class)
 *      .apiKey(consumerKey)
 *      .apiSecret(consumerSecret)
 *      .build();
 * </pre>
 */
public final class CpiApi 
    extends org.scribe.builder.api.DefaultApi10a
{

    protected enum API {

        Appspot( "https://cognitiveprofile.appspot.com/_ah/OAuthGetAccessToken",
                 "https://cognitiveprofile.appspot.com/_ah/OAuthGetRequestToken",
                 "https://cognitiveprofile.appspot.com/_ah/OAuthAuthorizeToken?oauth_token=%s"), 

        Cogprof( "https://cpi.cognitiveprofile.com/_ah/OAuthGetAccessToken",
                 "https://cpi.cognitiveprofile.com/_ah/OAuthGetRequestToken",
                 "https://cpi.cognitiveprofile.com/_ah/OAuthAuthorizeToken?oauth_token=%s");


        protected final String access, request, auth;

        private API(String access, String request, String auth){

            this.access = access;
            this.request = request;
            this.auth = auth;
        }
    }

    private final static API api = CpiApi.API.Cogprof;


    public CpiApi(){
        super();
    }

  
    @Override
    public String getAccessTokenEndpoint()
    {
        return CpiApi.api.access;
    }

    @Override
    public String getRequestTokenEndpoint()
    {
        return CpiApi.api.request;
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
        return String.format( CpiApi.api.request, requestToken);
    }
}
