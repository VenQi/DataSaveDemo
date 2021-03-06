package com.data.www.datasavedemo.entity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.data.www.datasavedemo.entity.GreenDaoUser;

import com.data.www.datasavedemo.entity.GreenDaoUserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig greenDaoUserDaoConfig;

    private final GreenDaoUserDao greenDaoUserDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        greenDaoUserDaoConfig = daoConfigMap.get(GreenDaoUserDao.class).clone();
        greenDaoUserDaoConfig.initIdentityScope(type);

        greenDaoUserDao = new GreenDaoUserDao(greenDaoUserDaoConfig, this);

        registerDao(GreenDaoUser.class, greenDaoUserDao);
    }
    
    public void clear() {
        greenDaoUserDaoConfig.getIdentityScope().clear();
    }

    public GreenDaoUserDao getGreenDaoUserDao() {
        return greenDaoUserDao;
    }

}
