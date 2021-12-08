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
            db.execSQL("CREATE TABLE administrators(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), username VARCHAR(255), pin INTEGER(4), password VARCHAR(255))");
            db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), username VARCHAR(255), email VARCHAR(255), contact INTEGER(10), password VARCHAR(255))");
            db.execSQL("CREATE TABLE vets(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), availability VARCHAR(255))");
            db.execSQL("CREATE TABLE tips(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), description VARCHAR(255))");
            db.execSQL("CREATE TABLE quotes(id INTEGER PRIMARY KEY AUTOINCREMENT, quote VARCHAR(255), quote_by VARCHAR(255))");
            //Create Default Admin
            setDefaults(db);

        } catch (Exception exception) {
            Toast.makeText(context, "Caught Exception:\n" + exception, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS administrators");
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS vets");
            db.execSQL("DROP TABLE IF EXISTS tips");
            db.execSQL("DROP TABLE IF EXISTS quotes");
            onCreate(db);
        } catch (Exception exception) {
            Toast.makeText(context, "Caught Exception:\n" + exception, Toast.LENGTH_SHORT).show();
        }
    }

    public void setDefaults(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Master");
        contentValues.put("username", "master");
        contentValues.put("pin", "1234");
        contentValues.put("password", "Master@123");

        db.insert("administrators", null, contentValues);
    }

    public void createAdmin(Add add){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", add.getName());
        contentValues.put("username", add.getUsername());
        contentValues.put("pin", add.getPin());
        contentValues.put("password", add.getPassword());

        db.insert("administrators", null, contentValues);
        db.close();
    }

    public boolean matchAdmin(String username, String password){
        boolean exists = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery("SELECT password FROM administrators WHERE username = ?", selectionArgs);

        if(cursor.moveToFirst()){
            do {
                Toast.makeText(context, "Username and password are:\n" + cursor.getString(0) + " ", Toast.LENGTH_SHORT).show();
                if(password.equals(cursor.getString(0))){
                    exists = true;
                }
            } while (cursor.moveToNext());
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

        cursor.close();
        db.close();
        return list;
    }

    public boolean createUser(Add add){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean exists = false;

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", add.getName());
        contentValues.put("username", add.getUsername());
        contentValues.put("email", add.getEmail());
        contentValues.put("contact", add.getContact());
        contentValues.put("password", add.getPassword());

        String[] selectionArgs = {add.getUsername()};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", selectionArgs);
        if(cursor.moveToFirst()){
            do {
                exists = true;
            } while (cursor.moveToNext());
        }

        if(!exists) {
            db.insert("users", null, contentValues);
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public boolean matchUser(String username, String password) {
        boolean exists = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery("SELECT username, password FROM users WHERE username = ?", selectionArgs);

        if(cursor.moveToFirst()){
            do {
                if(username.equals(cursor.getString(0)) && password.equals(cursor.getString(1))){
                    exists = true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return exists;
    }

    public boolean changeUserPassword(String username, String newPassword, String verification) {
        boolean exists = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", selectionArgs);

        if(cursor.moveToFirst()){
            do {
                String lastFourDigits = cursor.getString(4).substring(6);
                if(username.equals(cursor.getString(2)) && verification.equals(lastFourDigits)){
                    exists = true;
                }
            } while (cursor.moveToNext());
        }

        if(exists) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("password", newPassword);
            db.update("users", contentValues, "username = ?", new String[]{username});
        }

        cursor.close();
        db.close();

        return exists;
    }
}
