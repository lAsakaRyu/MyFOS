package com.audio.yametech.myfos.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.audio.yametech.myfos.Entity.ChangeLog;
import com.audio.yametech.myfos.Entity.ExtendedChangeLog;
import com.audio.yametech.myfos.Entity.Staff;
import com.audio.yametech.myfos.R;

import java.util.List;
import java.util.Map;

/**
 * Created by TAR UC on 7/28/2017.
 */

public class ReportDetailAdapter extends ArrayAdapter<ExtendedChangeLog> {
    public ReportDetailAdapter(Activity context, int resource, List<ExtendedChangeLog> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ExtendedChangeLog itemRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.report_change_log,
                            parent,
                            false);
        }

        TextView reportChangeIDTextView, reportChangeStaffIDTextView, reportChangeStaffNameTextView, reportChangePositionTextView, reportChangeDateTextView;

        reportChangeIDTextView = (TextView) convertView.findViewById(R.id.reportChangeIDTextView);
        reportChangeStaffIDTextView = (TextView) convertView.findViewById(R.id.reportChangeStaffIDTextView);
        reportChangeStaffNameTextView = (TextView) convertView.findViewById(R.id.reportChangeStaffNameTextView);
        reportChangePositionTextView = (TextView) convertView.findViewById(R.id.reportChangePositionTextView);
        reportChangeDateTextView = (TextView) convertView.findViewById(R.id.reportChangeDateTextView);



        reportChangeIDTextView.setText(itemRecord.get_ID());
        reportChangeStaffIDTextView.setText(itemRecord.getStaff().get_ID());
        reportChangeStaffNameTextView.setText(itemRecord.getStaff().get_Name());
        reportChangePositionTextView.setText(itemRecord.getStaff().get_Position());
        reportChangeDateTextView.setText(itemRecord.get_DateChange());


        return convertView;
    }
}
