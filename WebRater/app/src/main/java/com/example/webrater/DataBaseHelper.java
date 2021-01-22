package com.example.webrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Created by zemmari on 14/11/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME ="website_db";
    private final static String TABLE_NAME ="website_table";
    private final static int VERSION_DB = 1;
    private final static String COL_1 ="ID";
    private final static String COL_2 ="URL";
    private final static String COL_3 ="RATING";


    public DataBaseHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
//On crée la base en exécutant la requête de création //...
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, URL TEXT, RATING INTEGER)");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        //On peut fait ce qu'on veut ici mais on va juste supprimer la table et la recréer //...
        db.execSQL("DROP TABLE IF EXIST "+ TABLE_NAME);
        onCreate(db);
    }

}
