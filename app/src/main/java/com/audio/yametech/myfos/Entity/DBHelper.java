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

public class DBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyFOSDB";

    // Contacts table name
    private static final String TABLE_STAFF = "staff";
    private static final String TABLE_SECURITY = "security";
    private static final String TABLE_PLACED_ORDER = "placed_order";
    private static final String TABLE_PAYMENT = "payment";
    private static final String TABLE_ORDER_DETAIL = "order_detail";
    private static final String TABLE_MENU = "menu";
    private static final String TABLE_CHANGE_LOG = "change_log";

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
    private static final String KEY_STAFF_ID = "staff_id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ORDER_DATE = "order_date";
    private static final String KEY_TABLE_NUMBER = "table_number";
    private static final String KEY_ORDER_ID = "order_id";
    private static final String KEY_GRAND_TOTAL = "grand_total";
    private static final String KEY_AMOUNT_PAID = "amount_paid";
    private static final String KEY_CHANGE = "change";
    private static final String KEY_PAYMENT_TIME = "payment_time";
    private static final String KEY_PAYMENT_DATE = "payment_date";
    private static final String KEY_MENU_ID = "menu_id";
    private static final String KEY_QTY = "qty";
    private static final String KEY_SUB_TOTAL = "sub_total";
    private static final String KEY_DESC= "desc";
    private static final String KEY_PRICE = "price";
    private static final String KEY_TYPE = "type";
    private static final String KEY_STOCK_STATUS = "stock_status";
    private static final String KEY_DATE_CHANGE = "date_change";
    private static final String KEY_SECURITY_ID = "security_id";


    public DBHelper(Context context) {
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

        String CREATE_SECURITY_TABLE = "CREATE TABLE " + TABLE_SECURITY + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_STAFF_ID + " TEXT, "
                + KEY_PASSWORD + " TEXT " + ")";

        db.execSQL(CREATE_SECURITY_TABLE);

        String CREATE_PLACED_ORDER_TABLE = "CREATE TABLE " + TABLE_PLACED_ORDER + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_ORDER_DATE + " TEXT, "
                + KEY_TABLE_NUMBER + " TEXT, "
                + KEY_STATUS + " TEXT "+ ")";

        db.execSQL(CREATE_PLACED_ORDER_TABLE);

        String CREATE_PAYMENT_TABLE = "CREATE TABLE " + TABLE_PAYMENT + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_ORDER_ID + " TEXT, "
                + KEY_STAFF_ID + " TEXT, "
                + KEY_GRAND_TOTAL + " DOUBLE, "
                + KEY_AMOUNT_PAID + " DOUBLE, "
                + KEY_CHANGE + " DOUBLE, "
                + KEY_PAYMENT_TIME + " TEXT, "
                + KEY_PAYMENT_DATE + " TEXT " + ")";

        db.execSQL(CREATE_PAYMENT_TABLE);

        String CREATE_ORDER_DETAIL_TABLE = "CREATE TABLE " + TABLE_ORDER_DETAIL + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_MENU_ID + " TEXT, "
                + KEY_ORDER_ID + " TEXT, "
                + KEY_QTY + " INTEGER, "
                + KEY_SUB_TOTAL + " DOUBLE " + ")";

        db.execSQL(CREATE_ORDER_DETAIL_TABLE);

        String CREATE_MENU_TABLE = "CREATE TABLE " + TABLE_MENU + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT, "
                + KEY_DESC + " TEXT, "
                + KEY_PRICE + " DOUBLE, "
                + KEY_TYPE + " TEXT, "
                + KEY_STATUS + " TEXT, "
                + KEY_STOCK_STATUS + " TEXT " + ")";

        db.execSQL(CREATE_MENU_TABLE);

        String CREATE_CHANGE_LOG_TABLE = "CREATE TABLE " + TABLE_CHANGE_LOG + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_DATE_CHANGE + " TEXT, "
                + KEY_SECURITY_ID + " TEXT " + ")";

        db.execSQL(CREATE_CHANGE_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECURITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACED_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHANGE_LOG);
        // Create tables again
        onCreate(db);
    }

    public void addNewStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, staff.get_ID());
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

    public Staff getStaffByID(String ID){
        Staff staff = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_STAFF+" WHERE ID = ?", new String[]{ID});
        if (cursor.moveToFirst()){
            staff = new Staff(
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
        }
        db.close();
        cursor.close();
        return staff;
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
        db.close();
        return securityList;
    }

    public Verification verifyLoginCredential(String username, String password){
        Verification result = new Verification(0);

        List<Security> securityList = getAllSecurityList();
        for(Security security : securityList){
            Log.i("DEBUG",security.get_ID()+security.get_StaffID()+security.get_Password()+username+password);
            if(username.equals(security.get_StaffID())){
                result.set_VerificationStatus(1);
                if(password.equals(security.get_Password())) {
                    result.set_VerificationStatus(2);
                    result.set_Security(security);
                }
            }
        }
        return result;
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
