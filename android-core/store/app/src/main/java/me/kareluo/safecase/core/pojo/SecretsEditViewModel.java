package me.kareluo.safecase.core.pojo;

/**
 * Created by felix on 2017/12/3 下午10:51.
 */

public class SecretsEditViewModel extends SecretsViewModel {

    public static final int TYPE_FIELD_MORE = 100;

    public static final int TYPE_SECRET_MORE = 101;

    public SecretsEditViewModel() {
        super();
    }

    public SecretsEditViewModel(Secrets secrets) {
        super(secrets);
    }

    public void addFiled(int position) {

        // Header
        int itemCount = 1;
        if (position < itemCount) {
            return;
        }

        if (fields != null) {
            // Fields
            itemCount += fields.size();
        }

        if (position < itemCount) {
            return;
        }

        // Header's Fields More.
        itemCount++;
        if (position < itemCount) {
            addField();
            return;
        }

        if (secrets != null) {
            for (Secret secret : secrets) {
                itemCount += secret.getFieldCount() + 1;
                if (position < itemCount) {
                    return;
                }
                itemCount++;
                if (position < itemCount) {
                    secret.addField();
                    return;
                }
            }
        }
    }

    public void addSecret(int position) {
        addSecret();
    }

    public int getViewType(int position) {

        // Header
        int itemCount = 1;

        if (position < itemCount) {
            return TYPE_HEAD;
        }

        // Header's Fields
        if (fields != null) {
            itemCount += fields.size();
            if (position < itemCount) {
                return TYPE_FIELD;
            }
        }

        // Header's Fields More
        itemCount++;
        if (position < itemCount) {
            return TYPE_FIELD_MORE;
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

                // More
                itemCount++;
                if (position < itemCount) {
                    return TYPE_FIELD_MORE;
                }
            }
        }

        // Secret's More
        itemCount++;
        if (position < itemCount) {
            return TYPE_SECRET_MORE;
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

        // Header's Fields More
        itemCount++;

        if (secrets != null) {
            itemCount += secrets.size() << 1;
            for (Secret secret : secrets) {
                itemCount += secret.getFieldCount();
            }
        }

        // Secret's More
        itemCount++;

        return itemCount;
    }

    @Override
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

        // Header's Fields More
        itemCount++;

        if (secrets != null) {
            for (Secret secret : secrets) {

                // Secret
                itemCount++;

                // Fields
                if (position < itemCount + secret.getFieldCount()) {
                    return secret.getFields().get(position - itemCount);
                }

                // More
                itemCount++;
            }
        }

        return null;
    }

    @Override
    public Secret getSecret(int position) {

        // Header
        int itemCount = 1;

        // Header's Fields
        if (fields != null) {
            itemCount += fields.size();
        }

        // Header's Fields More
        itemCount++;

        if (secrets != null) {
            for (Secret secret : secrets) {

                // Secret
                itemCount++;
                if (position < itemCount) {
                    return secret;
                }

                // Fields & More
                itemCount += secret.getFieldCount() + 1;
            }
        }

        return null;
    }
}
