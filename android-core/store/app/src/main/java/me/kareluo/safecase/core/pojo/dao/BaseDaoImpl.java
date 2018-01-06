package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by felix on 2017/12/3 下午9:22.
 */

public class BaseDaoImpl<T, ID> extends com.j256.ormlite.dao.BaseDaoImpl<T, ID>
        implements BaseDao<T, ID> {

    public BaseDaoImpl(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public boolean isEmpty() throws SQLException {
        return countOf() == 0;
    }

    @Override
    public void createOrUpdate(Collection<T> datas) throws SQLException {
        if (datas != null) {
            for (T data : datas) {
                createOrUpdate(data);
            }
        }
    }
}
