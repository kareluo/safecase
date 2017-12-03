package me.kareluo.safecase.core.pojo;

import java.util.List;

/**
 * Created by felix on 2017/12/3 下午10:51.
 */

public class SecretsViewModel extends Secrets {

    private List<Field> fields;

    private List<Secret> secrets;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Secret> getSecrets() {
        return secrets;
    }

    public void setSecrets(List<Secret> secrets) {
        this.secrets = secrets;
    }

    public int getItemCount() {

        int itemCount = 2;

        if (fields != null) {
            itemCount += fields.size();
        }

        if (secrets != null) {
            itemCount += secrets.size();
        }

        return itemCount;
    }
}
