package com.mircea.botez.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mircea.botez.model.User;

import java.util.Calendar;

/**
 * Created by botez on 12/28/2017.
 */

public class DBHelper {//extends SQLiteOpenHelper{
//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME = "UserManager.db";
//    private static final String TABLE_USER = "user";
//    private static final String COLUMN_USER_ID = "user_id";
//    private static final String COLUMN_USER_NAME = "user_name";
//    private static final String COLUMN_USER_EMAIL = "user_email";
//    private static final String COLUMN_USER_PASSWORD = "user_password";
//
//    private String create_user_table = "CREATE TABLE " + TABLE_USER + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                                                                           + COLUMN_USER_NAME + " TEXT, "
//                                                                           + COLUMN_USER_EMAIL + " TEXT, "
//                                                                           + COLUMN_USER_PASSWORD + " TEXT) ";
//
//    private static final String TABLE_ACTIVITY = "activity";
//    private static final String COLUMN_ACTIVITY_ID = "activity_id";
//    private static final String COLUMN_FK_USER = "fk_user";
//    private static final String COLUMN_START_TIME = "start_time";
//    private static final String COLUMN_END_TIME = "end_time";
//
//    private String create_activity_table = "CREATE TABLE " + TABLE_ACTIVITY + " (" + COLUMN_ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                                                                                   + COLUMN_START_TIME + " DATETIME, "
//                                                                                   + COLUMN_END_TIME + " DATETIME, "
//                                                                                   + COLUMN_FK_USER + " INTEGER, FOREIGN KEY(" + COLUMN_FK_USER + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + ") )";
//
//    private String drop_user_table = "DROP TABLE IF EXISTS " + TABLE_USER;
//    private String drop_activity_table = "DROP TABLE IF EXISTS " + TABLE_ACTIVITY;
//
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(create_user_table);
//        sqLiteDatabase.execSQL(create_activity_table);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL(drop_user_table);
//        sqLiteDatabase.execSQL(drop_activity_table);
//        onCreate(sqLiteDatabase);
//    }
//
//    public void addUser(User user) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getName());
//        values.put(COLUMN_USER_EMAIL, user.getEmail());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//
//        sqLiteDatabase.insert(TABLE_USER, null, values);
//        sqLiteDatabase.close();
//    }
//
//    public boolean checkUser(String email) {
//        String[] columns = {COLUMN_USER_ID};
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String selection = COLUMN_USER_EMAIL + "=? ";
//        String[] selectionArgs = { email };
//
//        Cursor cursor = sqLiteDatabase.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        sqLiteDatabase.close();
//        if(cursorCount > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean checkUser(String email, String password) {
//        String[] columns = {COLUMN_USER_ID};
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String selection = COLUMN_USER_EMAIL + "=? AND " + COLUMN_USER_PASSWORD + " =? ";
//        String[] selectionArgs = { email, password };
//
//        Cursor cursor = sqLiteDatabase.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        sqLiteDatabase.close();
//        if(cursorCount > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    public void addActivity(int userId) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_FK_USER, userId);
//        values.put(COLUMN_START_TIME, Calendar.getInstance().getTime().toString());
//        //TODO: see if something else is needed
//
//        sqLiteDatabase.insert(TABLE_ACTIVITY, null, values);
//        sqLiteDatabase.close();
//    }
}
