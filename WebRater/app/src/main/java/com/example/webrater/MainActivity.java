package com.example.webrater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {

    boolean isSearching;
    private String url;
    DataBaseHelper websiteDB;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // open database
        websiteDB = new DataBaseHelper(this);
        isSearching=false;

    }

    public void onPause(){
        super.onPause();
        websiteDB.close();
    }

    public void start(View view){
        Intent search = new Intent(this, Search.class);
        startActivity(search);
    }

    public DataBaseHelper getWebsiteDB ( ) {
        return websiteDB;
    }

    public boolean isSearching ( ) {
        return isSearching;
    }

    public void setSearching (boolean searching) {
        this.isSearching = searching;
    }
}
