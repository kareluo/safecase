package me.kareluo.safecase.core.pojo.dao;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by felix on 2017/12/3 下午9:21.
 */

public interface BaseDao<T, ID> extends Dao<T, ID> {

    boolean isEmpty() throws SQLException;

    void createOrUpdate(Collection<T> datas) throws SQLException;

}
