package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/8/2018.
 */

public class InstanceDataHolder {
    private DBHelper _DbHelper;
    private Staff _Staff;
    private static final InstanceDataHolder ourInstance = new InstanceDataHolder();

    public static InstanceDataHolder getInstance() {
        return ourInstance;
    }

    private InstanceDataHolder() {
    }

    public Staff get_Staff() {
        return _Staff;
    }

    public void set_Staff(Staff _Staff) {
        this._Staff = _Staff;
    }

    public DBHelper get_DbHelper() {
        return _DbHelper;
    }

    public void set_DbHelper(DBHelper _DbHelper) {
        this._DbHelper = _DbHelper;
    }
}
