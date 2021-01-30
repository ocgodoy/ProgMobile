package com.example.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Contact> contactsList;
    private Context context;

    public CustomAdapter(Context cont,ArrayList<Contact> list){
        this.contactsList = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contactsList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.contact_row, null);

            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.phoneNumber = (TextView)convertView.findViewById(R.id.phoneNumber);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Contact stu = contactsList.get(position);
        holder.name.setText(stu.getName());
        holder.phoneNumber.setText(stu.getPhoneNumber());

        return convertView;
    }

    private static class ViewHolder{
        public TextView name;
        public TextView phoneNumber;
    }
}
