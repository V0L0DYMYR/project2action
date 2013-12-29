package org.project2action.resource;

import java.util.Calendar;
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
import org.project2action.dao.ProjectDao;
import org.project2action.dao.UserDao;
import org.project2action.domain.Idea;
import org.project2action.domain.Project;
import org.project2action.domain.User;

import com.yammer.dropwizard.hibernate.UnitOfWork;

@Path("/api/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

	private final ProjectDao projectDao;
	private final UserDao    userDao;
	
	   public ProjectResource(ProjectDao  projectDao, UserDao userDao) {
	        this.projectDao = projectDao;
	        this.userDao = userDao;
	    }

	    @GET
	    @UnitOfWork
	    // TODO: get all last 30
	    public List<Project> getProjects() {
	        return projectDao.findAll();
	    }

	    
	    @GET
	    @Path("{id}")
	    @UnitOfWork
	    public Project get(@Context User user, @PathParam("id")Long projectId) {
	        System.out.println(user+" "+ "get project "+projectId);
	        return projectDao.get(projectId);
	    }
	    
	   
	    @POST
	    @UnitOfWork
	    public Project save(@Context User user, Project project) {
	        return projectDao.saveOrUpdate(project.withInitiator(user).withStartDate(Calendar.getInstance().getTime()));
	    }

	    @PUT
	    @Path("{id}")
	    @UnitOfWork
	    public Project amend(Project project) {
	    	if (project.getInitiatorId()!=null) {
	    		User author = userDao.get(project.getInitiatorId());
	    		if (author==null) {
	    			throw new IllegalArgumentException("Author id is incorrect");
	    		}
	    		project = project.withInitiator(author);
	    	}
	        return projectDao.saveOrUpdate(project);
	    }

	    @DELETE
	    @Path("{id}")
	    @UnitOfWork
	    public void delete(@Context User user, @PathParam("id")Long id) {
	        projectDao.delete(id);
	    }

	
	
	
}
