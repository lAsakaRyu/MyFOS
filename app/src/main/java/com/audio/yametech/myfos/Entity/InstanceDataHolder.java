package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/8/2018.
 */

public class InstanceDataHolder {
    private DBHelper _DbHelper;
    private Staff _ActiveStaff;
    private String _ActiveTableNo;
    private Menus _ActiveMenuItem;
    private String _SelectedQuantity;

    private static final InstanceDataHolder ourInstance = new InstanceDataHolder();

    public static InstanceDataHolder getInstance() {
        return ourInstance;
    }

    private InstanceDataHolder() {
    }

    public Staff get_ActiveStaff() {
        return _ActiveStaff;
    }

    public void set_ActiveStaff(Staff _ActiveStaff) {
        this._ActiveStaff = _ActiveStaff;
    }

    public DBHelper get_DbHelper() {
        return _DbHelper;
    }

    public void set_DbHelper(DBHelper _DbHelper) {
        this._DbHelper = _DbHelper;
    }

    public String get_ActiveTableNo() {
        return _ActiveTableNo;
    }

    public void set_ActiveTableNo(String _ActiveTableNo) {
        this._ActiveTableNo = _ActiveTableNo;
    }

    public Menus get_ActiveMenuItem() {
        return _ActiveMenuItem;
    }

    public void set_ActiveMenuItem(Menus _ActiveMenuItem) {
        this._ActiveMenuItem = _ActiveMenuItem;
    }

    public String get_SelectedQuantity() {
        return _SelectedQuantity;
    }

    public void set_SelectedQuantity(String _SelectedQuantity) {
        this._SelectedQuantity = _SelectedQuantity;
    }
}
