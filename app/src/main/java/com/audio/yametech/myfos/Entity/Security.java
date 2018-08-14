package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

public class Security {
    private String _ID;
    private String _StaffID;
    private String _Password;

    public Security() {
    }

    public Security(String _ID, String _StaffID, String _Password) {
        this._ID = _ID;
        this._StaffID = _StaffID;
        this._Password = _Password;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_StaffID() {
        return _StaffID;
    }

    public void set_StaffID(String _StaffID) {
        this._StaffID = _StaffID;
    }

    public String get_Password() {
        return _Password;
    }

    public void set_Password(String _Password) {
        this._Password = _Password;
    }
}
