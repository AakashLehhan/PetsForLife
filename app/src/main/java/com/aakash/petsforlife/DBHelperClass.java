package com.aakash.petsforlife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
            db.execSQL("CREATE TABLE pets(id INTEGER PRIMARY KEY AUTOINCREMENT, animal_name VARCHAR(255), owner_name VARCHAR(255), animal_type VARCHAR(255), animal_token VARCHAR(255), animal_desc VARCHAR(255), animal_dob VARCHAR(255))");
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
            db.execSQL("DROP TABLE IF EXISTS pets");
            db.execSQL("DROP TABLE IF EXISTS vets");
            db.execSQL("DROP TABLE IF EXISTS tips");
            db.execSQL("DROP TABLE IF EXISTS quotes");

            Cursor cursor = db.rawQuery("SELECT username FROM users", null);
            if(cursor.moveToFirst()) {
                do {
                    db.execSQL("DROP TABLE IF EXISTS " + cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
            onCreate(db);
        } catch (Exception exception) {
            Toast.makeText(context, "Caught Exception:\n" + exception, Toast.LENGTH_SHORT).show();
        }
    }

    public void setDefaults(@NonNull SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Master");
        contentValues.put("username", "master");
        contentValues.put("pin", "1234");
        contentValues.put("password", "Master@123");

        db.insert("administrators", null, contentValues);
    }

    public void createAdmin(@NonNull Entity entity){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", entity.getName());
        contentValues.put("username", entity.getUsername());
        contentValues.put("pin", entity.getPin());
        contentValues.put("password", entity.getPassword());

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
                if(password.equals(cursor.getString(0))){
                    exists = true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exists;
    }

    public List<Entity> getAdmins(){
        List<Entity> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM administrators", null);
        if (cursor.moveToFirst()) {
            do {
                Entity entity = new Entity(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
                list.add(entity);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public boolean createUser(@NonNull Entity entity){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean exists = false;

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", entity.getName());
        contentValues.put("username", entity.getUsername());
        contentValues.put("email", entity.getEmail());
        contentValues.put("contact", entity.getContact());
        contentValues.put("password", entity.getPassword());

        String[] selectionArgs = {entity.getUsername()};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", selectionArgs);
        if(cursor.moveToFirst()){
            do {
                exists = true;
            } while (cursor.moveToNext());
        }

        if(!exists) {
            db.insert("users", null, contentValues);
            db.execSQL("CREATE TABLE " + entity.getUsername() + "(pid INTEGER PRIMARY KEY AUTOINCREMENT, animal_name VARCHAR(255), animal_type VARCHAR(255), animal_token VARCHAR(255), animal_desc VARCHAR(255), animal_dob VARCHAR(255))");
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public Entity getUserDetails(String username) {
        Entity entity = new Entity();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", selectionArgs);

        if(cursor.moveToFirst()){
            do {
                entity.setName(cursor.getString(1));
                entity.setUsername(cursor.getString(2));
                entity.setEmail(cursor.getString(3));
                entity.setContact(Long.parseLong(cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return entity;
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

    public boolean addPet(String ownerName, String petName, String petToken, String petType, String petDOB, String petDescription) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("animal_name", petName);
        contentValues.put("animal_type", petType);
        contentValues.put("animal_dob", petDOB);
        contentValues.put("animal_desc", petDescription);
        contentValues.put("animal_token", petToken);
        db.insert(ownerName, null, contentValues);

        contentValues.put("owner_name", ownerName);
        db.insert("pets", null, contentValues);

        db.close();
        return true;
    }

    public boolean validatePet(String token) {
        boolean exists = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pets WHERE animal_token = ?", new String[]{token});

        if(cursor.moveToFirst()) {
            do {
                exists = true;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return exists;
    }

    public List<Entity> getAllUserPets(String username){
        List<Entity> list = new ArrayList<Entity>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + username, null);

        if(cursor.moveToFirst()) {
            do {
                Entity entity = new Entity(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                list.add(entity);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public List<Entity> getAllPets(){
        List<Entity> list = new ArrayList<Entity>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pets", null);

        if(cursor.moveToFirst()) {
            do {
                Entity entity = new Entity(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                list.add(entity);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public List<Entity> getAllUsers(){
        List<Entity> list = new ArrayList<Entity>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);

        if(cursor.moveToFirst()) {
            do {
                Entity entity = new Entity(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Long.parseLong(cursor.getString(4)),
                        cursor.getString(5));
                list.add(entity);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public boolean addPost(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", title);
        contentValues.put("description", description);

        db.insert("tips", null, contentValues);
        db.close();
        return true;
    }

    public List<Entity> getAllPosts() {
        List<Entity> list = new ArrayList<Entity>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tips", null);

        if(cursor.moveToFirst()) {
            do {
                Entity entity = new Entity(cursor.getString(1), cursor.getString(2));
                list.add(entity);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
}
