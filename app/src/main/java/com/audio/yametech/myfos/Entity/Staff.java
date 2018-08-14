package com.audio.yametech.myfos.Entity;

/**
 * Created by Aldnoah on 8/7/2018.
 */

public class Staff {
    private String _ID;
    private String _Name;
    private String _Position;
    private String _Gender;
    private String _DateOfBirth;
    private String _IC;
    private String _PhoneNumber;
    private String _Email;
    private String _Status;
    private String _DefaultPass;
    private String _JoinDate;
    private String _LeaveDate;

    public Staff() {
    }

    public Staff(String _ID, String _Name, String _Position, String _Gender, String _DateOfBirth, String _IC, String _PhoneNumber, String _Email, String _Status, String _DefaultPass, String _JoinDate, String _LeaveDate) {
        this._ID = _ID;
        this._Name = _Name;
        this._Position = _Position;
        this._Gender = _Gender;
        this._DateOfBirth = _DateOfBirth;
        this._IC = _IC;
        this._PhoneNumber = _PhoneNumber;
        this._Email = _Email;
        this._Status = _Status;
        this._DefaultPass = _DefaultPass;
        this._JoinDate = _JoinDate;
        this._LeaveDate = _LeaveDate;
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

    public String get_Position() {
        return _Position;
    }

    public void set_Position(String _Position) {
        this._Position = _Position;
    }

    public String get_Gender() {
        return _Gender;
    }

    public void set_Gender(String _Gender) {
        this._Gender = _Gender;
    }

    public String get_DateOfBirth() {
        return _DateOfBirth;
    }

    public void set_DateOfBirth(String _DateOfBirth) {
        this._DateOfBirth = _DateOfBirth;
    }

    public String get_IC() {
        return _IC;
    }

    public void set_IC(String _IC) {
        this._IC = _IC;
    }

    public String get_PhoneNumber() {
        return _PhoneNumber;
    }

    public void set_PhoneNumber(String _PhoneNumber) {
        this._PhoneNumber = _PhoneNumber;
    }

    public String get_Email() {
        return _Email;
    }

    public void set_Email(String _Email) {
        this._Email = _Email;
    }

    public String get_Status() {
        return _Status;
    }

    public void set_Status(String _Status) {
        this._Status = _Status;
    }

    public String get_DefaultPass() {
        return _DefaultPass;
    }

    public void set_DefaultPass(String _DefaultPass) {
        this._DefaultPass = _DefaultPass;
    }

    public String get_JoinDate() {
        return _JoinDate;
    }

    public void set_JoinDate(String _JoinDate) {
        this._JoinDate = _JoinDate;
    }

    public String get_LeaveDate() {
        return _LeaveDate;
    }

    public void set_LeaveDate(String _LeaveDate) {
        this._LeaveDate = _LeaveDate;
    }
}
