package me.kareluo.safecase.core.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import me.kareluo.safecase.core.pojo.dao.SecretDaoImpl;
import me.kareluo.safecase.core.pojo.fields.SecretFields;

/**
 * Created by felix on 2017/12/3 下午6:53.
 */
@DatabaseTable(tableName = Secret.TABLE_NAME, daoClass = SecretDaoImpl.class)
public class Secret implements SecretFields {

    public static final String TABLE_NAME = "secret";

    @DatabaseField(columnName = FIELD_UID)
    private String uid;

    @DatabaseField(columnName = FIELD_USERNAME)
    private String username;

    @DatabaseField(columnName = FIELD_PASSWORD)
    private String password;

    @DatabaseField(columnName = FIELD_PASSWORD_ALIAS)
    private String passwordAlias;

    @DatabaseField(columnName = FIELD_REFERENCE)
    private String reference;

    @DatabaseField(columnName = FIELD_REMARK)
    private String remark;

    @DatabaseField(columnName = FIELD_BELONG)
    private String belong;

    @DatabaseField(columnName = FIELD_UPDATED)
    private Long updated;

    @DatabaseField(columnName = FIELD_CREATED)
    private Long created;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAlias() {
        return passwordAlias;
    }

    public void setPasswordAlias(String passwordAlias) {
        this.passwordAlias = passwordAlias;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Secret{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordAlias='" + passwordAlias + '\'' +
                ", reference='" + reference + '\'' +
                ", remark='" + remark + '\'' +
                ", belong='" + belong + '\'' +
                ", updated=" + updated +
                ", created=" + created +
                '}';
    }
}
