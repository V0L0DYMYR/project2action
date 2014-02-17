package org.project2action.common;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import org.project2action.config.Config;
import org.project2action.dao.UserDao;
import org.project2action.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;

@Provider
public class UserInjector extends AbstractHttpContextInjectable<User> implements InjectableProvider<Context, Type> {
    private Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserDao userDao;
    private String securityTokenName = null;

    public UserInjector(UserDao userDao, Config config){
        this.userDao = userDao;
        this.securityTokenName = config.getAuthorization().getSecurityTokenName();
    }

    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable<User> getInjectable(ComponentContext ic, Context context, Type type) {
        LOG.trace(type.toString());
        if (type.equals(User.class)) {
            return this;
        }
        return null;
    }

    @Override
    public User getValue(HttpContext c) {
        String securityToken = c.getRequest().getCookieNameValueMap().getFirst(securityTokenName);
        return userDao.findBySecurityToken(securityToken);
    }
}
