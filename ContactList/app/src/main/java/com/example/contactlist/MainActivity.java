package com.example.contactlist;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;

import android.provider.ContactsContract;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> MyContacts;
    private ListView listView;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)this.findViewById(R.id.list);

        ListContact();

        // initiate the listAdapter
        CustomAdapter myAdapter = new CustomAdapter(this, MyContacts);

        // assign the list adapter
        listView.setAdapter(myAdapter);

        // share contactList
        sharedPreferences = this.getSharedPreferences("contactList", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MyContacts);
        editor.putString("contactList", json);
        editor.commit();
    }
    private void ListContact() {
        MyContacts = new ArrayList<Contact>();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    1);
        }
        else {
            ContentResolver ConnectApp = this.getContentResolver();
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            String[] projection = new String[]{ContactsContract.Data.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER, ContactsContract.Data._ID, ContactsContract.Data.PHONETIC_NAME};
            Cursor cur = ConnectApp.query(uri, projection, null, null, null);

            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                    int num = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Data._ID));
                    Cursor phones = ConnectApp.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                    while (phones.moveToNext()) {
                        String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if (num != 0) {
                            MyContacts.add(new Contact(name, number));
                        }
                    }
                    phones.close();
                } while (cur.moveToNext());
            }
            cur.close();
        }
    }


}