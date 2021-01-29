package com.example.webrater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Website> websitesList;
    private Context context;

    public CustomAdapter(Context cont,ArrayList<Website> list){
        this.websitesList = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.websitesList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.websitesList.get(position);
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
            convertView = inf.inflate(R.layout.websites_row, null);

            holder = new ViewHolder();
            holder.url = (TextView)convertView.findViewById(R.id.url);
            //holder.rating = (TextView)convertView.findViewById(R.id.rating);
            holder.stars = (RatingBar)convertView.findViewById(R.id.stars);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Website stu = websitesList.get(position);
        holder.url.setText(stu.getUrl());
        //holder.rating.setText(String.valueOf(stu.getRating()));
        holder.stars.setRating(stu.getRating());

        return convertView;
    }

    private static class ViewHolder{
        public TextView url;
        //public TextView rating;
        public RatingBar stars;
    }
}
