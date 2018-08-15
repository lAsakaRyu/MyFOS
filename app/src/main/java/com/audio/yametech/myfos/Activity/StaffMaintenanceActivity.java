package com.audio.yametech.myfos.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.audio.yametech.myfos.Adapter.StaffDetailAdapter;
import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Security;
import com.audio.yametech.myfos.Entity.Staff;
import com.audio.yametech.myfos.R;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private boolean update;
    private Button confirmUpdateStaffButton;

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
        updateICEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>11){
                    String d=s.toString().substring(4,6);
                    String m=s.toString().substring(2,4);
                    String y=s.toString().substring(0,2);
                    if(y.charAt(0)=='9')
                        y = "19"+y;
                    else
                        y = "20"+y;
                    String dob = String.format("%s/%s/%s",d,m,y);
                    updateDOBEditText.setText(dob);
                }
            }
        });
        updateDOBEditText = view.findViewById(R.id.updateDOBEditText);
        positionSpinner = view.findViewById(R.id.positionSpinner);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,positions);
        positionSpinner.setAdapter(aa);
        statusSpinner = view.findViewById(R.id.statusSpinner);
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,statuses);
        statusSpinner.setAdapter(aa2);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);

        confirmUpdateStaffButton = view.findViewById(R.id.confirmUpdateStaffButton);
        confirmUpdateStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = updateIDEditText.getText().toString();
                String name = updateNameEditText.getText().toString();
                String contact = updateContactEditText.getText().toString();
                String email = updateEmailEditText.getText().toString();
                String ic = updateICEditText.getText().toString();
                String dob = updateDOBEditText.getText().toString();
                String position = positionSpinner.getSelectedItem().toString();
                String status = statusSpinner.getSelectedItem().toString();
                String gender = ((RadioButton)view.findViewById(genderRadioGroup.getCheckedRadioButtonId())).getText().toString();
                if(update){
                    String leave;
                    if(statusSpinner.getSelectedItem().toString().equals("Working"))
                        leave = "?";
                    else
                        leave = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
                    Staff newStaff = new Staff(id,name,position,gender,dob,ic,contact,email,status,selectedStaff.get_DefaultPass(),selectedStaff.get_JoinDate(),leave);
                    InstanceDataHolder.getInstance().get_DbHelper().updateStaffInfo(newStaff);
                    dialog.dismiss();
                    Toast.makeText(getThisActivity(),"Staff successfully updated. ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Staff newStaff = new Staff(id,name,position,gender,dob,ic,contact,email,status,ic.substring(0,6),new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime()),"?");
                    InstanceDataHolder.getInstance().get_DbHelper().addNewStaff(newStaff);
                    InstanceDataHolder.getInstance().get_DbHelper().addNewSecurity(new Security(InstanceDataHolder.getInstance().get_DbHelper().getNewID("security","X"),newStaff.get_ID(),newStaff.get_DefaultPass()));
                    dialog.dismiss();
                    Toast.makeText(getThisActivity(),"Staff successfully added. ",Toast.LENGTH_SHORT).show();
                }
                updateStaffListView();
                selectedStaff = null;
            }
        });
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

    public void updateStaffButtonPressed(View event){
        if(populateFields()){
            update=true;
            clearFocus();
            confirmUpdateStaffButton.setText("Update");
            dialog.show();
        }
        else{
            Toast.makeText(this,"No staff selected.",Toast.LENGTH_SHORT).show();
        }
    }

    public void addStaffButtonPressed(View event){
        update=false;
        clearFields();
        clearFocus();
        updateIDEditText.setText(InstanceDataHolder.getInstance().get_DbHelper().getNewID("staff","S"));
        confirmUpdateStaffButton.setText("Add");
        statusSpinner.setEnabled(false);
        dialog.show();
    }

    public void deleteStaffButtonPressed(View event){
        if(selectedStaff!=null) {
            if (selectedStaff.get_ID().equals(InstanceDataHolder.getInstance().get_ActiveStaff().get_ID())) {
                Toast.makeText(this, "You cannot remove yourself!", Toast.LENGTH_SHORT).show();
            }
            else {
                InstanceDataHolder.getInstance().get_DbHelper().deleteStaff(selectedStaff.get_ID());
                InstanceDataHolder.getInstance().get_DbHelper().deleteSecuritybyStaffID(selectedStaff.get_ID());
                Toast.makeText(this, "Staff deleted.", Toast.LENGTH_SHORT).show();
                selectedStaff = null;
            }
        }
        else{
            Toast.makeText(this,"No staff selected.",Toast.LENGTH_SHORT).show();
        }
        updateStaffListView();
    }

    public void resetFieldButtonPressed(View event){
        if(selectedStaff!=null) {
            populateFields();
        }
        else{
            clearFields();
        }
    }

    public void cancelUpdateStaffButtonPressed(View event){
        dialog.cancel();
    }

    private void clearFields(){
        updateNameEditText.setText("");
        updateContactEditText.setText("");
        updateEmailEditText.setText("");
        updateICEditText.setText("");
        updateDOBEditText.setText("");
        positionSpinner.setSelection(0);
        statusSpinner.setSelection(0);
        genderRadioGroup.clearCheck();
    }

    private void clearFocus(){
        updateIDEditText.clearFocus();
        updateNameEditText.clearFocus();
        updateContactEditText.clearFocus();
        updateEmailEditText.clearFocus();
        updateICEditText.clearFocus();
        updateDOBEditText.clearFocus();
        positionSpinner.clearFocus();
        statusSpinner.clearFocus();
        genderRadioGroup.clearFocus();
    }
    private boolean populateFields(){
        if(selectedStaff!=null) {
            updateIDEditText.setText(selectedStaff.get_ID());
            updateNameEditText.setText(selectedStaff.get_Name());
            updateContactEditText.setText(selectedStaff.get_PhoneNumber());
            updateEmailEditText.setText(selectedStaff.get_Email());
            updateICEditText.setText(selectedStaff.get_IC());
            updateDOBEditText.setText(selectedStaff.get_DateOfBirth());
            for (int a = 0; a < positions.length; a++) {
                if (selectedStaff.get_Position().equals(positions[a])) {
                    positionSpinner.setSelection(a);
                    break;
                }
            }
            for (int a = 0; a < statuses.length; a++) {
                if (selectedStaff.get_Status().equals(statuses[a])) {
                    statusSpinner.setSelection(a);
                    break;
                }
            }
            if (selectedStaff.get_Gender().equals("Male"))
                genderRadioGroup.check(R.id.maleRadioButton);
            else if (selectedStaff.get_Gender().equals("Female"))
                genderRadioGroup.check(R.id.femaleRadioButton);
            return true;
        }
        else{
            return false;
        }
    }
    private Activity getThisActivity(){
        return this;
    }
}
