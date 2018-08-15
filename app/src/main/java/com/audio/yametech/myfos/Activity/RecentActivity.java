package com.audio.yametech.myfos.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.audio.yametech.myfos.Adapter.MenuItemAdapter;
import com.audio.yametech.myfos.Adapter.RecentPaymentAdapter;
import com.audio.yametech.myfos.Entity.ExtendedOrderDetails;
import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Payment;
import com.audio.yametech.myfos.R;

import java.util.List;

public class RecentActivity extends AppCompatActivity {
    private ListView recentPaymentListView;
    private List<Payment> payments;
    private Dialog dialog;
    private View view;
    private ListView recentDetailListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        view = getLayoutInflater().inflate(R.layout.activity_recent_detail,null);
        recentDetailListView = view.findViewById(R.id.recentDetailListView);
        dialog = new Dialog(this,R.style.Theme_AppCompat_Dialog);
        dialog.setContentView(view);

        recentPaymentListView = (ListView) findViewById(R.id.recentPaymentListView);
        recentPaymentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String orderID = payments.get(position).get_OrderID();
                List<ExtendedOrderDetails> orderDetails = InstanceDataHolder.getInstance().get_DbHelper().getOrderDetailsByID(orderID);
                MenuItemAdapter menuItemAdapter = new MenuItemAdapter(getThisActivity(),android.R.layout.simple_spinner_dropdown_item,orderDetails);
                recentDetailListView.setAdapter(menuItemAdapter);
                dialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        payments = InstanceDataHolder.getInstance().get_DbHelper().getAllPaymentList();
        RecentPaymentAdapter recentPaymentAdapter = new RecentPaymentAdapter(this,android.R.layout.simple_spinner_dropdown_item,payments);
        recentPaymentListView.setAdapter(recentPaymentAdapter);
    }

    private Activity getThisActivity(){
        return this;
    }
}
