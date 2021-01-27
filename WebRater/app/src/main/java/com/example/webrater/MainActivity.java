package com.example.webrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isSearching;
    private String url;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isSearching=false;
    }

    public void onResume() {
        super.onResume();
        if(isSearching){
            isSearching = false;
            Intent intent = new Intent(this, Rating.class);
            intent.putExtra("URL", url);
            startActivity(intent);
        }
    }

    public void onPause(){
        super.onPause();
    }

    public void search (View view) {
        isSearching = true;
        EditText searchBar = (EditText) findViewById(R.id.url);
        url = searchBar.getText().toString();
        if(url.isEmpty()){
            Toast toast = Toast.makeText(this, "Please fill in the address text box", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            System.out.println(url);
            Uri page = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, page);
            searchBar.clearComposingText();
            startActivity(intent);
        }
    }
}
