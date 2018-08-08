package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/8/2018.
 */

public class InstanceDataHolder {
    private DBHelper _DbHelper;
    private Staff _ActiveStaff;
    private int _ActiveTableNo;

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

    public int get_ActiveTableNo() {
        return _ActiveTableNo;
    }

    public void set_ActiveTableNo(int _ActiveTableNo) {
        this._ActiveTableNo = _ActiveTableNo;
    }
}
