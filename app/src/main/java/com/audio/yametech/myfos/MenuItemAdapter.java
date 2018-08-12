package com.audio.yametech.myfos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.audio.yametech.myfos.Entity.ExtendedOrderDetails;
import com.audio.yametech.myfos.Entity.Menus;

import java.util.List;

/**
 * Created by TAR UC on 7/28/2017.
 */

public class MenuItemAdapter extends ArrayAdapter<ExtendedOrderDetails> {
    public MenuItemAdapter(Activity context, int resource, List<ExtendedOrderDetails> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ExtendedOrderDetails itemRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.menu_item_list,
                            parent,
                            false);
        }

        TextView textViewID, textViewName, textViewPrice, textViewQuantity, textViewSubTotal;

        textViewID = (TextView) convertView.findViewById(R.id.idTextView);
        textViewName = (TextView) convertView.findViewById(R.id.nameTextView);
        textViewPrice = (TextView) convertView.findViewById(R.id.priceTextView);
        textViewQuantity = (TextView) convertView.findViewById(R.id.quantityTextView);
        textViewSubTotal = (TextView) convertView.findViewById(R.id.subTotalTextView);


        textViewID.setText(itemRecord.get_ID());
        textViewName.setText(itemRecord.get_Name());
        textViewPrice.setText("RM "+String.format("%.2f",itemRecord.get_Price()));
        textViewQuantity.setText("x"+itemRecord.get_Qty());
        textViewSubTotal.setText("RM "+String.format("%.2f",itemRecord.get_SubTotal()));


        return convertView;

    }
}
