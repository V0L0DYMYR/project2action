package org.project2action.dao;

import static org.hibernate.criterion.Restrictions.like;

import java.util.List;

import org.hibernate.SessionFactory;
import org.project2action.domain.Idea;
import org.project2action.domain.Project;
import org.project2action.domain.User;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.hibernate.criterion.Restrictions.in;


public class ProjectDao extends AbstractDao<Project> {

	public ProjectDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Project>  findByName(String query)
	{
      return list(criteria()
                .add(like("name", "%"+query+"%")));
	}
	
	public List<Project>  findByIdea(Long ideaId)
	{
      return list(criteria()
                .add(eq("idea.id", ideaId)));
	}
	
	
	public List<Project>  findByInitiator(Long initiatorId)
	{
      return list(criteria()
                .add(eq("initiator.id", initiatorId)));
	}
	
	

	public List<Project>  findByParticipant(User participant)
	{
		// select o from Project o where x in o.participants
		throw new RuntimeException("Not implemented");
  //    return list(criteria()
  //              .add(in("participants", )));
	}
	
	
	public Project   get(Long id)
	{
		return (Project)session().get(Project.class, id);
	}
	
	
	
	
}
