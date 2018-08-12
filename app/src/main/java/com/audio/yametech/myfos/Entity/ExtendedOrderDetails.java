package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/12/2018.
 */

public class ExtendedOrderDetails extends OrderDetail {
    private String _Name;
    private double _Price;

    public ExtendedOrderDetails(String _ID, String _MenuID, String _OrderID, int _Qty, double _SubTotal, String _Name, double _Price) {
        super(_ID, _MenuID, _OrderID, _Qty, _SubTotal);
        this._Name = _Name;
        this._Price = _Price;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public double get_Price() {
        return _Price;
    }

    public void set_Price(double _Price) {
        this._Price = _Price;
    }

    @Override
    public String toString() {
        return super.toString()+" ExtendedOrderDetails{" +
                "_Name='" + _Name + '\'' +
                ", _Price=" + _Price +
                '}';
    }
}
