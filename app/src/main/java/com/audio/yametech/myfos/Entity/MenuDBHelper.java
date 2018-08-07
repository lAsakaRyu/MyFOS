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

public class MenuDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_MENU = "menu";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC= "desc";
    private static final String KEY_PRICE = "price";
    private static final String KEY_TYPE = "type";
    private static final String KEY_STATUS = "status";
    private static final String KEY_STOCK_STATUS = "stock_status";

    public MenuDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_MENU + "("
                + KEY_ID + " TEXT, PRIMARY KEY,"
                + KEY_NAME + " TEXT, "
                + KEY_DESC + " TEXT, "
                + KEY_PRICE + " DOUBLE, "
                + KEY_TYPE + " TEXT, "
                + KEY_STATUS + " TEXT, "
                + KEY_STOCK_STATUS + " TEXT " + ")";

        db.execSQL(CREATE_STAFF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        // Create tables again
        onCreate(db);
    }

    public void addNewMenu(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, menu.get_Name());
        values.put(KEY_DESC, menu.get_Desc());
        values.put(KEY_PRICE, menu.get_Price());
        values.put(KEY_TYPE, menu.get_Type());
        values.put(KEY_STATUS, menu.get_Status());
        values.put(KEY_STOCK_STATUS, menu.get_StockStatus());

        db.insert(TABLE_MENU, null, values);
        db.close();
    }


    public boolean updateMenuInfo(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_NAME, menu.get_Name());
        args.put(KEY_DESC, menu.get_Desc());
        args.put(KEY_PRICE, menu.get_Price());
        args.put(KEY_TYPE, menu.get_Type());
        args.put(KEY_STATUS, menu.get_Status());
        args.put(KEY_STOCK_STATUS, menu.get_StockStatus());

        return db.update(TABLE_MENU, args, KEY_ID + "=" + menu.get_ID(), null) > 0;
    }


    public boolean deleteMenu(int delID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_MENU, KEY_ID + "=" + delID, null) > 0;
    }

    public List<Menu> getAllMenuList() {
        List<Menu> menuList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_MENU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Menu menu = new Menu(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );

                // Adding contact to list
                menuList.add(menu);
            } while (cursor.moveToNext());
        }
        return menuList;
    }
}
