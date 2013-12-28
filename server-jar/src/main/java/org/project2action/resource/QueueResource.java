package org.project2action.resource;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.project2action.dao.QueueDao;
import org.project2action.dao.UserDao;
import org.project2action.domain.Queue;
import org.project2action.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("/api/queue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QueueResource {

    private QueueDao queueDao;
    private UserDao userDao;

    public QueueResource(QueueDao queueDao, UserDao userDao) {
        this.queueDao = queueDao;
        this.userDao = userDao;
    }

    @GET
    @UnitOfWork
    public List<Queue> getQueues(@Context User user){
        return queueDao.findByUser(user);
    }

    @GET
    @Path("find/{input}")
    @UnitOfWork
    public List<Queue> find(@PathParam("input") String input){
        return queueDao.findByName(input);
    }

    @POST
    @UnitOfWork
    public Queue createProject(@Context User user, Queue queue){
        return queueDao.saveOrUpdate(new Queue(queue, user));
    }

    @POST
    @Path("{queueId}/jumpIn")
    @UnitOfWork
    public void jumpIn(@Context User user, @PathParam("queueId") Long queueId){
        Queue queue = queueDao.get(queueId);
        userDao.saveOrUpdate(user.jumpIn(queue));
    }

    @GET
    @Path("memberIn")
    @UnitOfWork
    public List<Queue> memberIn(@Context User user){
        return user.getMemberIn();
    }

    @DELETE
    @Path("{id}")
    @UnitOfWork
    public void deleteQueue(@Context User user, @PathParam("id") Long id){
        queueDao.delete(id);
    }

}
