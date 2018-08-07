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

public class PlacedOrderDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_PLACED_ORDER = "placed_order";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ORDER_DATE = "order_date";
    private static final String KEY_TABLE_NUMBER = "table_number";
    private static final String KEY_STATUS = "status";

    public PlacedOrderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SECURITY_TABLE = "CREATE TABLE " + TABLE_PLACED_ORDER + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_ORDER_DATE + " TEXT, "
                + KEY_TABLE_NUMBER + " TEXT, "
                + KEY_STATUS + " TEXT, "+ ")";

        db.execSQL(CREATE_SECURITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACED_ORDER);
        // Create tables again
        onCreate(db);
    }

    public void addNewPlacedOrder(PlacedOrder placedOrder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ORDER_DATE, placedOrder.get_OrderDate());
        values.put(KEY_TABLE_NUMBER, placedOrder.get_TableNumber());
        values.put(KEY_STATUS, placedOrder.get_Status());

        db.insert(TABLE_PLACED_ORDER, null, values);
        db.close();
    }


    public boolean updatePlacedOrderInfo(PlacedOrder placedOrder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_ORDER_DATE, placedOrder.get_OrderDate());
        args.put(KEY_TABLE_NUMBER, placedOrder.get_TableNumber());
        args.put(KEY_STATUS, placedOrder.get_Status());

        return db.update(TABLE_PLACED_ORDER, args, KEY_ID + "=" + placedOrder.get_ID(), null) > 0;
    }


    public boolean deletePlacedOrder(int delID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_PLACED_ORDER, KEY_ID + "=" + delID, null) > 0;
    }

    public List<PlacedOrder> getAllPlacedOrderList() {
        List<PlacedOrder> placedOrderList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_PLACED_ORDER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PlacedOrder placedOrder = new PlacedOrder(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3)
                );

                // Adding contact to list
                placedOrderList.add(placedOrder);
            } while (cursor.moveToNext());
        }
        return placedOrderList;
    }
}
