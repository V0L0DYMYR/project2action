package org.project2action.security;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.eclipse.jetty.util.ajax.JSON;
import org.project2action.config.Authorization;
import org.project2action.config.GoogleAuthorization;
import org.project2action.dao.UserDao;
import org.project2action.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

@Path("/oauth2/google")
public class GoogleOAuth2Resource extends AbstractOAuthResource {

    private Logger LOG = LoggerFactory.getLogger(GoogleOAuth2Resource.class);
    private final GoogleAuthorization googleConfig;
    private final UserDao userDao;

    public GoogleOAuth2Resource(UserDao userDao, Authorization config) {
        super(config);
        this.userDao = userDao;
        this.googleConfig = config.getGoogle();
    }

    @GET
    @UnitOfWork
    public Response googleCallback(@QueryParam("state") String state, @QueryParam("code") String code) {
        LOG.info("OAuth2 callback from Google - state:{}, code:{}", state, code);

        String token = requestForAccessToken(code);
        User user = requestForUserInfo(token);
        userDao.saveOrUpdate(user);

        return Response.seeOther(URI.create(config.getHomePage()))
                .cookie(createCookieWithSecurityToken(token))
                .build();
    }

    private User requestForUserInfo(String token) {
        ClientResponse response = getWebResource(googleConfig.getUserInfoUrl())
                .queryParam(googleConfig.getAccessTokenName(), token)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
        String userInfo = response.getEntity(String.class);
        LOG.info("User Info:" + userInfo);
        User user = parseUserInfo(userInfo);
        return new User(user, token);
    }

    private User parseUserInfo(String userInfoString) {
        Map<String, String> userMap = (Map<String, String>) JSON.parse(userInfoString);
        String googleId = userMap.get("id");
        String email = userMap.get("email");
        String locale = userMap.get("locale");
        String fullName = userMap.get("name");
        return new User(null, googleId, email, fullName, locale);
    }

    private WebResource getWebResource(String url) {
        Client client = new Client().create();
        return client.resource(url);
    }

    private String requestForAccessToken(String googleAccessCode) {
        ClientResponse clientResponse = getWebResource(googleConfig.getOauth2Url())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, getGoogleParamsForAccessToken(googleAccessCode));
        String googleResponse = clientResponse.getEntity(String.class);
        String googleAccessToken = parseAccessToken(googleResponse);
        LOG.info("Given Google access token:{}\n, from Authorization client response:{}", googleAccessToken, googleResponse);
        return googleAccessToken;
    }

    private MultivaluedMap<String, String> getGoogleParamsForAccessToken(String code) {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("code", code);
        params.add("client_id", googleConfig.getClientId());
        params.add("client_secret", googleConfig.getClientSecret());
        params.add("redirect_uri", googleConfig.getRedirectUri());
        params.add("grant_type", googleConfig.getGrantType());
        return params;
    }

    private String parseAccessToken(String response) {
        Map<String, String> responseMap = (Map<String, String>) JSON.parse(response);
        return responseMap.get(googleConfig.getAccessTokenName());
    }

}
