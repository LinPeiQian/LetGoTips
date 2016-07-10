package com.brianLin.db;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * Created by Jensen on 2016/3/21.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "letgotips";
    /*
     * 版本1
     * */
    private static final int DATABASE_VERSION = 1;

    private static DBHelper mDBHelper;

    private static Context mContext;

    public static DBHelper getInstance(Context context) {
        mContext = context;
        if (mDBHelper == null) {
            mDBHelper = new DBHelper(context);
        }
        return mDBHelper;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(getTag(), "onCreate");
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        createTables(db);
    }

    private void dropTables(SQLiteDatabase db) {
//        db.execSQL("DROP TABLE IF EXISTS " + MainQuotesDb.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + QuotesApiDB.TABLE_NAME);
//		db.execSQL("DROP TABLE IF EXISTS " + QuotesDb.TABLE_NAME);
    }

    private void createTables(SQLiteDatabase db) {
        ThreadInfoDao.createTable(db);
//        db.execSQL(QuotesDb.CREATE_TABLE);  //副行情
//        db.execSQL(WarningDb.CREATE_TABLE);	//行情预警
//        db.execSQL(PersonalDB.CREATE_TABLE); //登陆用户信息
//        db.execSQL(MainQuotesDb.CREATE_TABLE); //主行情
//        db.execSQL(OtherUserDB.CREATE_TABLE); //其他用户信息
//        db.execSQL(QuotesApiDB.CREATE_TABLE); //其他用户信息

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        dropTables(db);
        createTables(db);
    }

    private String getTag() {
        return this.getClass().toString();
    }


}
