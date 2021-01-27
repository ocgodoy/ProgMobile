package com.example.webrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends MainActivity {

    private String url;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
            Uri page = Uri.parse(url);
            Intent browse = new Intent(Intent.ACTION_VIEW, page);
            searchBar.setText("");
            startActivity(browse);
        }
    }


}