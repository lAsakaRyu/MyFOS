package com.audio.yametech.myfos;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Staff;

import java.util.List;

public class StaffMaintenanceActivity extends AppCompatActivity {
    private ListView staffDetailListView;
    private List<Staff> staffList;
    private Staff selectedStaff;
    private Dialog dialog;
    private View view;
    private EditText updateIDEditText;
    private EditText updateNameEditText;
    private EditText updateContactEditText;
    private EditText updateEmailEditText;
    private EditText updateICEditText;
    private EditText updateDOBEditText;
    private Spinner positionSpinner;
    private Spinner statusSpinner;
    private RadioGroup genderRadioGroup;
    private String[] positions = {"Chef","Cleaner","Cashier","Manager","Waiter"};
    private String[] statuses = {"Working","Resigned","Retired","MIA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_maintenance);
        staffDetailListView = (ListView) findViewById(R.id.updateStaffListView);
        staffDetailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                selectedStaff = staffList.get(position);
            }
        });
        dialog = new Dialog(this,R.style.Theme_AppCompat_Dialog);
        view = getLayoutInflater().inflate(R.layout.activity_update_staff,null);
        updateIDEditText = view.findViewById(R.id.updateIDEditText);
        updateNameEditText = view.findViewById(R.id.updateNameEditText);
        updateContactEditText = view.findViewById(R.id.updateContactEditText);
        updateEmailEditText = view.findViewById(R.id.updateEmailEditText);
        updateICEditText = view.findViewById(R.id.updateICEditText);
        updateDOBEditText = view.findViewById(R.id.updateDOBEditText);
        positionSpinner = view.findViewById(R.id.positionSpinner);
        statusSpinner = view.findViewById(R.id.statusSpinner);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        dialog.setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStaffListView();
    }

    private void updateStaffListView(){
        staffList = InstanceDataHolder.getInstance().get_DbHelper().getAllStaffList();
        StaffDetailAdapter staffDetailAdapter = new StaffDetailAdapter(this,android.R.layout.simple_spinner_dropdown_item,staffList);
        staffDetailListView.setAdapter(staffDetailAdapter);
    }
}
