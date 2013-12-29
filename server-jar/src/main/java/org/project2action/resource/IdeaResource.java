package org.project2action.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.project2action.dao.IdeaDao;
import org.project2action.dao.PersonDao;
import org.project2action.dao.UserDao;
import org.project2action.domain.Idea;
import org.project2action.domain.Person;
import org.project2action.domain.Poll;
import org.project2action.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.dropwizard.hibernate.UnitOfWork;


@Path("/api/idea")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IdeaResource {

    private static Logger log = LoggerFactory.getLogger(IdeaResource.class);
    private IdeaDao ideaDao;
    private UserDao userDao;

    public IdeaResource(IdeaDao  ideaDao, UserDao userDao) {
        this.ideaDao = ideaDao;
        this.userDao = userDao;
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
    public Idea get(@Context User user, @PathParam("id")Long ideaId) {
        System.out.println(user+" "+ "get idea "+ideaId);
        return ideaDao.get(ideaId);
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
    	if (idea.getAuthorId()!=null) {
    		User author = userDao.get(idea.getAuthorId());
    		if (author==null) {
    			throw new IllegalArgumentException("Author id is incorrect");
    		}
    		idea = idea.withAuthor(author);
    	}
        return ideaDao.saveOrUpdate(idea);
    }

    @DELETE
    @Path("{id}")
    @UnitOfWork
    public void delete(@Context User user, @PathParam("id")Long id) {
        ideaDao.delete(id);
    }

	
}
