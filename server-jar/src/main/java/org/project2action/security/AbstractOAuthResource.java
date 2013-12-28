package org.project2action.security;

import org.project2action.config.Authorization;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;

public abstract class AbstractOAuthResource {

    protected final Authorization config;

    public AbstractOAuthResource(Authorization config) {
        this.config = config;
    }

    protected NewCookie createCookieWithSecurityToken(String token) {
        return new NewCookie(config.getSecurityTokenName(), token,
                config.getSecureUrl(), null, null, config.getSecurityTokenExpirationTime(), false);
    }

    protected NewCookie cookieForEmptySecurityToken() {
        return new NewCookie(config.getSecurityTokenName(), "deleted",
                config.getSecureUrl(), null, null, 0, false);
    }

    @POST
    @Path("logout")
    public Response logout(){
        return Response.seeOther(URI.create(config.getSignInPage()))
                .cookie(cookieForEmptySecurityToken())
                .build();
    }
}
