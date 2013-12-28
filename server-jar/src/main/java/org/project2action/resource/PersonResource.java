package org.project2action.resource;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.project2action.dao.PersonDao;
import org.project2action.domain.Person;
import org.project2action.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private Logger LOG = LoggerFactory.getLogger(PersonResource.class);
    private PersonDao personDao;

    public PersonResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    public List<Person> get(@Context User user, @PathParam("id")Long queueId) {
        System.out.println(user+" "+ queueId);
        return personDao.findByQueue(user, queueId);
    }

    @POST
    @UnitOfWork
    public Person save(@Context User user, Person person) {
        return personDao.saveOrUpdate(new Person(person, user));
    }

    @PUT
    @Path("{id}")
    @UnitOfWork
    public Person amend(Person person) {
        return personDao.saveOrUpdate(person);
    }

    @DELETE
    @Path("{id}")
    @UnitOfWork
    public void delete(@Context User user, @PathParam("id")Long id) {
        personDao.delete(id);
    }
}
