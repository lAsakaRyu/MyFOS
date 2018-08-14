package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

public class PlacedOrder {
    private String _ID;
    private String _OrderDate;
    private String _TableNumber;
    private String _Status;

    public PlacedOrder() {
    }

    public PlacedOrder(String _ID, String _OrderDate, String _TableNumber, String _Status) {
        this._ID = _ID;
        this._OrderDate = _OrderDate;
        this._TableNumber = _TableNumber;
        this._Status = _Status;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_OrderDate() {
        return _OrderDate;
    }

    public void set_OrderDate(String _OrderDate) {
        this._OrderDate = _OrderDate;
    }

    public String get_TableNumber() {
        return _TableNumber;
    }

    public void set_TableNumber(String _TableNumber) {
        this._TableNumber = _TableNumber;
    }

    public String get_Status() {
        return _Status;
    }

    public void set_Status(String _Status) {
        this._Status = _Status;
    }
}

