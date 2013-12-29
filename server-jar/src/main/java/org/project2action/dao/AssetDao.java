package org.project2action.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.project2action.domain.Asset;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.isNull;


public class AssetDao extends AbstractDao<Asset> {

	public AssetDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<Asset> findByProject(Long projectId)
	{
	  Criteria c = criteria().add(eq("neededInProject.id",projectId));
	  return c.list();
	}
	
	public List<Asset> findByUser(Long userId)
	{
	  Criteria c = criteria().add(eq("providedByUser.id",userId));
	  return c.list();
	}

	public List<Asset> getSame(Long assetId) {
		// TODO Auto-generated method stub
		Asset a = get(assetId);
		if (a==null) {
			throw new IllegalArgumentException("Invalid assetId");
		}
		return list(criteria().add(eq("name",a.getName())));
	}
	
	

}
