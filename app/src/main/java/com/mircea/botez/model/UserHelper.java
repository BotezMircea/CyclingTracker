package com.mircea.botez.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mircea.botez.Exception.CustomException;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

/**
 * Created by botez on 2/22/2018.
 */

public class UserHelper extends AbstractHelper {

    public boolean checkUserByEmailAndPassword(String email, String password, Context context) throws CustomException {
        List<User> users = getDao(context).queryBuilder()
                .where(UserDao.Properties.Email.eq(email), UserDao.Properties.Password.eq(password))
                .list();
        if(users.size() > 1) {
            throw new CustomException("There are multiple users with the same email and password");
        }
        if(users.size() == 1) {
            return true;
        }
        return false;
    }

    public boolean checkUserByEmail(String email, Context context) throws CustomException {
        List<User> users = getDao(context).queryBuilder()
                .where(UserDao.Properties.Email.eq(email))
                .list();
        if(users.size() > 1) {
            throw new CustomException("There are multiple users with the same email and password");
        }
        if(users.size() == 1) {
            return true;
        }
        return false;
    }

    public void saveUser(User user, Context context) {
        getDao(context).save(user);
    }

    @Override
    public AbstractDao getDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "cycling_activities", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getUserDao();
    }
}
