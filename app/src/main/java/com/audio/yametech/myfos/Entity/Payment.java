package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

public class Payment {
    private String _ID;
    private String _OrderID;
    private String _StaffID;
    private double _GrandTotal;
    private double _AmountPaid;
    private double _Change;
    private String _PaymentTime;
    private String _PaymentDate;

    public Payment() {
    }

    public Payment(String _ID, String _OrderID, String _StaffID, double _GrandTotal, double _AmountPaid, double _Change, String _PaymentTime, String _PaymentDate) {
        this._ID = _ID;
        this._OrderID = _OrderID;
        this._StaffID = _StaffID;
        this._GrandTotal = _GrandTotal;
        this._AmountPaid = _AmountPaid;
        this._Change = _Change;
        this._PaymentTime = _PaymentTime;
        this._PaymentDate = _PaymentDate;
    }

    public Payment(String _OrderID, String _StaffID, double _GrandTotal, double _AmountPaid, double _Change, String _PaymentTime, String _PaymentDate) {
        this._OrderID = _OrderID;
        this._StaffID = _StaffID;
        this._GrandTotal = _GrandTotal;
        this._AmountPaid = _AmountPaid;
        this._Change = _Change;
        this._PaymentTime = _PaymentTime;
        this._PaymentDate = _PaymentDate;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_OrderID() {
        return _OrderID;
    }

    public void set_OrderID(String _OrderID) {
        this._OrderID = _OrderID;
    }

    public String get_StaffID() {
        return _StaffID;
    }

    public void set_StaffID(String _StaffID) {
        this._StaffID = _StaffID;
    }

    public double get_GrandTotal() {
        return _GrandTotal;
    }

    public void set_GrandTotal(double _GrandTotal) {
        this._GrandTotal = _GrandTotal;
    }

    public double get_AmountPaid() {
        return _AmountPaid;
    }

    public void set_AmountPaid(double _AmountPaid) {
        this._AmountPaid = _AmountPaid;
    }

    public double get_Change() {
        return _Change;
    }

    public void set_Change(double _Change) {
        this._Change = _Change;
    }

    public String get_PaymentTime() {
        return _PaymentTime;
    }

    public void set_PaymentTime(String _PaymentTime) {
        this._PaymentTime = _PaymentTime;
    }

    public String get_PaymentDate() {
        return _PaymentDate;
    }

    public void set_PaymentDate(String _PaymentDate) {
        this._PaymentDate = _PaymentDate;
    }

    public boolean verifyPaymentAmount(){
        return this._AmountPaid-this._GrandTotal>=0;
    }
}
