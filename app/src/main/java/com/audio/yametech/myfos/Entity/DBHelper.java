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

    private SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
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

    public String getNewID(String table, String prefix){
        String newID = null;

        String queryString = "SELECT MAX(id) FROM "+ table;
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()&&cursor.getString(0)!=null){
            String id = (cursor.getString(0)).substring(1);
            int num = Integer.parseInt(id);
            num++;
            newID = prefix+String.valueOf(num);
        }
        else{
            newID = prefix+"1001";
        }

        return newID;
    }

    public void addNewStaff(Staff staff) {
        

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
        
    }

    public boolean updateStaffInfo(Staff staff) {
        

        ContentValues args = new ContentValues();

        args.put(KEY_NAME, staff.get_Name());
        args.put(KEY_POSITION, staff.get_Position());
        args.put(KEY_GENDER, staff.get_Gender());
        args.put(KEY_PHONE_NO, staff.get_PhoneNumber());
        args.put(KEY_EMAIL, staff.get_Email());
        args.put(KEY_STATUS, staff.get_Status());
        args.put(KEY_LEAVE_DATE, staff.get_LeaveDate());

        return db.update(TABLE_STAFF, args, KEY_ID + "= ?", new String[]{staff.get_ID()}) > 0;
    }

    public boolean deleteStaff(String delID){
        

        return db.delete(TABLE_STAFF, KEY_ID + "= ?", new String[]{delID}) > 0;
    }

    public List<Staff> getAllStaffList() {
        List<Staff> staffList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_STAFF;

        
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
        
        cursor.close();
        return staff;
    }


    public void addNewSecurity(Security security) {
        

        ContentValues values = new ContentValues();

        values.put(KEY_ID, security.get_ID());
        values.put(KEY_STAFF_ID, security.get_StaffID());
        values.put(KEY_PASSWORD, security.get_Password());

        db.insert(TABLE_SECURITY, null, values);
        
    }

    public boolean updateSecurityInfo(Security security) {
        ContentValues args = new ContentValues();
        args.put(KEY_PASSWORD, security.get_Password());
        return db.update(TABLE_SECURITY, args, KEY_ID + "= ?", new String[]{security.get_ID()}) > 0;
    }

    public boolean deleteSecurity(String delID){
        

        return db.delete(TABLE_SECURITY, KEY_ID + "= ?", new String[]{delID}) > 0;
    }

    public boolean deleteSecuritybyStaffID(String delID){
        return db.delete(TABLE_SECURITY, KEY_STAFF_ID + "= ?", new String[]{delID}) > 0;
    }

    public List<Security> getAllSecurityList() {
        List<Security> securityList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_SECURITY;

        
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

    public List<Security> getAllWorkingSecurityList() {
        List<Security> securityList = new ArrayList<>();

        String selectQuery = "SELECT  X.* FROM security X, staff S where S.id = X.staff_id AND S.status = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"Working"});

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

    public Verification verifyLoginCredential(String username, String password){
        Verification result = new Verification();

        List<Security> securityList = getAllWorkingSecurityList();
        for(Security security : securityList){
            Log.i("DEBUG",security.toString());
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

    public String getPlacedOrderID(String tableNumber){
        String placedOrderID = null;

        String queryString = "SELECT id FROM "+TABLE_PLACED_ORDER+" WHERE table_number = ? AND status = ?";
        Cursor cursor = db.rawQuery(queryString,new String[]{tableNumber,"active"});

        if(cursor.moveToFirst())
            placedOrderID = cursor.getString(0);

        return placedOrderID;
    }

    public void addNewPlacedOrder(PlacedOrder placedOrder) {
        

        ContentValues values = new ContentValues();

        values.put(KEY_ID, placedOrder.get_ID());
        values.put(KEY_ORDER_DATE, placedOrder.get_OrderDate());
        values.put(KEY_TABLE_NUMBER, placedOrder.get_TableNumber());
        values.put(KEY_STATUS, placedOrder.get_Status());
        db.insert(TABLE_PLACED_ORDER, null, values);
        
    }

    public boolean updatePlacedOrderInfo(PlacedOrder placedOrder) {
        

        ContentValues args = new ContentValues();

        args.put(KEY_ORDER_DATE, placedOrder.get_OrderDate());
        args.put(KEY_TABLE_NUMBER, placedOrder.get_TableNumber());
        args.put(KEY_STATUS, placedOrder.get_Status());

        return db.update(TABLE_PLACED_ORDER, args, KEY_ID + "=" + placedOrder.get_ID(), null) > 0;
    }

    public boolean deletePlacedOrder(String delID){
        

        return db.delete(TABLE_PLACED_ORDER, KEY_ID + "= ?", new String[]{delID}) > 0;
    }

    public List<PlacedOrder> getAllPlacedOrderList() {
        List<PlacedOrder> placedOrderList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_PLACED_ORDER;

        
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PlacedOrder placedOrder = new PlacedOrder(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );

                // Adding contact to list
                placedOrderList.add(placedOrder);
            } while (cursor.moveToNext());
        }
        return placedOrderList;
    }

    public List<String> getAllActiveTableList() {
        List<String> tableNumberList = new ArrayList<>();

        String selectQuery = "SELECT table_number FROM " + TABLE_PLACED_ORDER + " WHERE status = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"active"});

        if (cursor.moveToFirst()) {
            do {
                tableNumberList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return tableNumberList;
    }

    public void updatePaidOrder(String orderid){
        ContentValues args = new ContentValues();
        args.put(KEY_STATUS, "paid");
        db.update(TABLE_PLACED_ORDER, args, KEY_ID + " = ?", new String[]{orderid});
    }


    public void addNewPayment(Payment payment) {
        

        ContentValues values = new ContentValues();

        values.put(KEY_ID, payment.get_ID());
        values.put(KEY_ORDER_ID, payment.get_OrderID());
        values.put(KEY_STAFF_ID, payment.get_StaffID());
        values.put(KEY_GRAND_TOTAL, payment.get_GrandTotal());
        values.put(KEY_AMOUNT_PAID, payment.get_AmountPaid());
        values.put(KEY_CHANGE, payment.get_Change());
        values.put(KEY_PAYMENT_TIME, payment.get_PaymentTime());
        values.put(KEY_PAYMENT_DATE, payment.get_PaymentDate());

        db.insert(TABLE_PAYMENT, null, values);
        
    }

    public boolean updatePaymentInfo(Payment payment) {
        

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

    public boolean deletePayment(String delID){
        

        return db.delete(TABLE_PAYMENT, KEY_ID + "= ?", new String[]{delID}) > 0;
    }

    public List<Payment> getAllPaymentList() {
        List<Payment> paymentList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_PAYMENT + " ORDER BY "+KEY_PAYMENT_DATE + " , "+KEY_PAYMENT_TIME;

        
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
        

        ContentValues values = new ContentValues();

        values.put(KEY_ID,orderDetail.get_ID());
        values.put(KEY_MENU_ID, orderDetail.get_MenuID());
        values.put(KEY_ORDER_ID, orderDetail.get_OrderID());
        values.put(KEY_QTY, orderDetail.get_Qty());
        values.put(KEY_SUB_TOTAL, orderDetail.get_SubTotal());

        db.insert(TABLE_ORDER_DETAIL, null, values);
        
    }

    public boolean updateOrderDetailInfo(OrderDetail orderDetail) {
        

        ContentValues args = new ContentValues();

        args.put(KEY_MENU_ID, orderDetail.get_MenuID());
        args.put(KEY_ORDER_ID, orderDetail.get_OrderID());
        args.put(KEY_QTY, orderDetail.get_Qty());
        args.put(KEY_SUB_TOTAL, orderDetail.get_SubTotal());

        return db.update(TABLE_ORDER_DETAIL, args, KEY_ID + "=" + orderDetail.get_ID(), null) > 0;
    }

    public boolean deleteOrderDetail(String delID){
        

        return db.delete(TABLE_ORDER_DETAIL, KEY_ID + "= ?", new String[]{delID}) > 0;
    }

    public List<OrderDetail> getAllOrderDetailList() {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_ORDER_DETAIL;

        
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

    public List<ExtendedOrderDetails> getOrderDetailsByTable(String tableNumber){
        List<ExtendedOrderDetails> orderDetails = new ArrayList<>();

        String selectQuery = "SELECT O.*, M.name, M.price FROM order_detail O, placed_order P, menu M WHERE O.menu_id = M.id AND O.order_id = P.id AND P.status = ? AND P.table_number = ?";

        Cursor cursor = db.rawQuery(selectQuery,new String[]{"active",tableNumber});
        if (cursor.moveToFirst()) {
            do {
                ExtendedOrderDetails orderDetail = new ExtendedOrderDetails(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getString(5),
                        cursor.getDouble(6)
                );
                orderDetails.add(orderDetail);
            } while (cursor.moveToNext());
        }
        return orderDetails;
    }

    public List<ExtendedOrderDetails> getOrderDetailsByID(String id){
        List<ExtendedOrderDetails> orderDetails = new ArrayList<>();

        String selectQuery = "SELECT O.*, M.name, M.price FROM order_detail O, placed_order P, menu M WHERE O.menu_id = M.id AND O.order_id = P.id AND P.id = ?";

        Cursor cursor = db.rawQuery(selectQuery,new String[]{id});
        if (cursor.moveToFirst()) {
            do {
                ExtendedOrderDetails orderDetail = new ExtendedOrderDetails(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getString(5),
                        cursor.getDouble(6)
                );
                orderDetails.add(orderDetail);
            } while (cursor.moveToNext());
        }
        return orderDetails;
    }


    public void addNewMenu(Menus menus) {
        

        ContentValues values = new ContentValues();
        values.put(KEY_ID, menus.get_ID());
        values.put(KEY_NAME, menus.get_Name());
        values.put(KEY_DESC, menus.get_Desc());
        values.put(KEY_PRICE, menus.get_Price());
        values.put(KEY_TYPE, menus.get_Type());
        values.put(KEY_STATUS, menus.get_Status());
        values.put(KEY_STOCK_STATUS, menus.get_StockStatus());

        db.insert(TABLE_MENU, null, values);
        
    }

    public boolean updateMenuInfo(Menus menus) {
        

        ContentValues args = new ContentValues();

        args.put(KEY_NAME, menus.get_Name());
        args.put(KEY_DESC, menus.get_Desc());
        args.put(KEY_PRICE, menus.get_Price());
        args.put(KEY_TYPE, menus.get_Type());
        args.put(KEY_STATUS, menus.get_Status());
        args.put(KEY_STOCK_STATUS, menus.get_StockStatus());

        return db.update(TABLE_MENU, args, KEY_ID + "= ?", new String[]{menus.get_ID()}) > 0;
    }

    public boolean deleteMenu(String delID){
        return db.delete(TABLE_MENU, KEY_ID + "= ?", new String[]{delID}) > 0;
    }

    public List<Menus> getAllMenuList() {
        List<Menus> menusList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_MENU;

        
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Menus menus = new Menus(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );

                // Adding contact to list
                menusList.add(menus);
            } while (cursor.moveToNext());
        }
        return menusList;
    }

    public String[] getMenuIDs(){
        String selectQuery = "SELECT ID FROM " + TABLE_MENU;
        
        Cursor cursor = db.rawQuery(selectQuery,null);
        List<String> strings = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                strings.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return strings.toArray(new String[0]);
    }

    public Menus getMenu(String ID){
        Menus menus = null;
        String selectQuery = "SELECT * FROM " + TABLE_MENU +" WHERE ID = ?";
        Cursor cursor = db.rawQuery(selectQuery,new String[]{ID});
        if(cursor.moveToFirst()){
            menus = new Menus(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }
        return menus;
    }


    public void addNewChangeLog(ChangeLog changeLog) {
        

        ContentValues values = new ContentValues();

        values.put(KEY_DATE_CHANGE, changeLog.get_DateChange());
        values.put(KEY_SECURITY_ID, changeLog.get_SecurityID());

        db.insert(TABLE_CHANGE_LOG, null, values);
        
    }

    public boolean updateChangeLogInfo(ChangeLog changeLog) {
        

        ContentValues args = new ContentValues();

        args.put(KEY_DATE_CHANGE, changeLog.get_DateChange());
        args.put(KEY_SECURITY_ID, changeLog.get_SecurityID());

        return db.update(TABLE_CHANGE_LOG, args, KEY_ID + "=" + changeLog.get_ID(), null) > 0;
    }

    public boolean deleteChangeLog(String delID){
        

        return db.delete(TABLE_CHANGE_LOG, KEY_ID + "= ?", new String[]{delID}) > 0;
    }

    public List<ChangeLog> getAllChangeLogList() {
        List<ChangeLog> changeLogList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CHANGE_LOG;

        
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
