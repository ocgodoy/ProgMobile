package com.example.webrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    private static DataBaseHelper sInstance;

    public DataBaseHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    public void onCreate(SQLiteDatabase db){
//On crée la base en exécutant la requête de création //...
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, URL TEXT, RATING INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        //On peut fait ce qu'on veut ici mais on va juste supprimer la table et la recréer //...
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String url, int rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, url);
        contentValues.put(COL_3, rating);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateRating(String url, int rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, rating);
        db.update(TABLE_NAME, contentValues, "URL = ?", new String[] {url});
        return true;
    }

    public Cursor getURL(String url){
        System.out.println("getting url from db \n");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select URL, RATING from "+TABLE_NAME+" where URL = ?", new String[] {url});
        return res;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }


}
