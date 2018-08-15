package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/16/2018.
 */

public class ExtendedChangeLog extends ChangeLog {
    private Staff staff;

    public ExtendedChangeLog(String _ID, String _DateChange, String _SecurityID, Staff staff) {
        super(_ID, _DateChange, _SecurityID);
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
