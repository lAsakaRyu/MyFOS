package com.audio.yametech.myfos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Menus;

import java.util.List;

public class MenuMaintenanceActivity extends AppCompatActivity {
    private ListView menuDetailListView;
    private List<Menus> menus;
    private Menus selectedMenu;
    private Dialog dialog;
    private View view;
    private EditText updateIDEditText;
    private EditText updateNameEditText;
    private EditText updateDescEditText;
    private EditText updatePriceEditText;
    private RadioGroup typeRadioGroup;
    private Spinner statusSpinner;
    private CheckBox stockCheckBox;
    private String[] status = {"Available","Not Available"};
    private Button confirmUpdateButton;
    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maintenance);

        menuDetailListView = (ListView) findViewById(R.id.menuDetailListView);
        menuDetailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                selectedMenu = menus.get(position);
            }
        });

        dialog = new Dialog(this,R.style.Theme_AppCompat_Dialog);
        view = getLayoutInflater().inflate(R.layout.activity_update_menu,null);
        updateIDEditText = view.findViewById(R.id.updateIDEditText);
        updateNameEditText = view.findViewById(R.id.updateNameEditText);
        updateDescEditText = view.findViewById(R.id.updateDescEditText);
        updatePriceEditText = view.findViewById(R.id.updatePriceEditText);
        typeRadioGroup = view.findViewById(R.id.genderRadioGroup);
        statusSpinner = view.findViewById(R.id.statusSpinner);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,status);
        statusSpinner.setAdapter(aa);
        stockCheckBox = view.findViewById(R.id.stockCheckBox);
        confirmUpdateButton = view.findViewById(R.id.confirmUpdateStaffButton);
        confirmUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = updateIDEditText.getText().toString();
                String name = updateNameEditText.getText().toString();
                String desc = updateDescEditText.getText().toString();
                double price = Double.valueOf(updatePriceEditText.getText().toString());
                String type = ((RadioButton)view.findViewById(typeRadioGroup.getCheckedRadioButtonId())).getText().toString();
                String status = statusSpinner.getSelectedItem().toString();
                String stock;
                if(stockCheckBox.isChecked())
                    stock = "Yes";
                else
                    stock = "No";
                Menus updatingMenu = new Menus(id,name,desc,price,type,status,stock);
                if(update){
                    InstanceDataHolder.getInstance().get_DbHelper().updateMenuInfo(updatingMenu);
                    dialog.dismiss();
                    Toast.makeText(getThisActivity(),"Successfully updated item.",Toast.LENGTH_SHORT).show();
                }
                else{
                    InstanceDataHolder.getInstance().get_DbHelper().addNewMenu(updatingMenu);
                    dialog.dismiss();
                    Toast.makeText(getThisActivity(),"Successfully added item.",Toast.LENGTH_SHORT).show();
                }
                updateMenuListView();
                selectedMenu = null;
            }
        });
        dialog.setContentView(view);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMenuListView();
    }

    private void updateMenuListView(){
        menus = InstanceDataHolder.getInstance().get_DbHelper().getAllMenuList();
        MenuDetailAdapter menuDetailAdapter = new MenuDetailAdapter(this,android.R.layout.simple_spinner_dropdown_item,menus);
        menuDetailListView.setAdapter(menuDetailAdapter);
    }

    public void updateMenuButtonPressed(View event){
        if(selectedMenu!=null) {
            updateIDEditText.setText(selectedMenu.get_ID());
            updateNameEditText.setText(selectedMenu.get_Name());
            updateDescEditText.setText(selectedMenu.get_Desc());
            updatePriceEditText.setText(String.valueOf(selectedMenu.get_Price()));
            if (selectedMenu.get_Type().equals("Drink"))
                typeRadioGroup.check(R.id.drinkRadioButton);
            else if (selectedMenu.get_Type().equals("Food"))
                typeRadioGroup.check(R.id.foodRadioButton);
            if (selectedMenu.get_Status().equals("Available"))
                statusSpinner.setSelection(0);
            else if (selectedMenu.get_Status().equals("Not Available"))
                statusSpinner.setSelection(1);
            stockCheckBox.setChecked(selectedMenu.get_StockStatus().equals("Yes"));
            confirmUpdateButton.setText("Update");
            update = true;
            dialog.show();
        }
        else{
            Toast.makeText(this,"No item selected.",Toast.LENGTH_SHORT).show();
        }
    }

    public void addMenuButtonPressed(View event){
            updateIDEditText.setText(InstanceDataHolder.getInstance().get_DbHelper().getNewID("menu","M"));
            updateNameEditText.setText("");
            updateDescEditText.setText("");
            updatePriceEditText.setText("");
            typeRadioGroup.check(R.id.foodRadioButton);
            statusSpinner.setSelection(0);
            stockCheckBox.setChecked(false);
            confirmUpdateButton.setText("Add");
            update = false;
            dialog.show();

    }

    public void deleteMenuButtonPressed(View event){
        if(selectedMenu!=null) {
            InstanceDataHolder.getInstance().get_DbHelper().deleteMenu(selectedMenu.get_ID());
            Toast.makeText(this,"Item deleted.",Toast.LENGTH_SHORT).show();
            selectedMenu = null;
        }
        else{
            Toast.makeText(this,"No item selected.",Toast.LENGTH_SHORT).show();
        }
        updateMenuListView();
    }
    public void cancelUpdateButtonPressed(View event){
        dialog.cancel();
    }

    private Activity getThisActivity(){
        return this;
    }
}
