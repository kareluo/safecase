package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import me.kareluo.safecase.core.pojo.Secret;

/**
 * Created by felix on 2017/12/3 下午9:26.
 */

public class SecretDaoImpl extends BaseDaoImpl<Secret, String> implements SecretDao {

    public SecretDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Secret.class);
    }

    @Override
    public List<Secret> queryByBelong(String belong) throws SQLException {
        return queryBuilder().where().eq(Secret.FIELD_BELONG, belong).query();
    }
}
