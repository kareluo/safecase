package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import me.kareluo.safecase.core.pojo.Field;

/**
 * Created by felix on 2017/12/3 下午9:25.
 */

public class FieldDaoImpl extends BaseDaoImpl<Field, String> implements FieldDao {

    public FieldDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Field.class);
    }

    @Override
    public List<Field> queryByBelong(String belong) throws SQLException {
        return queryBuilder().where().eq(Field.FIELD_BELONG, belong).query();
    }
}
