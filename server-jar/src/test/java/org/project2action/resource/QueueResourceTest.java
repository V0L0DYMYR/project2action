package org.project2action.resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.project2action.dao.PersonDao;
import org.project2action.domain.Queue;
import org.project2action.domain.Person;
import org.project2action.domain.User;
import org.project2action.unitofwork.UnitOfWorkRule;

public class QueueResourceTest {

    QueueResource queueResource;

    @Rule
    public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule("conf/in-queue-test.json", User.class, Person.class, Queue.class);

    @Before
    public void setUp(){
        PersonDao personDao = new PersonDao(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void givenProjectId_whenGETTikects_ReturnTicketOnlyFromThatproject(){

    }

    @Test
    public void givenUserId_whenGETProjects_ReturnProjectsAvailableForThatUser(){

    }
}
