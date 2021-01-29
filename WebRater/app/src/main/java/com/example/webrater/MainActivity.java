package com.example.webrater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean isSearching;
    private String url;
    DataBaseHelper websiteDB;
    ListView listView;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // open database
        websiteDB = new DataBaseHelper(this);
        isSearching=false;
    }

    public void onPause(){
        super.onPause();
    }

    public void start(View view){
        Intent search = new Intent(this, Search.class);
        startActivity(search);
    }

    public void viewRatings(View view){
        int res = websiteDB.getAllData();
        if(res == -1){
            showMessage("Error", "You have not rated any website yet.");
            return;
        } else {
            Intent myRatings = new Intent(this, MyRatings.class);
            startActivity(myRatings);
        }
    }

    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
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
