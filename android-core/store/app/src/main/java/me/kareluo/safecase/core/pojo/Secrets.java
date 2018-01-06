package me.kareluo.safecase.core.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.kareluo.safecase.core.pojo.dao.SecretsDaoImpl;
import me.kareluo.safecase.core.pojo.fields.SecretsFields;

/**
 * Created by felix on 2017/12/3 下午6:54.
 */

@DatabaseTable(tableName = Secrets.TABLE_NAME, daoClass = SecretsDaoImpl.class)
public class Secrets implements SecretsFields {

    public static final String TABLE_NAME = "secrets";

    @DatabaseField(columnName = FIELD_UID, id = true)
    private String uid;

    @DatabaseField(columnName = FIELD_TYPE)
    private int type;

    @DatabaseField(columnName = FIELD_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = FIELD_REFERENCE)
    private String reference;

    @DatabaseField(columnName = FIELD_REMARK)
    private String remark;

    @DatabaseField(columnName = FIELD_UPDATED)
    private Long updated;

    @DatabaseField(columnName = FIELD_CREATED)
    private Long created;

    protected List<Field> fields;

    protected List<Secret> secrets;

    public Secrets() {

    }

    public Secrets(Secrets secrets) {
        this.uid = secrets.uid;
        this.type = secrets.type;
        this.name = secrets.name;
        this.description = secrets.description;
        this.reference = secrets.reference;
        this.remark = secrets.remark;
        this.updated = secrets.updated;
        this.created = secrets.created;
        this.fields = secrets.fields;
        this.secrets = secrets.secrets;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void addField() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(Field.create(uid));
    }

    public List<Secret> getSecrets() {
        return secrets;
    }

    public void setSecrets(List<Secret> secrets) {
        this.secrets = secrets;
    }

    public void addSecret() {
        if (secrets == null) {
            secrets = new ArrayList<>();
        }
        secrets.add(Secret.create(uid));
    }

    public static Secrets create() {
        Secrets secrets = new Secrets();
        secrets.setUid(UUID.randomUUID().toString());
        return secrets;
    }
}
