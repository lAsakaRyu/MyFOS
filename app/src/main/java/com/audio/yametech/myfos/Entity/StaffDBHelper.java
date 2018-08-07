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

public class StaffDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_STAFF = "staff";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_POSITION = "position";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_IC = "ic";
    private static final String KEY_PHONE_NO = "phone_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_STATUS = "status";
    private static final String KEY_DEFAULT_PASS = "default_pass";
    private static final String KEY_JOIN_DATE = "join_date";
    private static final String KEY_LEAVE_DATE = "leave_date";

    public StaffDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_STAFF + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT, "
                + KEY_POSITION + " TEXT, "
                + KEY_GENDER + " TEXT, "
                + KEY_DOB + " TEXT, "
                + KEY_IC + " TEXT, "
                + KEY_PHONE_NO + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_STATUS + " TEXT, "
                + KEY_DEFAULT_PASS + " TEXT, "
                + KEY_JOIN_DATE + " TEXT, "
                + KEY_LEAVE_DATE + " TEXT " + ")";

        db.execSQL(CREATE_STAFF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
        // Create tables again
        onCreate(db);
    }

    public void addNewStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, staff.get_Name());
        values.put(KEY_POSITION, staff.get_Position());
        values.put(KEY_GENDER, staff.get_Gender());
        values.put(KEY_DOB, staff.get_DateOfBirth());
        values.put(KEY_IC, staff.get_IC());
        values.put(KEY_PHONE_NO, staff.get_PhoneNumber());
        values.put(KEY_EMAIL, staff.get_Email());
        values.put(KEY_STATUS, staff.get_Status());
        values.put(KEY_DEFAULT_PASS, staff.get_DefaultPass());
        values.put(KEY_JOIN_DATE, staff.get_JoinDate());
        values.put(KEY_LEAVE_DATE, staff.get_LeaveDate());

        db.insert(TABLE_STAFF, null, values);
        db.close();
    }


    public boolean updateStaffInfo(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_NAME, staff.get_Name());
        args.put(KEY_POSITION, staff.get_Position());
        args.put(KEY_GENDER, staff.get_Gender());
        args.put(KEY_DOB, staff.get_DateOfBirth());
        args.put(KEY_IC, staff.get_IC());
        args.put(KEY_PHONE_NO, staff.get_PhoneNumber());
        args.put(KEY_EMAIL, staff.get_Email());
        args.put(KEY_STATUS, staff.get_Status());
        args.put(KEY_DEFAULT_PASS, staff.get_DefaultPass());
        args.put(KEY_JOIN_DATE, staff.get_JoinDate());
        args.put(KEY_LEAVE_DATE, staff.get_LeaveDate());

        return db.update(TABLE_STAFF, args, KEY_ID + "=" + staff.get_ID(), null) > 0;
    }


    public boolean deleteStaff(int delID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_STAFF, KEY_ID + "=" + delID, null) > 0;
    }

    public List<Staff> getAllStaffList() {
        List<Staff> staffList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_STAFF;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11)
                );

                // Adding contact to list
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        return staffList;
    }
}
