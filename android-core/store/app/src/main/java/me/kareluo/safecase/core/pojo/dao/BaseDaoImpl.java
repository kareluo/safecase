package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by felix on 2017/12/3 下午9:22.
 */

public class BaseDaoImpl<T, ID> extends com.j256.ormlite.dao.BaseDaoImpl<T, ID> {

    public BaseDaoImpl(ConnectionSource connectionSource, Class dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

}
