package org.project2action.unitofwork;

import org.hibernate.SessionFactory;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class UnitOfWorkRule extends TestWatcher {

    UnitOfWorkHelper unitOfWorkHelper = new UnitOfWorkHelper();
    boolean commitDefault = false;

    public UnitOfWorkRule(String config, Class<?>... entities){
        try {
            unitOfWorkHelper.initDB(config, entities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UnitOfWorkRule commitDefault(){
        commitDefault = true;
        return this;
    }

    @Override
    protected void starting(Description description) {
        unitOfWorkHelper.startSession();
    }

    @Override
    protected void finished(Description description) {
        if(commitDefault)
            unitOfWorkHelper.commitAndCloseSession();
        else
            unitOfWorkHelper.rollbackAndCloseSession();
    }

    public SessionFactory getSessionFactory() {
        return unitOfWorkHelper.getSessionFactory();
    }
}
