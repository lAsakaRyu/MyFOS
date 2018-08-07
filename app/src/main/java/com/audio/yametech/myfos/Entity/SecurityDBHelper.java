package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SecurityDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_SECURITY = "security";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_STAFF_ID = "staff_id";
    private static final String KEY_PASSWORD = "password";

    public SecurityDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SECURITY_TABLE = "CREATE TABLE " + TABLE_SECURITY + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_STAFF_ID + " TEXT, "
                + KEY_PASSWORD + " TEXT " + ")";

        db.execSQL(CREATE_SECURITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECURITY);
        // Create tables again
        onCreate(db);
    }

    public void addNewSecurity(Security security) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, security.get_ID());
        values.put(KEY_STAFF_ID, security.get_StaffID());
        values.put(KEY_PASSWORD, security.get_Password());

        db.insert(TABLE_SECURITY, null, values);
        db.close();
    }


    public boolean updateSecurityInfo(Security security) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_STAFF_ID, security.get_StaffID());
        args.put(KEY_PASSWORD, security.get_Password());

        return db.update(TABLE_SECURITY, args, KEY_ID + "=" + security.get_ID(), null) > 0;
    }


    public boolean deleteSecurity(int delID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_SECURITY, KEY_ID + "=" + delID, null) > 0;
    }

    public List<Security> getAllSecurityList() {
        List<Security> securityList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_SECURITY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Security security = new Security(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );

                // Adding contact to list
                securityList.add(security);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return securityList;
    }

    public int verifyLoginCredential(String username, String password){
        int result = 0;

        List<Security> securityList = getAllSecurityList();
        for(Security security : securityList){
            Log.i("DEBUG",security.get_ID()+security.get_StaffID()+security.get_Password()+username+password);
            if(username.equals(security.get_StaffID())){
                result = 1;
                if(password.equals(security.get_Password()))
                    result = 2;
            }
        }
        return result;
    }
}
