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

import org.project2action.dao.AssetDao;
import org.project2action.dao.IdeaDao;
import org.project2action.dao.ProjectDao;
import org.project2action.dao.UserDao;
import org.project2action.domain.Asset;
import org.project2action.domain.Idea;
import org.project2action.domain.Project;
import org.project2action.domain.User;

import com.yammer.dropwizard.hibernate.UnitOfWork;

/**
 * Resource for assets
 * @author rssh
 *
 */
@Path("/api/asset")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AssetResource {

	private AssetDao assetDao;
	private UserDao  userDao;
	private ProjectDao projectDao;
	
	  public AssetResource(AssetDao  assetDao, UserDao userDao, ProjectDao projectDao) {
	        this.assetDao = assetDao;
	        this.userDao = userDao;
	        this.projectDao = projectDao;
	    }

	    @GET
	    @UnitOfWork
	    // TODO: get all last 30
	    public List<Asset> getAll() {
	        return assetDao.findAll();
	    }


	    @GET
	    @Path("my")
	    @UnitOfWork
	    public List<Asset> getMy(@Context User user, @PathParam("id")Long assetId) {
	        System.out.println(user+" "+ "get asset "+assetId);
	        return assetDao.findByUser(user.getId());
	    }

	    
	    @GET
	    @Path("{id}")
	    @UnitOfWork
	    public Asset get(@Context User user, @PathParam("id")Long assetId) {
	        System.out.println(user+" "+ "get asset "+assetId);
	        return assetDao.get(assetId);
	    }

	    @GET
	    @Path("{id}/same")
	    @UnitOfWork
	    public List<Asset> getSame(@Context User user, @PathParam("id")Long assetId) {
	        System.out.println(user+" "+ "get same asset "+assetId);
	        return assetDao.getSame(assetId);
	    }
	    

	    @POST
	    @UnitOfWork
	    public Asset save(@Context User user, Asset asset) {
	    	if (asset.getNeededInProjectId()!=null) {
	    		Project project = projectDao.get(asset.getNeededInProjectId());
	    		if (project==null) {
	    			throw new IllegalArgumentException("project not found in db");
	    		}
	    		asset = asset.withNeededInProject(project);
	    	} else {
	    		// attach one to current user.
	    		asset = asset.withProvidedByUser(user);
	    	}
	        return assetDao.saveOrUpdate(asset);
	    }

	    @PUT
	    @Path("{id}")
	    @UnitOfWork
	    public Asset amend(Asset asset) {
	    	if (asset.getNeededInProjectId()!=null) {
	    		Project project = projectDao.get(asset.getNeededInProjectId());
	    		if (project==null) {
	    			throw new IllegalArgumentException("Project id is incorrect");
	    		}
	    	    asset = asset.withNeededInProject(project);
	    	}
	        return assetDao.saveOrUpdate(asset);
	    }

	    @DELETE
	    @Path("{id}")
	    @UnitOfWork
	    public void delete(@Context User user, @PathParam("id")Long id) {
	        assetDao.delete(id);
	    }


	
}
