package org.project2action.dao;

import org.hibernate.SessionFactory;
import org.project2action.domain.Asset;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;


public class AssetDao extends AbstractDao<Asset> {

    public AssetDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Asset> findByProject(Long projectId) {
        return list(criteria().add(eq("neededInProject.id", projectId)));
    }

    public List<Asset> findByUser(Long userId) {
        return list(criteria().add(eq("providedByUser.id", userId)));
    }

    public List<Asset> getSame(Long assetId) {
        // TODO Auto-generated method stub
        Asset a = get(assetId);
        if (a == null) {
            throw new IllegalArgumentException("Invalid assetId");
        }
        return list(criteria().add(eq("name", a.getName())));
    }


}
