package me.kareluo.safecase.core.pojo;

/**
 * Created by felix on 2017/12/3 下午10:51.
 */

public class SecretsViewModel extends Secrets {

    public static final int TYPE_HEAD = 0;

    public static final int TYPE_FIELD = 1;

    public static final int TYPE_SECRET = 2;

    public SecretsViewModel() {
        super();
    }

    public SecretsViewModel(Secrets secrets) {
        super(secrets);
    }

    public int getViewType(int position) {

        // Header
        int itemCount = 1;
        if (position < itemCount) {
            return TYPE_HEAD;
        }

        if (fields != null) {
            itemCount += fields.size();
            if (position < itemCount) {
                return TYPE_FIELD;
            }
        }

        if (secrets != null) {
            for (Secret secret : secrets) {
                itemCount++;
                if (position < itemCount) {
                    return TYPE_SECRET;
                }
                itemCount += secret.getFieldCount();
                if (position < itemCount) {
                    return TYPE_FIELD;
                }
            }
        }

        return -1;
    }

    public int getItemCount() {

        // Header
        int itemCount = 1;

        // Header's Fields
        if (fields != null) {
            itemCount += fields.size();
        }

        if (secrets != null) {
            itemCount += secrets.size();
            for (Secret secret : secrets) {
                itemCount += secret.getFieldCount();
            }
        }

        return itemCount;
    }

    public Secret getSecret(int position) {

        // Header
        int itemCount = 1;

        // Header's Fields
        if (fields != null) {
            itemCount += fields.size();
        }

        if (secrets != null) {
            for (Secret secret : secrets) {
                itemCount++;
                if (position < itemCount) {
                    return secret;
                }
                itemCount += secret.getFieldCount();
            }
        }

        return null;
    }

    public Field getField(int position) {

        // Header
        int itemCount = 1;

        // Header's Fields
        if (fields != null) {
            if (position < itemCount + fields.size()) {
                return fields.get(position - itemCount);
            }
            itemCount += fields.size();
        }

        if (secrets != null) {
            for (Secret secret : secrets) {
                itemCount++;
                if (position < itemCount + secret.getFieldCount()) {
                    return secret.getFields().get(position - itemCount);
                }
                itemCount += secret.getFieldCount();
            }
        }

        return null;
    }
}
