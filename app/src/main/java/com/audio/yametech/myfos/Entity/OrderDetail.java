package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

public class OrderDetail {
    private String _ID;
    private String _MenuID;
    private String _OrderID;
    private int _Qty;
    private double _SubTotal;

    public OrderDetail() {
    }

    public OrderDetail(String _ID, String _MenuID, String _OrderID, int _Qty, double _SubTotal) {
        this._ID = _ID;
        this._MenuID = _MenuID;
        this._OrderID = _OrderID;
        this._Qty = _Qty;
        this._SubTotal = _SubTotal;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_MenuID() {
        return _MenuID;
    }

    public void set_MenuID(String _MenuID) {
        this._MenuID = _MenuID;
    }

    public String get_OrderID() {
        return _OrderID;
    }

    public void set_OrderID(String _OrderID) {
        this._OrderID = _OrderID;
    }

    public int get_Qty() {
        return _Qty;
    }

    public void set_Qty(int _Qty) {
        this._Qty = _Qty;
    }

    public double get_SubTotal() {
        return _SubTotal;
    }

    public void set_SubTotal(double _SubTotal) {
        this._SubTotal = _SubTotal;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "_ID='" + _ID + '\'' +
                ", _MenuID='" + _MenuID + '\'' +
                ", _OrderID='" + _OrderID + '\'' +
                ", _Qty=" + _Qty +
                ", _SubTotal=" + _SubTotal +
                '}';
    }
}
