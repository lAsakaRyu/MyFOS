package com.audio.yametech.myfos.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.audio.yametech.myfos.Entity.Menus;
import com.audio.yametech.myfos.Entity.Staff;
import com.audio.yametech.myfos.R;

import java.util.List;

/**
 * Created by TAR UC on 7/28/2017.
 */

public class StaffDetailAdapter extends ArrayAdapter<Staff> {
    public StaffDetailAdapter(Activity context, int resource, List<Staff> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Staff itemRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.staff_detail_list,
                            parent,
                            false);
        }

        TextView updateStaffIDTextView, updateStaffNameTextView, updateStaffGenderTextView, updateStaffPositionTextView, updateStaffStatusTextView, updateStaffJoinTextView, updateStaffLeaveTextView;

        updateStaffIDTextView = (TextView) convertView.findViewById(R.id.updateStaffIDTextView);
        updateStaffNameTextView = (TextView) convertView.findViewById(R.id.updateStaffNameTextView);
        updateStaffGenderTextView = (TextView) convertView.findViewById(R.id.updateStaffGenderTextView);
        updateStaffPositionTextView = (TextView) convertView.findViewById(R.id.updateStaffPositionTextView);
        updateStaffStatusTextView = (TextView) convertView.findViewById(R.id.updateStaffStatusTextView);
        updateStaffJoinTextView = (TextView) convertView.findViewById(R.id.updateStaffJoinTextView);
        updateStaffLeaveTextView = (TextView) convertView.findViewById(R.id.updateStaffLeaveTextView);


        updateStaffIDTextView.setText(itemRecord.get_ID());
        updateStaffNameTextView.setText(itemRecord.get_Name());
        updateStaffGenderTextView.setText(itemRecord.get_Gender());
        updateStaffPositionTextView.setText(itemRecord.get_Position());
        updateStaffStatusTextView.setText(itemRecord.get_Status());
        updateStaffJoinTextView.setText(itemRecord.get_JoinDate());
        updateStaffLeaveTextView.setText(itemRecord.get_LeaveDate());

        return convertView;
    }
}
