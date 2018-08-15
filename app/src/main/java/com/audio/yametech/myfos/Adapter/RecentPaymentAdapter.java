package com.audio.yametech.myfos.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Payment;
import com.audio.yametech.myfos.R;

import java.util.List;

/**
 * Created by TAR UC on 7/28/2017.
 */

public class RecentPaymentAdapter extends ArrayAdapter<Payment> {
    public RecentPaymentAdapter(Activity context, int resource, List<Payment> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Payment itemRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.recent_payment_list,
                            parent,
                            false);
        }

        TextView recentIDTextView, recentOIDTextView, recentNameTextView, recentCashTextView, recentGTotalTextView, recentChangeTextView, recentDateTextView, recentTimeTextView;

        recentIDTextView = (TextView) convertView.findViewById(R.id.menuDetailIDTextView);
        recentOIDTextView = (TextView) convertView.findViewById(R.id.recentOIDTextView);
        recentNameTextView = (TextView) convertView.findViewById(R.id.recentNameTextView);
        recentCashTextView = (TextView) convertView.findViewById(R.id.menuDetailPriceTextView);
        recentGTotalTextView = (TextView) convertView.findViewById(R.id.menuDetailTypeTextView);
        recentChangeTextView = (TextView) convertView.findViewById(R.id.recentChangeTextView);
        recentDateTextView = (TextView) convertView.findViewById(R.id.recentDateTextView);
        recentTimeTextView = (TextView) convertView.findViewById(R.id.recentTimeTextView);

        recentIDTextView.setText(itemRecord.get_ID());
        recentOIDTextView.setText(itemRecord.get_OrderID());
        recentNameTextView.setText(InstanceDataHolder.getInstance().get_DbHelper().getStaffByID(itemRecord.get_StaffID()).get_Name());
        recentCashTextView.setText("RM "+String.format("%.2f",itemRecord.get_AmountPaid()));
        recentGTotalTextView.setText("RM "+String.format("%.2f",itemRecord.get_GrandTotal()));
        recentChangeTextView.setText("RM "+String.format("%.2f",itemRecord.get_Change()));
        recentDateTextView.setText(itemRecord.get_PaymentDate());
        recentTimeTextView.setText(itemRecord.get_PaymentTime());



        return convertView;

    }
}
