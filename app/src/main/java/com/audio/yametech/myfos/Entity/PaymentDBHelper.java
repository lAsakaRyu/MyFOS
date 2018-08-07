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

public class PaymentDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_PAYMENT = "payment";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ORDER_ID = "order_id";
    private static final String KEY_STAFF_ID = "staff_id";
    private static final String KEY_GRAND_TOTAL = "grand_total";
    private static final String KEY_AMOUNT_PAID = "amount_paid";
    private static final String KEY_CHANGE = "change";
    private static final String KEY_PAYMENT_TIME = "payment_time";
    private static final String KEY_PAYMENT_DATE = "payment_date";


    public PaymentDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_PAYMENT + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_ORDER_ID + " TEXT, "
                + KEY_STAFF_ID + " TEXT, "
                + KEY_GRAND_TOTAL + " DOUBLE, "
                + KEY_AMOUNT_PAID + " DOUBLE, "
                + KEY_CHANGE + " DOUBLE, "
                + KEY_PAYMENT_TIME + " TEXT, "
                + KEY_PAYMENT_DATE + " TEXT, " + ")";

        db.execSQL(CREATE_STAFF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        // Create tables again
        onCreate(db);
    }

    public void addNewPayment(Payment payment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ORDER_ID, payment.get_OrderID());
        values.put(KEY_STAFF_ID, payment.get_StaffID());
        values.put(KEY_GRAND_TOTAL, payment.get_GrandTotal());
        values.put(KEY_AMOUNT_PAID, payment.get_AmountPaid());
        values.put(KEY_CHANGE, payment.get_Change());
        values.put(KEY_PAYMENT_TIME, payment.get_PaymentTime());
        values.put(KEY_PAYMENT_DATE, payment.get_PaymentDate());

        db.insert(TABLE_PAYMENT, null, values);
        db.close();
    }

    public boolean updatePaymentInfo(Payment payment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_ORDER_ID, payment.get_OrderID());
        args.put(KEY_STAFF_ID, payment.get_StaffID());
        args.put(KEY_GRAND_TOTAL, payment.get_GrandTotal());
        args.put(KEY_AMOUNT_PAID, payment.get_AmountPaid());
        args.put(KEY_CHANGE, payment.get_Change());
        args.put(KEY_PAYMENT_TIME, payment.get_PaymentTime());
        args.put(KEY_PAYMENT_DATE, payment.get_PaymentDate());

        return db.update(TABLE_PAYMENT, args, KEY_ID + "=" + payment.get_ID(), null) > 0;
    }

    public boolean deletePayment(int delID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_PAYMENT, KEY_ID + "=" + delID, null) > 0;
    }

    public List<Payment> getAllPaymentList() {
        List<Payment> paymentList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_PAYMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Payment payment = new Payment(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getString(6),
                        cursor.getString(7)
                );

                // Adding contact to list
                paymentList.add(payment);
            } while (cursor.moveToNext());
        }
        return paymentList;
    }
}
