package me.kareluo.safecase.core.pojo;

import java.util.List;

/**
 * Created by felix on 2017/12/3 下午10:44.
 */

public class SecretViewModel extends Secret {

    private List<Field> fields;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "SecretViewModel{" +
                "fields=" + fields +
                "} " + super.toString();
    }
}
