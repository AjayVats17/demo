package com.example.polestaruser.imageslider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "Easytravel";
    private static final String TABLE_NAME = "Logininfo";
    private static final String NAME = "name";
    private static final String PASS="pass";
    private static final int VERSION = 1;
    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "(" + NAME + " PRIMARY KEY, " + PASS + " TEXT NOT NULL " + ")";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME ;
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    public void openDatabase() {
        database = getWritableDatabase();
    }

    public void closeDatabase() {
        database.close();
    }

    public long insert(User u) {
        ContentValues values = new ContentValues();
        values.put(NAME, u.getName());
        values.put(PASS, u.getPass());
        return database.insert(TABLE_NAME, null, values);
    }

    public User Fetch(){

        SQLiteDatabase db = this.getReadableDatabase();
        User u =new User();
        try {
            String Query = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(Query, null);
            cursor.moveToLast();
            u.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            u.setPass(cursor.getString(cursor.getColumnIndex(PASS)));
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return u;
    }

    public void deleteall(){

        SQLiteDatabase db = this.getReadableDatabase();
        String del = "DELETE FROM " + TABLE_NAME ;
        db.execSQL(del);
    }

    public User first(){
        SQLiteDatabase db = this.getReadableDatabase();
        User u =new User();
        try {
            String Query = "SELECT * FROM "+TABLE_NAME;

            Cursor cursor = db.rawQuery(Query, null);
            cursor.moveToFirst();
            u.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            u.setPass(cursor.getString(cursor.getColumnIndex(PASS)));
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return u;
    }

    public User forgot(String n){
        SQLiteDatabase db = this.getReadableDatabase();
        User u =new User();
        try {
            String Query = "SELECT "+PASS+" FROM "+TABLE_NAME+" WHERE "+NAME+" = '"+n+"' ";
            Cursor cursor = db.rawQuery(Query,null);
            if (cursor.moveToFirst())
            {
                do {
                    u.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                    u.setPass(cursor.getString(cursor.getColumnIndex(PASS)));
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return u;
    }

}

