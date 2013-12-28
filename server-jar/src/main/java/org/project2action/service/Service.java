package org.project2action.service;

import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import org.project2action.UserInjector;
import org.project2action.config.Authorization;
import org.project2action.config.Config;
import org.project2action.dao.PersonDao;
import org.project2action.dao.PollDao;
import org.project2action.dao.QueueDao;
import org.project2action.dao.UserDao;
import org.project2action.domain.Person;
import org.project2action.domain.Poll;
import org.project2action.domain.Queue;
import org.project2action.domain.User;
import org.project2action.resource.PersonResource;
import org.project2action.resource.PollResource;
import org.project2action.resource.QueueResource;
import org.project2action.security.DummyOAuthResource;
import org.project2action.security.GoogleOAuth2Resource;
import org.project2action.security.SecureTokenFilter;


public class Service extends com.yammer.dropwizard.Service<Config> {

    private final HibernateBundle<Config> hibernate = new HibernateBundle<Config>(Person.class, User.class, Queue.class, Poll.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(Config configuration) {
            return configuration.getDatabaseConfiguration();
        }
    };

    public static void main(String[] arg) throws Exception {
        new Service().run(arg);
    }

    @Override
    public void initialize(Bootstrap<Config> configBootstrap) {
        configBootstrap.setName("/");
        configBootstrap.addBundle(hibernate);
    }

    @Override
    public void run(Config config, Environment env) throws Exception {
        env.addResource(createTicketResource());
        env.addResource(createProjectResource());
        env.addResource(createOAuth2Resource(config));
        env.addResource(new PollResource(new PollDao(hibernate.getSessionFactory())));
        env.addProvider(new UserInjector(getUserDao(), config));
        Authorization auth = config.getAuthorization();
        env.addFilter(new SecureTokenFilter(auth.getSecurityTokenName()), auth.getSecureUrl()+"/*");
        env.addResource(new DummyOAuthResource(getUserDao(), auth));
        env.addResource(new AssetsBundle(config.getAssetsLocation(),"/assets/"));
    }

    private QueueResource createProjectResource() {
        return new QueueResource(new QueueDao(hibernate.getSessionFactory()), getUserDao());
    }

    private GoogleOAuth2Resource createOAuth2Resource(Config config) {
        return new GoogleOAuth2Resource(getUserDao(), config.getAuthorization());
    }

    private UserDao getUserDao() {
        return new UserDao(hibernate.getSessionFactory());
    }

    public PersonResource createTicketResource() {
        final PersonDao personDao = new PersonDao(hibernate.getSessionFactory());
        return new PersonResource(personDao);
    }
}
