package me.kareluo.safecase.core.pojo.dao;

import java.sql.SQLException;
import java.util.List;

import me.kareluo.safecase.core.pojo.Field;

/**
 * Created by felix on 2017/12/3 下午9:24.
 */

public interface FieldDao extends BaseDao<Field, String> {

    List<Field> queryByBelong(String belong) throws SQLException;
}
