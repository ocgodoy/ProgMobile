package com.example.webrater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ViewFlipper;

public class MyRatings extends MainActivity {

    DataBaseHelper websiteDB;
    ListView listView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ratings);

        websiteDB = getWebsiteDB();
        websiteDB.getAllData();

        listView = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, websiteDB.allWebsites);
        listView.setAdapter(adapter);
    }

    @Override
    public void onPause ( ) {
        super.onPause();
        //finish();
    }
}