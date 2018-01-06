package me.kareluo.safecase.core.pojo.dao;

import java.sql.SQLException;
import java.util.List;

import me.kareluo.safecase.core.pojo.Secret;

/**
 * Created by felix on 2017/12/3 下午9:26.
 */

public interface SecretDao extends BaseDao<Secret, String> {

    List<Secret> queryByBelong(String belong) throws SQLException;

}
