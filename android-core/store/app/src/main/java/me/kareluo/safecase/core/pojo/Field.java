package me.kareluo.safecase.core.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import me.kareluo.safecase.core.pojo.dao.FieldDaoImpl;
import me.kareluo.safecase.core.pojo.fields.FieldFields;

/**
 * Created by felix on 2017/12/3 下午7:04.
 */
@DatabaseTable(tableName = Field.TABLE_NAME, daoClass = FieldDaoImpl.class)
public class Field implements FieldFields {

    public static final String TABLE_NAME = "field";

    @DatabaseField(columnName = FIELD_UID, id = true)
    private String uid;

    @DatabaseField(columnName = FIELD_TYPE)
    private int type;

    @DatabaseField(columnName = FIELD_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_VALUE)
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
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
        return "Field{" +
                "uid='" + uid + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", belong='" + belong + '\'' +
                ", updated=" + updated +
                ", created=" + created +
                '}';
    }
}
