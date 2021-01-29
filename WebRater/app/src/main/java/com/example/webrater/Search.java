package com.example.webrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Search extends MainActivity {

    DataBaseHelper websiteDB;
    private String url;
    private int rating;

    private PopupWindow popupWindow;

    public Search ( ) {
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        websiteDB = getWebsiteDB();
    }

    public void onResume() {
        super.onResume();
        if(isSearching()){
            setSearching(false);
            Intent rating = new Intent(this, Rating.class);
            rating.putExtra("URL", url);
            startActivity(rating);
        }
    }
    public void search (View view) {
        setSearching(true);
        EditText searchBar = (EditText) findViewById(R.id.url);
        url = searchBar.getText().toString();
        if(url.isEmpty()){
            Toast toast = Toast.makeText(this, "Please fill in the address text box", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            if(isRated(url)){
                System.out.println("URL already rated\n");
                showPopupWindow(view);
            }
            else {
                Uri page = Uri.parse(url);
                Intent browse = new Intent(Intent.ACTION_VIEW, page);
                searchBar.setText("");
                startActivity(browse);
            }
        }
    }

    public void searchAgain(View view){
        Uri page = Uri.parse(url);
        Intent browse = new Intent(Intent.ACTION_VIEW, page);
        startActivity(browse);
    }

    private boolean isRated(String url){
        Cursor cursor = websiteDB.getByURL(url);
        if(cursor.getCount()==0){
            return false;
        } else {
            cursor.moveToFirst();
            rating = cursor.getInt(1);
            return true;
        }
    }

    private void showPopupWindow(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void closePopupWindow(View view){
        popupWindow.dismiss();
    }

}