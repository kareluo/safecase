package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import me.kareluo.safecase.core.pojo.Secrets;

/**
 * Created by felix on 2017/12/3 下午9:27.
 */

public class SecretsDaoImpl extends BaseDaoImpl<Secrets, String> implements SecretsDao {

    public SecretsDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Secrets.class);
    }

}
