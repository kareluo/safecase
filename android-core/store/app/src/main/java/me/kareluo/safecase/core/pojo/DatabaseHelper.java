package me.kareluo.safecase.core.pojo;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by felix on 2017/12/3 下午9:42.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper implements DatabaseConfig {

    private static DatabaseHelper mInstance;

    private DatabaseHelper() {
        super(Provider.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance() {
        if (mInstance == null) {
            synchronized (DatabaseHelper.class) {
                if (mInstance == null) {
                    mInstance = new DatabaseHelper();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Field.class);
            TableUtils.createTable(connectionSource, Secret.class);
            TableUtils.createTable(connectionSource, Secrets.class);
        } catch (SQLException ignore) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
