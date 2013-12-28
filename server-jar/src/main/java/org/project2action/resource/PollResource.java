package org.project2action.resource;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.project2action.dao.PollDao;
import org.project2action.domain.Poll;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/poll")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PollResource {
    private final PollDao pollDao;

    public PollResource(PollDao pollDao) {
        this.pollDao = pollDao;
    }

    @GET
    @UnitOfWork
    public List<Poll> getPolls() {
        return pollDao.findAll();
    }

    @GET
    @Path("{pollId}")
    @UnitOfWork
    public Poll getPoll(@PathParam("pollId") Long pollId) {
        return pollDao.get(pollId);
    }

    @POST
    @UnitOfWork
    public Poll createProject(Poll poll) {
        return pollDao.saveOrUpdate(poll);
    }
}
