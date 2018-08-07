package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

public class Menu {
    private String _ID;
    private String _Name;
    private String _Desc;
    private double _Price;
    private String _Type;
    private String _Status;
    private String _StockStatus;

    public Menu() {
    }

    public Menu(String _ID, String _Name, String _Desc, double _Price, String _Type, String _Status, String _StockStatus) {
        this._ID = _ID;
        this._Name = _Name;
        this._Desc = _Desc;
        this._Price = _Price;
        this._Type = _Type;
        this._Status = _Status;
        this._StockStatus = _StockStatus;
    }

    public Menu(String _Name, String _Desc, double _Price, String _Type, String _Status, String _StockStatus) {
        this._Name = _Name;
        this._Desc = _Desc;
        this._Price = _Price;
        this._Type = _Type;
        this._Status = _Status;
        this._StockStatus = _StockStatus;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_Desc() {
        return _Desc;
    }

    public void set_Desc(String _Desc) {
        this._Desc = _Desc;
    }

    public double get_Price() {
        return _Price;
    }

    public void set_Price(double _Price) {
        this._Price = _Price;
    }

    public String get_Type() {
        return _Type;
    }

    public void set_Type(String _Type) {
        this._Type = _Type;
    }

    public String get_Status() {
        return _Status;
    }

    public void set_Status(String _Status) {
        this._Status = _Status;
    }

    public String get_StockStatus() {
        return _StockStatus;
    }

    public void set_StockStatus(String _StockStatus) {
        this._StockStatus = _StockStatus;
    }
}
