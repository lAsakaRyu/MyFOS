package com.audio.yametech.myfos;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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

import com.audio.yametech.myfos.Entity.ExtendedOrderDetails;
import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Menus;
import com.audio.yametech.myfos.Entity.OrderDetail;
import com.audio.yametech.myfos.Entity.PlacedOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView staffName;
    private TextClock textClock;

    private Button button1;
    private View view;
    private EditText orderEditText;
    private EditText tableEditText;
    private EditText cashierEditText;
    private Dialog dialog;
    private Spinner menuIDSpinner;
    private Spinner quantitySpinner;
    private String[] quantites = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    private String[] menuids;
    private TextView menuNameText;
    private TextView menuPriceText;
    private ListView menuItemListView;



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

        //staffName = (TextView) findViewById(R.id.staffName);
        //staffName.setText(InstanceDataHolder.getInstance().get_ActiveStaff().get_Name());
        setTitle(getString(R.string.title_activity_main)+" "+InstanceDataHolder.getInstance().get_ActiveStaff().get_Name());
        textClock = (TextClock) findViewById(R.id.textClock);
        textClock.setFormat12Hour(null);
        //textClock.setFormat24Hour("dd/MM/yyyy hh:mm:ss a");
        textClock.setFormat24Hour("dd/MM/yyyy hh:mm:ss a");

        initializeTableFunction();
    }

    private void initializeTableFunction(){
        view = getLayoutInflater().inflate(R.layout.activity_table,null);
        orderEditText = view.findViewById(R.id.orderno);
        tableEditText = view.findViewById(R.id.tableno);
        cashierEditText =  view.findViewById(R.id.cashiername);
        dialog = new Dialog(this,R.style.Theme_AppCompat_Dialog);
        dialog.setContentView(view);

        menuNameText = view.findViewById(R.id.menuNameText);
        menuPriceText = view.findViewById(R.id.menuPriceText);

        quantitySpinner = view.findViewById(R.id.quantitySpinner);
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

        menuIDSpinner = view.findViewById(R.id.menuIDSpinner);
        //InstanceDataHolder.getInstance().get_DbHelper().addNewMenu(new Menus(InstanceDataHolder.getInstance().get_DbHelper().getNewID("menu","M"),"Apple Juice","Apple juice is a fruit juice made by the maceration and pressing of apples.",5.00,"Drink","Available","Yes"));
        //InstanceDataHolder.getInstance().get_DbHelper().addNewMenu(new Menus(InstanceDataHolder.getInstance().get_DbHelper().getNewID("menu","M"),"Milo Ice","Iced Milo.",2.50,"Drink","Available","Yes"));
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

        menuItemListView = view.findViewById(R.id.menuItemListView);
        menuItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                InstanceDataHolder.getInstance().set_SelectedOrder(((TextView)view.findViewById(R.id.idTextView)).getText().toString());
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
    }

    public void removeOrderButton(View event){
        String orderDetailID = InstanceDataHolder.getInstance().get_SelectedOrder();
        InstanceDataHolder.getInstance().get_DbHelper().deleteOrderDetail(orderDetailID);
        updateMenuListView(InstanceDataHolder.getInstance().get_ActiveTableNo());
    }

    public void updateMenuListView(String tableNumber){
        List<ExtendedOrderDetails> menuItems = InstanceDataHolder.getInstance().get_DbHelper().getOrderDetailsByTable(tableNumber);
        if(menuItems != null){
            MenuItemAdapter menuItemAdapter = new MenuItemAdapter(this, R.layout.menu_item_list, menuItems);
            menuItemListView.setAdapter(menuItemAdapter);
        }
        else{
            String placedOrderID = InstanceDataHolder.getInstance().get_DbHelper().getPlacedOrderID(tableNumber);
            if(placedOrderID != null){
                InstanceDataHolder.getInstance().get_DbHelper().deletePlacedOrder(placedOrderID);
            }
        }
    }

    public void tableButtonPressed(View event){
        String tableno = ((Button)findViewById(event.getId())).getText().toString();
        InstanceDataHolder.getInstance().set_ActiveTableNo(tableno);
        cashierEditText.setText(InstanceDataHolder.getInstance().get_ActiveStaff().get_Name());
        tableEditText.setText(tableno);
        updateMenuListView(tableno);

        dialog.show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
