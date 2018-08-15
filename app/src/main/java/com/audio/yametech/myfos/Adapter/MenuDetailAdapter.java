package com.audio.yametech.myfos.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Menus;
import com.audio.yametech.myfos.R;

import java.util.List;

/**
 * Created by TAR UC on 7/28/2017.
 */

public class MenuDetailAdapter extends ArrayAdapter<Menus> {
    public MenuDetailAdapter(Activity context, int resource, List<Menus> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Menus itemRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.menu_detail_list,
                            parent,
                            false);
        }

        TextView menuDetailIDTextView, menuDetailNameTextView, menuDetailDescTextView, menuDetailPriceTextView, menuDetailTypeTextView, menuDetailStatusTextView, menuDetailStockTextView;

        menuDetailIDTextView = (TextView) convertView.findViewById(R.id.menuDetailIDTextView);
        menuDetailNameTextView = (TextView) convertView.findViewById(R.id.menuDetailNameTextView);
        menuDetailDescTextView = (TextView) convertView.findViewById(R.id.menuDetailDescTextView);
        menuDetailPriceTextView = (TextView) convertView.findViewById(R.id.menuDetailPriceTextView);
        menuDetailTypeTextView = (TextView) convertView.findViewById(R.id.menuDetailTypeTextView);
        menuDetailStatusTextView = (TextView) convertView.findViewById(R.id.menuDetailStatusTextView);
        menuDetailStockTextView = (TextView) convertView.findViewById(R.id.menuDetailStockTextView);

        menuDetailIDTextView.setText(itemRecord.get_ID());
        menuDetailNameTextView.setText(itemRecord.get_Name());
        menuDetailDescTextView.setText(itemRecord.get_Desc());
        menuDetailPriceTextView.setText("RM "+String.format("%.2f",itemRecord.get_Price()));
        menuDetailTypeTextView.setText(itemRecord.get_Type());
        menuDetailStatusTextView.setText(itemRecord.get_Status());
        menuDetailStockTextView.setText(itemRecord.get_StockStatus());

        return convertView;
    }
}
