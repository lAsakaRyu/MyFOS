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

public class OrderDetailDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_ORDER_DETAIL = "order_detail";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_MENU_ID = "menu_id";
    private static final String KEY_ORDER_ID = "order_id";
    private static final String KEY_QTY = "qty";
    private static final String KEY_SUB_TOTAL = "sub_total";

    public OrderDetailDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_ORDER_DETAIL + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_MENU_ID + " TEXT, "
                + KEY_ORDER_ID + " TEXT, "
                + KEY_QTY + " INTEGER, "
                + KEY_SUB_TOTAL + " DOUBLE " + ")";

        db.execSQL(CREATE_STAFF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_DETAIL);
        // Create tables again
        onCreate(db);
    }

    public void addNewOrderDetail(OrderDetail orderDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_MENU_ID, orderDetail.get_MenuID());
        values.put(KEY_ORDER_ID, orderDetail.get_OrderID());
        values.put(KEY_QTY, orderDetail.get_Qty());
        values.put(KEY_SUB_TOTAL, orderDetail.get_SubTotal());

        db.insert(TABLE_ORDER_DETAIL, null, values);
        db.close();
    }

    public boolean updateOrderDetailInfo(OrderDetail orderDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_MENU_ID, orderDetail.get_MenuID());
        args.put(KEY_ORDER_ID, orderDetail.get_OrderID());
        args.put(KEY_QTY, orderDetail.get_Qty());
        args.put(KEY_SUB_TOTAL, orderDetail.get_SubTotal());

        return db.update(TABLE_ORDER_DETAIL, args, KEY_ID + "=" + orderDetail.get_ID(), null) > 0;
    }

    public boolean deleteOrderDetail(int delID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_ORDER_DETAIL, KEY_ID + "=" + delID, null) > 0;
    }

    public List<OrderDetail> getAllOrderDetailList() {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_ORDER_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                OrderDetail orderDetail = new OrderDetail(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getDouble(4)
                );
                // Adding contact to list
                orderDetailList.add(orderDetail);
            } while (cursor.moveToNext());
        }
        return orderDetailList;
    }
}
