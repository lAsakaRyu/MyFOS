package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/8/2018.
 */

public class Verification extends Security {
    private int _VerificationStatus;

    public Verification() {
    }

    public Verification(int _VerificationStatus) {
        this._VerificationStatus = _VerificationStatus;
    }

    public int get_VerificationStatus() {
        return _VerificationStatus;
    }

    public void set_VerificationStatus(int _VerificationStatus) {
        this._VerificationStatus = _VerificationStatus;
    }

    public void set_Security(Security security){
        super.set_ID(security.get_ID());
        super.set_StaffID(security.get_StaffID());
        super.set_Password(security.get_Password());
    }
}
