package com.aakash.petsforlife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelperClass extends SQLiteOpenHelper {

    public static final String DB_NAME = "PetsForLife";
    public static final int DB_VERSION = 1;

    private Context context;

    public DBHelperClass(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE administrators(name VARCHAR(255), username VARCHAR(255), pin INTEGER(4), password VARCHAR(255))");

            //Create Default Admin
            Add add = new Add("Master", "master", 1234, "Master@123");
            createAdmin(add);

        } catch (Exception exception) {
            Toast.makeText(context, "Caught Exception:\n" + exception, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS administrators");
            onCreate(db);
        } catch (Exception exception) {
            Toast.makeText(context, "Caught Exception:\n" + exception, Toast.LENGTH_SHORT).show();
        }
    }

    public void createAdmin(Add add){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", add.getName());
        contentValues.put("username", add.getName());
        contentValues.put("pin", add.getName());
        contentValues.put("password", add.getName());

        db.insert("administrators", null, contentValues);
        db.close();
    }

    public boolean matchAdmin(String username, String password){
        boolean exists = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username, password FROM administrators WHERE username = '" + username + "'", null);

        Toast.makeText(context, "Matching", Toast.LENGTH_SHORT).show();

        if(cursor.moveToFirst()){
            do {
                Toast.makeText(context, "Username and password are:\n" + cursor.getString(0) + " " + cursor.getString(3), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
            //if(password.equals(cursor.getString(3))){
            //    exists = true;
            //}
        }
        cursor.close();
        db.close();
        return exists;
    }

    public List<Add> getAdmins(){
        List<Add> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM administrators", null);
        if (cursor.moveToFirst()) {
            do {
                Add add = new Add(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
                list.add(add);
            } while (cursor.moveToNext());
        }

        return list;
    }
}
