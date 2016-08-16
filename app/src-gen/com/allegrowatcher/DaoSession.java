package com.allegrowatcher;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.allegrowatcher.AllegroId;

import com.allegrowatcher.AllegroIdDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig allegroIdDaoConfig;

    private final AllegroIdDao allegroIdDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        allegroIdDaoConfig = daoConfigMap.get(AllegroIdDao.class).clone();
        allegroIdDaoConfig.initIdentityScope(type);

        allegroIdDao = new AllegroIdDao(allegroIdDaoConfig, this);

        registerDao(AllegroId.class, allegroIdDao);
    }
    
    public void clear() {
        allegroIdDaoConfig.getIdentityScope().clear();
    }

    public AllegroIdDao getAllegroIdDao() {
        return allegroIdDao;
    }

}
