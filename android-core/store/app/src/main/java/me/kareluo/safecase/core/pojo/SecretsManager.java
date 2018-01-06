package me.kareluo.safecase.core.pojo;

import java.sql.SQLException;
import java.util.List;

import me.kareluo.safecase.core.pojo.dao.FieldDao;
import me.kareluo.safecase.core.pojo.dao.SecretDao;
import me.kareluo.safecase.core.pojo.dao.SecretsDao;

/**
 * Created by felix on 2018/1/6 下午3:35.
 */

public class SecretsManager {

    public static Secrets getSecrets(String secretUid) throws SQLException {
        SecretsDao secretsDao = DatabaseHelper.get(Secrets.class);
        Secrets secrets = secretsDao.queryForId(secretUid);
        if (secrets != null) {
            FieldDao fieldDao = DatabaseHelper.get(Field.class);
            SecretDao secretDao = DatabaseHelper.get(Secret.class);
            List<Secret> ss = secretDao.queryByBelong(secrets.getUid());
            if (ss != null) {
                for (Secret secret : ss) {
                    secret.setFields(fieldDao.queryByBelong(
                            secretUid + ":" + secret.getUid()));
                }
                secrets.setSecrets(ss);
            }

            secrets.setFields(fieldDao.queryByBelong(secretUid));
        }
        return secrets;
    }

    public static void setSecrets(Secrets secrets) throws SQLException {
        if (secrets == null) return;

        SecretsDao secretsDao = DatabaseHelper.get(Secrets.class);
        secretsDao.createOrUpdate(secrets);

        FieldDao fieldDao = DatabaseHelper.get(Field.class);
        fieldDao.createOrUpdate(secrets.getFields());

        List<Secret> ss = secrets.getSecrets();
        if (ss != null) {
            SecretDao secretDao = DatabaseHelper.get(Secret.class);
            for (Secret secret : ss) {
                secretDao.createOrUpdate(secret);
                fieldDao.createOrUpdate(secret.getFields());
            }
        }
    }
}
