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
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Menus;

import org.w3c.dom.Text;

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

        menuIDSpinner = view.findViewById(R.id.menuIDSpinner);
        //InstanceDataHolder.getInstance().get_DbHelper().addNewMenu(new com.audio.yametech.myfos.Entity.Menu("M0001","Apple Juice","Apple juice is a fruit juice made by the maceration and pressing of apples.",5.00,"Drink","Available","Yes"));
        //InstanceDataHolder.getInstance().get_DbHelper().addNewMenu(new com.audio.yametech.myfos.Entity.Menu("M0002","Milo Ice","Iced Milo.",2.50,"Drink","Available","Yes"));
        menuids = InstanceDataHolder.getInstance().get_DbHelper().getMenuIDs();
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,menuids);
        menuIDSpinner.setAdapter(aa2);
        menuIDSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Menus menuItem = InstanceDataHolder.getInstance().get_DbHelper().getMenu(menuids[position]);
                if(menuItem != null) {
                    menuNameText.setText(menuItem.get_Name());
                    menuPriceText.setText("RM " + String.format("%.2f", menuItem.get_Price()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initializeButton();
    }

    private void initializeButton(){
        button1 = (Button) findViewById(R.id.button);

    }

    public void tableButtonPressed(View event){
        String tableno = ((Button)findViewById(event.getId())).getText().toString();
        cashierEditText.setText(InstanceDataHolder.getInstance().get_ActiveStaff().get_Name());
        tableEditText.setText(tableno);
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
