package org.project2action.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.project2action.domain.Person;
import org.project2action.domain.Queue;
import org.project2action.domain.User;
import org.project2action.unitofwork.UnitOfWorkRule;

import java.util.List;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.project2action.Utils.asSet;

@Ignore
public class TicketDaoTest {

    PersonDao personDao;
    UserDao userDao;
    QueueDao queueDao;

    @Rule
    public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule("conf/in-queue-test.json", User.class, Person.class, Queue.class);

    @Before
    public void setUp() {
        personDao = new PersonDao(unitOfWorkRule.getSessionFactory());
        userDao = new UserDao(unitOfWorkRule.getSessionFactory());
        queueDao = new QueueDao(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void givenProjectId_whenGETTikects_ReturnTicketOnlyFromThatProject() {
        Long projectId = createProjectWithUser();
        createTicket(projectId);
        createTicket(projectId);

        Long projectId_2 = createProjectWithUser();
        createTicket(projectId_2);

        //List<Person> persons = personDao.findByQueue(1L);
        //assertThat(persons.size()).isEqualTo(2);
    }

    @Test
    public void givenUserId_whenGETTickets_ReturnProjectsOnlyThatUsers() {
        User user = createUser();
        createProject("A project", user);
        createProject("B project", user);

        User user_2 = createUser();
        createProject("C project", user_2);

        List<Queue> queues = queueDao.findByUser(user);
        assertThat(queues.size()).isEqualTo(2);
    }

    private User createUser() {
        User user = userDao.saveOrUpdate(newUser());
        return user;
    }

    private User newUser() {
        return new User(null, "1", "user@gmail.com", "Ivan Ivanov", "en");
    }

    @Test
    public void givenTicketWithNotUniqueLabes_whenSaveTicket_LabelsAreUniqueInTicket() {
        Long projectId = createProjectWithUser();
        createTicket(projectId, asSet("sprint 1", "sprint 1", "sprint 2", "sprint 1", "sprint 2"));
        List<Person> all = personDao.findAll();
        Person person = all.get(0);
    }

    @Test
    public void givenTicketWithTwoLabels_afterSecondSaveWithOneLabel_WillHasOnlyOneLabel() {
        Long projectId = createProjectWithUser();
        Person person = personDao.saveOrUpdate(newTicket(projectId, asSet("A", "B")));

        person = personDao.merge(new Person(person.getId(), "B Person", 1L, projectId));
    }

    private Long createProject(String name, User user) {
        return queueDao.saveOrUpdate(new Queue(name, user.getId())).getId();
    }

    @Test
    public void givenLabel_whenFindTickets_ReturnAllTicketsWithinAllRlatedLabels() {
        Long projectId = createProjectWithUser();
        createTicket(projectId, asSet("AAA"));
        createTicket(projectId, asSet("AAA"));
        createTicket(projectId, asSet("BBB"));

        List<Person> aPersons = personDao.findByLabel("AAA");

        assertThat(aPersons.size()).isEqualTo(2);
    }

    private Long createProjectWithUser() {
        User user = createUser();
        return queueDao.saveOrUpdate(new Queue("project name", user.getId())).getId();
    }

    private Person createTicket(Long projectId) {
        return createTicket(projectId, null);
    }

    private Person createTicket(Long projectId, Set<String> labels) {
        return personDao.saveOrUpdate(newTicket(projectId, labels));
    }

    private Person newTicket(Long projectId, Set<String> labels) {
        return new Person(null, "default title", 1L, projectId);
    }

}
