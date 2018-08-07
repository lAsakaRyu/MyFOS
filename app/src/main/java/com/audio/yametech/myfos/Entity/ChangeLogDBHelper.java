package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ChangeLogDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_CHANGE_LOG = "change_log";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE_CHANGE = "date_change";
    private static final String KEY_SECURITY_ID = "security_id";

    public ChangeLogDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_CHANGE_LOG + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_DATE_CHANGE + " TEXT, "
                + KEY_SECURITY_ID + " TEXT " + ")";

        db.execSQL(CREATE_STAFF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHANGE_LOG);
        // Create tables again
        onCreate(db);
    }

    public void addNewChangeLog(ChangeLog changeLog) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_DATE_CHANGE, changeLog.get_DateChange());
        values.put(KEY_SECURITY_ID, changeLog.get_SecurityID());

        db.insert(TABLE_CHANGE_LOG, null, values);
        db.close();
    }


    public boolean updateChangeLogInfo(ChangeLog changeLog) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_DATE_CHANGE, changeLog.get_DateChange());
        args.put(KEY_SECURITY_ID, changeLog.get_SecurityID());

        return db.update(TABLE_CHANGE_LOG, args, KEY_ID + "=" + changeLog.get_ID(), null) > 0;
    }


    public boolean deleteChangeLog(int delID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_CHANGE_LOG, KEY_ID + "=" + delID, null) > 0;
    }

    public List<ChangeLog> getAllChangeLogList() {
        List<ChangeLog> changeLogList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CHANGE_LOG;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ChangeLog changeLog = new ChangeLog(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );

                // Adding contact to list
                changeLogList.add(changeLog);
            } while (cursor.moveToNext());
        }
        return changeLogList;
    }
}
