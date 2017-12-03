package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import me.kareluo.safecase.core.pojo.Field;

/**
 * Created by felix on 2017/12/3 下午9:25.
 */

public class FieldDaoImpl extends BaseDaoImpl<Field, String> implements BaseDao<Field, String> {

    public FieldDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Field.class);
    }

}
