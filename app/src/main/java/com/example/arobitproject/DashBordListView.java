package com.example.arobitproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class DashBordListView extends ArrayAdapter<String> {
    final Activity context;
    final String[] customer_name;

    public DashBordListView(@NonNull Activity context, String[] customerName) {
        super(context, R.layout.dashboard_list_view, customerName);
        this.context = context;
        this.customer_name = customerName;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") final View rowView = inflater.inflate(R.layout.dashboard_list_view, null, true);

        TextView customerName = rowView.findViewById(R.id.customer_name);
        TextView details = rowView.findViewById(R.id.details_user);
        customerName.setText(customer_name[position]);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowView.getContext().startActivity(new Intent(rowView.getContext(), OrderDetailsActivity.class));
            }
        });
        return rowView;
    }
}

