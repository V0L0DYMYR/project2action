package org.project2action.security;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.project2action.config.Authorization;
import org.project2action.dao.UserDao;
import org.project2action.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/oauth2/testing")
public class DummyOAuthResource extends AbstractOAuthResource {

    private final Authorization config;
    private final UserDao userDao;
    private Logger LOG = LoggerFactory.getLogger(DummyOAuthResource.class);

    public DummyOAuthResource(UserDao userDao, Authorization config) {
        super(config);
        this.config = config;
        this.userDao = userDao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public Response signIn(@FormParam("userName") String userName) {
        System.out.println("user id:" + userName);
        if (userName == null || userName.length() < 4) return Response.ok("Too short :(").build();
        User user = userDao.findBySecurityToken(userName);
        if (user == null)
            user = userDao.saveOrUpdate(new User(userName, userName, userName));

        return Response.seeOther(URI.create(config.getHomePage()))
                .cookie(createCookieWithSecurityToken(user.getSecurityToken()))
                .build();
    }

}
