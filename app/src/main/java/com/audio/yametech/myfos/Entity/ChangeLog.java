package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

public class ChangeLog {
    private String _ID;
    private String _DateChange;
    private String _SecurityID;

    public ChangeLog() {
    }

    public ChangeLog(String _ID, String _DateChange, String _SecurityID) {
        this._ID = _ID;
        this._DateChange = _DateChange;
        this._SecurityID = _SecurityID;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_DateChange() {
        return _DateChange;
    }

    public void set_DateChange(String _DateChange) {
        this._DateChange = _DateChange;
    }

    public String get_SecurityID() {
        return _SecurityID;
    }

    public void set_SecurityID(String _SecurityID) {
        this._SecurityID = _SecurityID;
    }
}
