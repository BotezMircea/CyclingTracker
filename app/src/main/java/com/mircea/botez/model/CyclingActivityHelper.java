package com.mircea.botez.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

/**
 * Created by botez on 2/22/2018.
 */

public class CyclingActivityHelper extends AbstractHelper {

    public void saveCyclingActivity(CyclingActivity cyclingActivity, Context context) {
        getDao(context).save(cyclingActivity);
    }

    public List<CyclingActivity> getCyclingActivities(Context context) {
        return getDao(context).loadAll();
    }

    public void deleteCyclingActivity(Context context, long id) {
        getDao(context).deleteByKey(id);
    }

    @Override
    public AbstractDao getDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "cycling_activities", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getCyclingActivityDao();
    }
}
