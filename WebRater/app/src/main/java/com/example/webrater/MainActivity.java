package com.example.webrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isSearching;

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
            startActivity(intent);
        }
    }

    public void search (View view) {
        isSearching = true;
        TextView searchBar = (TextView) findViewById(R.id.url);
        String url = searchBar.getText().toString();
        if(url.isEmpty()){
            Toast toast = Toast.makeText(this, "Please fill in the address text box", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Uri page = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, page);
            startActivity(intent);
        }
    }
}
