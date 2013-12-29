package org.project2action.dao;

import static org.hibernate.criterion.Restrictions.like;

import java.util.List;

import org.hibernate.SessionFactory;
import org.project2action.domain.Idea;

public class IdeaDao extends AbstractDao<Idea> {

	public IdeaDao(SessionFactory sessionFactory)
	{
	  super(sessionFactory);
	}
	
	public List<Idea>  find(String query)
	{
      return list(criteria()
                .add(like("name", "%"+query+"%")));
	}
	
	public Idea   get(Long id)
	{
		return (Idea)session().get(Idea.class, id);
	}
	
	
}
