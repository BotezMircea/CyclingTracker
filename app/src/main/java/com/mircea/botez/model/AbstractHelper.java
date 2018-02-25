package com.mircea.botez.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by botez on 2/22/2018.
 */

public abstract class AbstractHelper {
    private static final String DB_NAME = "cycling_activities";
    DaoSession daoSession;

    public AbstractDao getDao(Context context) {

        return null;
    }
}
