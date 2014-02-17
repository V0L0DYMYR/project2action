package org.project2action.resource;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.project2action.common.Utils;
import org.project2action.dao.IdeaDao;
import org.project2action.dao.ProjectDao;
import org.project2action.dao.UserDao;
import org.project2action.domain.Idea;
import org.project2action.domain.Project;
import org.project2action.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.project2action.common.Utils.asSet;


@Path("/api/idea")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IdeaResource {

    private static Logger log = LoggerFactory.getLogger(IdeaResource.class);
    private IdeaDao ideaDao;
    private UserDao userDao;
    private ProjectDao projectDao;

    public IdeaResource(IdeaDao ideaDao, UserDao userDao, ProjectDao projectDao) {
        this.ideaDao = ideaDao;
        this.userDao = userDao;
        this.projectDao = projectDao;
    }

    @GET
    @UnitOfWork
    // TODO: get all last 30
    public List<Idea> getIdeas() {
        return ideaDao.findAll();
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    public Idea get(@Context User user, @PathParam("id") Long ideaId) {
        Idea idea = ideaDao.get(ideaId);
        List<Project> projects = projectDao.findByIdea(idea.getId());
        return idea.withProjects(projects);
    }

    @GET
    @Path("{id}/projects")
    @UnitOfWork
    public List<Project> getProjects(@Context User user, @PathParam("id") Long ideaId) {
        System.out.println(user + " " + "get idea " + ideaId);
        return projectDao.findByIdea(ideaId);
    }

    @PUT
    @Path("{id}/like")
    @UnitOfWork
    public Idea like(@Context User user, @PathParam("id") Long ideaId){
        Idea idea = ideaDao.get(ideaId).withLikes(asSet(user));
        return ideaDao.merge(idea);
    }

    @POST
    @UnitOfWork
    public Idea save(@Context User user, Idea idea) {
        return ideaDao.saveOrUpdate(idea.withAuthor(user));
    }

    @PUT
    @Path("{id}")
    @UnitOfWork
    public Idea amend(Idea idea) {
        if (idea.getAuthorId() != null) {
            User author = userDao.get(idea.getAuthorId());
            if (author == null) {
                throw new IllegalArgumentException("Author id is incorrect");
            }
            idea = idea.withAuthor(author);
        }
        return ideaDao.saveOrUpdate(idea);
    }

    @DELETE
    @Path("{id}")
    @UnitOfWork
    public void delete(@Context User user, @PathParam("id") Long id) {
        ideaDao.delete(id);
    }


}
