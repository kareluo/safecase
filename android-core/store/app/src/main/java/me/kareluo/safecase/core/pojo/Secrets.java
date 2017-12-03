package me.kareluo.safecase.core.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import me.kareluo.safecase.core.pojo.fields.SecretsFields;

/**
 * Created by felix on 2017/12/3 下午6:54.
 */

@DatabaseTable()
public class Secrets implements SecretsFields {

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

    @Override
    public String toString() {
        return "Secrets{" +
                "uid='" + uid + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", reference='" + reference + '\'' +
                ", remark='" + remark + '\'' +
                ", updated=" + updated +
                ", created=" + created +
                '}';
    }
}
