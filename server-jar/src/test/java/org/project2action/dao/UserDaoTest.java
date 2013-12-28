package org.project2action.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.project2action.domain.Person;
import org.project2action.domain.Queue;
import org.project2action.domain.User;
import org.project2action.unitofwork.UnitOfWorkRule;

@Ignore
public class UserDaoTest {

    UserDao userDao;

    @Rule
    public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule("conf/in-queue-test.json", User.class, Person.class, Queue.class);

    @Before
    public void setUp(){
        userDao = new UserDao(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void givenValidUser_whenSave_willAppearInDb(){
        User user = new User(null, "1", "user@gmail.com", "Ivan Ivanov", "en");
        userDao.saveOrUpdate(user);
    }

    @Test
    public void find(){
        System.out.println(userDao.findAll());
    }
}
