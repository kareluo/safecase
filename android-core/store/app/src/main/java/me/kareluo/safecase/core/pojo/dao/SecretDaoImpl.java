package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import me.kareluo.safecase.core.pojo.Secret;

/**
 * Created by felix on 2017/12/3 下午9:26.
 */

public class SecretDaoImpl extends BaseDaoImpl<Secret, String> implements BaseDao<Secret, String> {

    public SecretDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Secret.class);
    }

}
