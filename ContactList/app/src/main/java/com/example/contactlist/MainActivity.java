package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("some_int", 0);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, ContactsFragment.class, null)
                    .commit();
        }
    }
}