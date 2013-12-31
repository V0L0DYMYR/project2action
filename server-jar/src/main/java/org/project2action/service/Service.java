package org.project2action.service;

import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import org.project2action.UserInjector;
import org.project2action.config.Authorization;
import org.project2action.config.Config;
import org.project2action.dao.AssetDao;
import org.project2action.dao.IdeaDao;
import org.project2action.dao.ProjectDao;
import org.project2action.dao.UserDao;
import org.project2action.domain.Asset;
import org.project2action.domain.Idea;
import org.project2action.domain.Project;
import org.project2action.domain.User;
import org.project2action.resource.AssetResource;
import org.project2action.resource.IdeaResource;
import org.project2action.resource.ProjectResource;
import org.project2action.security.DummyOAuthResource;
import org.project2action.security.GoogleOAuth2Resource;
import org.project2action.security.SecureTokenFilter;


public class Service extends com.yammer.dropwizard.Service<Config> {

    private final HibernateBundle<Config> hibernate = new HibernateBundle<Config>(
            User.class, Idea.class, Project.class, Asset.class) {
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
        final IdeaDao ideaDao = new IdeaDao(hibernate.getSessionFactory());
        final UserDao userDao = new UserDao(hibernate.getSessionFactory());
        final ProjectDao projectDao = new ProjectDao(hibernate.getSessionFactory());
        final AssetDao assetDao = new AssetDao(hibernate.getSessionFactory());

        env.addResource(createOAuth2Resource(config, userDao));
        env.addResource(createIdeaResource(ideaDao, userDao, projectDao));
        env.addResource(createProjectResource(projectDao, userDao, assetDao));
        env.addResource(new AssetResource(assetDao, userDao, projectDao));
        env.addProvider(new UserInjector(userDao, config));
        Authorization auth = config.getAuthorization();
        env.addFilter(new SecureTokenFilter(auth.getSecurityTokenName()), auth.getSecureUrl() + "/*");
        env.addResource(new DummyOAuthResource(userDao, auth));
        env.addResource(new AssetsBundle(config.getAssetsLocation(), "/assets/"));
    }

    private GoogleOAuth2Resource createOAuth2Resource(Config config, UserDao userDao) {
        return new GoogleOAuth2Resource(userDao, config.getAuthorization());
    }

    public IdeaResource createIdeaResource(IdeaDao ideaDao, UserDao userDao, ProjectDao projectDao) {
        return new IdeaResource(ideaDao, userDao, projectDao);
    }

    public ProjectResource createProjectResource(ProjectDao projectDao, UserDao userDao, AssetDao assetDao) {
        return new ProjectResource(projectDao, userDao, assetDao);
    }

}
