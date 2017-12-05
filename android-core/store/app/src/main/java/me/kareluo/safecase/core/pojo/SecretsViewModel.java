package me.kareluo.safecase.core.pojo;

import java.util.List;

/**
 * Created by felix on 2017/12/3 下午10:51.
 */

public class SecretsViewModel extends Secrets {

    public static final int TYPE_HEAD = 0;
    public static final int TYPE_FIELD = 1;
    public static final int TYPE_SECRET = 2;

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

    public int getViewType(int position) {
        int itemCount = 1;
        if (position == 0) {
            return TYPE_HEAD;
        }

        if (fields != null) {
            itemCount += fields.size();
            if (position < itemCount) {
                return TYPE_FIELD;
            }
        }

//        itemCount++;

        if (secrets != null) {
            itemCount += secrets.size();
            if (position < itemCount) {
                return TYPE_SECRET;
            }
        }

        return 0;
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
