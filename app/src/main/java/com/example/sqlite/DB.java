package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context, "usersInformation", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase SDB) {
        SDB.execSQL("create Table usersInformation(username TEXT primary key, phoneNumber TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SDB, int i, int i1) {
        SDB.execSQL("drop Table if exists userInformation");
    }

    public Boolean registerUser(String username, String phoneNumber, String email){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("phoneNumber", phoneNumber);
        cv.put("email", email);

        long res = sd.insert("usersInformation", null, cv);

        sd.close();
        return res != -1;
    }

    public Boolean updateUser(String userName, String phoneNumber, String email){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", userName);
        cv.put("phoneNumber", phoneNumber);
        cv.put("email", email);

        Cursor cursor = sd.rawQuery("Select * from usersInformation where username = ?", new String[]{userName});

        if(cursor.getCount() > 0) {
            long res = sd.update("usersInformation", cv, "username = ?", new String[]{userName});

            cursor.close();
            sd.close();
            return res != -1;
        }
        return false;
    }

    public Boolean deleteUser(String username){
        SQLiteDatabase sd = this.getWritableDatabase();

        Cursor cursor = sd.rawQuery("Select * from usersInformation where username = ?", new String[]{username});

        if(cursor.getCount() > 0) {
            long res = sd.delete("usersInformation", "username = ?", new String[]{username});

            cursor.close();
            sd.close();
            return res != -1;
        }
        return false;
    }

    public Cursor getData(){return this.getWritableDatabase().rawQuery("Select * from usersInformation", null);}
}
