package com.audio.yametech.myfos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.audio.yametech.myfos.Entity.ExtendedOrderDetails;
import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Menus;
import com.audio.yametech.myfos.Entity.OrderDetail;
import com.audio.yametech.myfos.Entity.Payment;
import com.audio.yametech.myfos.Entity.PlacedOrder;
import com.audio.yametech.myfos.Entity.Staff;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView staffName;
    private TextClock textClock;
    private View viewTable;
    private View viewPayment;
    private EditText orderEditText;
    private EditText tableEditText;
    private EditText cashierEditText;
    private EditText grandTotalEditText;
    private EditText paymentCashEditText;
    private EditText paymentGrandTotalEditText;
    private EditText paymentChangeEditText;
    private Dialog dialogTable;
    private Dialog dialogPayment;
    private Spinner menuIDSpinner;
    private Spinner quantitySpinner;
    private String[] quantites = {"1","2","3","4","5","6","7","8","9"};
    private String[] menuids;
    private TextView menuNameText;
    private TextView menuPriceText;
    private TextView paymentTransText;
    private TextView paymentCashierText;
    private TextView paymentDateText;
    private TextView paymentTimeText;
    private ListView menuItemListView;
    private ListView paymentItemListView;
    private List<Button> tableButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle(getString(R.string.title_activity_main)+" "+InstanceDataHolder.getInstance().get_ActiveStaff().get_Name());
        textClock = (TextClock) findViewById(R.id.textClock);
        textClock.setFormat12Hour(null);
        textClock.setFormat24Hour("dd/MM/yyyy hh:mm:ss a");

        initializeTableFunction();
        initializeTableButtons();

        if(!InstanceDataHolder.getInstance().get_ActiveStaff().get_Position().equals("Manager")){
            NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
            MenuItem menuItem = nv.getMenu().findItem(R.id.maintenanceGroupMenu);
            menuItem.setVisible(false);
        }
    }

    private void checkTableStatus(){
        List<String> activeTable = InstanceDataHolder.getInstance().get_DbHelper().getAllActiveTableList();
        for(Button b:tableButtons){
            if(activeTable.contains(b.getText())){
                b.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorActive));
            }
            else{
                b.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorPassive));
            }
        }
    }

    private void initializeTableButtons(){
        tableButtons = new ArrayList<>();
        tableButtons.add((Button)findViewById(R.id.button));
        tableButtons.add((Button)findViewById(R.id.button2));
        tableButtons.add((Button)findViewById(R.id.button3));
        tableButtons.add((Button)findViewById(R.id.button4));
        tableButtons.add((Button)findViewById(R.id.button5));
        tableButtons.add((Button)findViewById(R.id.button6));
        tableButtons.add((Button)findViewById(R.id.button7));
        tableButtons.add((Button)findViewById(R.id.button8));
        tableButtons.add((Button)findViewById(R.id.button9));
        tableButtons.add((Button)findViewById(R.id.button10));
        tableButtons.add((Button)findViewById(R.id.button11));
        tableButtons.add((Button)findViewById(R.id.button12));
        tableButtons.add((Button)findViewById(R.id.button13));
        tableButtons.add((Button)findViewById(R.id.button14));
        tableButtons.add((Button)findViewById(R.id.button15));
        tableButtons.add((Button)findViewById(R.id.button16));
        tableButtons.add((Button)findViewById(R.id.button17));
        tableButtons.add((Button)findViewById(R.id.button18));
        tableButtons.add((Button)findViewById(R.id.button19));
        tableButtons.add((Button)findViewById(R.id.button20));
        tableButtons.add((Button)findViewById(R.id.button21));
        tableButtons.add((Button)findViewById(R.id.button22));
        tableButtons.add((Button)findViewById(R.id.button23));
        tableButtons.add((Button)findViewById(R.id.button24));
        tableButtons.add((Button)findViewById(R.id.button25));
        tableButtons.add((Button)findViewById(R.id.button26));
        tableButtons.add((Button)findViewById(R.id.button27));
        tableButtons.add((Button)findViewById(R.id.button28));
        tableButtons.add((Button)findViewById(R.id.button29));
        tableButtons.add((Button)findViewById(R.id.button30));
    }

    private void initializeTableFunction(){
        viewTable = getLayoutInflater().inflate(R.layout.activity_table,null);
        orderEditText = viewTable.findViewById(R.id.orderno);
        tableEditText = viewTable.findViewById(R.id.tableno);
        cashierEditText =  viewTable.findViewById(R.id.cashiername);
        dialogTable = new Dialog(this,R.style.Theme_AppCompat_Dialog);
        dialogTable.setContentView(viewTable);

        menuNameText = viewTable.findViewById(R.id.menuNameText);
        menuPriceText = viewTable.findViewById(R.id.menuPriceText);

        quantitySpinner = viewTable.findViewById(R.id.quantitySpinner);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,quantites);
        quantitySpinner.setAdapter(aa);
        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InstanceDataHolder.getInstance().set_SelectedQuantity(quantites[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        menuIDSpinner = viewTable.findViewById(R.id.menuIDSpinner);
        List<Menus> menuList = InstanceDataHolder.getInstance().get_DbHelper().getAllMenuList();
        if(menuList.isEmpty()) {
            InstanceDataHolder.getInstance().get_DbHelper().addNewMenu(new Menus(InstanceDataHolder.getInstance().get_DbHelper().getNewID("menu","M"),"Apple Juice","Apple juice is a fruit juice made by the maceration and pressing of apples.",5.00,"Drink","Available","Yes"));
            InstanceDataHolder.getInstance().get_DbHelper().addNewMenu(new Menus(InstanceDataHolder.getInstance().get_DbHelper().getNewID("menu","M"),"Milo Ice","Iced Milo.",2.50,"Drink","Available","Yes"));
        }
        menuids = InstanceDataHolder.getInstance().get_DbHelper().getMenuIDs();
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,menuids);
        menuIDSpinner.setAdapter(aa2);
        menuIDSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Menus menuItem = InstanceDataHolder.getInstance().get_DbHelper().getMenu(menuids[position]);
                InstanceDataHolder.getInstance().set_ActiveMenuItem(menuItem);
                if(menuItem != null) {
                    menuNameText.setText(menuItem.get_Name());
                    menuPriceText.setText("RM " + String.format("%.2f", menuItem.get_Price()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        menuItemListView = viewTable.findViewById(R.id.menuItemListView);
        menuItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                InstanceDataHolder.getInstance().set_SelectedOrder(((TextView)view.findViewById(R.id.menuDetailIDTextView)).getText().toString());
            }
        });

        grandTotalEditText = viewTable.findViewById(R.id.grandTotalEditText);

        viewPayment = getLayoutInflater().inflate(R.layout.activity_payment,null);
        paymentCashEditText = viewPayment.findViewById(R.id.paymentCashEditText);
        paymentCashEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")) {
                    double grandTotal = InstanceDataHolder.getInstance().get_ActivePayment().get_GrandTotal();
                    double cash = Double.parseDouble(s.toString());
                    double change = cash - grandTotal;
                    if (change < 0)
                        change = 0;
                    paymentChangeEditText.setText(String.format("%.2f", change));
                    InstanceDataHolder.getInstance().get_ActivePayment().set_AmountPaid(cash);
                    InstanceDataHolder.getInstance().get_ActivePayment().set_Change(change);
                }
            }
        });
        paymentGrandTotalEditText = viewPayment.findViewById(R.id.paymentGrandTotalEditText);
        paymentChangeEditText = viewPayment.findViewById(R.id.paymentChangeEditText);
        paymentTransText = viewPayment.findViewById(R.id.paymentTransText);
        paymentCashierText = viewPayment.findViewById(R.id.paymentCashierText);
        paymentDateText = viewPayment.findViewById(R.id.paymentDateText);
        paymentTimeText = viewPayment.findViewById(R.id.paymentTimeText);
        paymentItemListView = viewPayment.findViewById(R.id.paymentItemListView);
        dialogPayment = new Dialog(this,R.style.Theme_AppCompat_Dialog);
        dialogPayment.setContentView(viewPayment);
        dialogPayment.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                paymentCashEditText.setText(null);
                InstanceDataHolder.getInstance().set_ActivePayment(null);
            }
        });
        dialogPayment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                checkTableStatus();
                paymentCashEditText.setText(null);
                InstanceDataHolder.getInstance().set_ActivePayment(null);
            }
        });
    }

    public void addOrderButton(View event){
        String placedOrderID = InstanceDataHolder.getInstance().get_DbHelper().getPlacedOrderID(InstanceDataHolder.getInstance().get_ActiveTableNo());
        if( placedOrderID == null){
            placedOrderID = InstanceDataHolder.getInstance().get_DbHelper().getNewID("placed_order","L");
            String dateOrdered = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
            PlacedOrder placedOrder = new PlacedOrder(placedOrderID,dateOrdered,InstanceDataHolder.getInstance().get_ActiveTableNo(),"active");
            InstanceDataHolder.getInstance().get_DbHelper().addNewPlacedOrder(placedOrder);
        }
        String menuID = InstanceDataHolder.getInstance().get_ActiveMenuItem().get_ID();
        String quantity = InstanceDataHolder.getInstance().get_SelectedQuantity();
        double subTotal = Double.valueOf(quantity)* InstanceDataHolder.getInstance().get_ActiveMenuItem().get_Price();
        OrderDetail orderDetail = new OrderDetail(InstanceDataHolder.getInstance().get_DbHelper().getNewID("order_detail","O"), menuID,placedOrderID,Integer.valueOf(quantity),subTotal);
        InstanceDataHolder.getInstance().get_DbHelper().addNewOrderDetail(orderDetail);
        updateMenuListView(InstanceDataHolder.getInstance().get_ActiveTableNo());
        checkTableStatus();
    }

    public void removeOrderButton(View event){
        String orderDetailID = InstanceDataHolder.getInstance().get_SelectedOrder();
        InstanceDataHolder.getInstance().get_DbHelper().deleteOrderDetail(orderDetailID);
        updateMenuListView(InstanceDataHolder.getInstance().get_ActiveTableNo());
        checkTableStatus();
    }

    public void updateMenuListView(String tableNumber){
        List<ExtendedOrderDetails> menuItems = InstanceDataHolder.getInstance().get_DbHelper().getOrderDetailsByTable(tableNumber);
        double grandTotal = 0;
        for(ExtendedOrderDetails e : menuItems){
            grandTotal += e.get_SubTotal();
        }
        grandTotalEditText.setText("RM "+String.format("%.2f",grandTotal));
        MenuItemAdapter menuItemAdapter = new MenuItemAdapter(this, R.layout.menu_item_list, menuItems);
        menuItemListView.setAdapter(menuItemAdapter);
        if(menuItems.isEmpty()){
            String placedOrderID = InstanceDataHolder.getInstance().get_DbHelper().getPlacedOrderID(tableNumber);
            if(placedOrderID != null){
                InstanceDataHolder.getInstance().get_DbHelper().deletePlacedOrder(placedOrderID);
            }
        }
    }

    public void clearOrderButton(View event){
        List<ExtendedOrderDetails> menuItems = InstanceDataHolder.getInstance().get_DbHelper().getOrderDetailsByTable(InstanceDataHolder.getInstance().get_ActiveTableNo());
        for(ExtendedOrderDetails e : menuItems){
            InstanceDataHolder.getInstance().get_DbHelper().deleteOrderDetail(e.get_ID());
        }
        updateMenuListView(InstanceDataHolder.getInstance().get_ActiveTableNo());
        checkTableStatus();
    }

    public void tableButtonPressed(View event){
        String tableno = ((Button)findViewById(event.getId())).getText().toString();
        InstanceDataHolder.getInstance().set_ActiveTableNo(tableno);
        cashierEditText.setText(InstanceDataHolder.getInstance().get_ActiveStaff().get_Name());
        tableEditText.setText(tableno);
        updateMenuListView(tableno);

        dialogTable.show();
    }

    public void paymentButtonPressed(View event){
        String tableNumber = InstanceDataHolder.getInstance().get_ActiveTableNo();
        List<ExtendedOrderDetails> menuItems = InstanceDataHolder.getInstance().get_DbHelper().getOrderDetailsByTable(tableNumber);
        double grandTotal = 0;
        for(ExtendedOrderDetails e : menuItems){
            grandTotal += e.get_SubTotal();
        }
        if(grandTotal>0) {
            String paymentID = InstanceDataHolder.getInstance().get_DbHelper().getNewID("payment", "P");
            String orderID = InstanceDataHolder.getInstance().get_DbHelper().getPlacedOrderID(tableNumber);
            Staff staff = InstanceDataHolder.getInstance().get_ActiveStaff();
            String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date().getTime());
            Payment newPayment = new Payment(paymentID, orderID, staff.get_ID(), grandTotal, 0, 0, date, time);
            InstanceDataHolder.getInstance().set_ActivePayment(newPayment);

            MenuItemAdapter menuItemAdapter = new MenuItemAdapter(this, R.layout.menu_item_list, menuItems);
            paymentItemListView.setAdapter(menuItemAdapter);
            paymentTransText.setText(paymentID);
            paymentCashierText.setText(staff.get_Name());
            paymentDateText.setText(date);
            paymentTimeText.setText(time);
            paymentGrandTotalEditText.setText(String.format("%.2f", grandTotal));
            dialogPayment.show();
            paymentCashEditText.requestFocus();
        }
        else{
            Toast.makeText(this,"Order is Empty",Toast.LENGTH_SHORT).show();
        }

    }

    public void proceedButtonPressed(View event){
        boolean suffic = InstanceDataHolder.getInstance().get_ActivePayment().verifyPaymentAmount();
        if(suffic) {
            Payment payment = InstanceDataHolder.getInstance().get_ActivePayment();
            InstanceDataHolder.getInstance().get_DbHelper().addNewPayment(payment);
            InstanceDataHolder.getInstance().get_DbHelper().updatePaidOrder(payment.get_OrderID());
            dialogPayment.dismiss();
            dialogTable.dismiss();
            Toast.makeText(this,"Payment Completed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Insufficient Cash",Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelButtonPressed(View event){
        if(dialogPayment.isShowing())
            dialogPayment.cancel();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation viewTable item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recent) {
            Intent intent = new Intent(this,RecentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_change) {
            Intent intent = new Intent(this,PasswordActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_maintmenu) {
            Intent intent = new Intent(this,MenuMaintenanceActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_maintstaff) {
            Intent intent = new Intent(this,StaffMaintenanceActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            finish();
            InstanceDataHolder.getInstance().reset();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this,"Logout successfully.",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkTableStatus();

    }
}
