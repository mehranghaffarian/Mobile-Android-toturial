package com.example.sqlite_second_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public static final String CUSTOMERS_TABLE = "customersInfo";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IS_ACTIVE = "isActive";
    public static final String AGE = "age";

    public DB(@Nullable Context context) {
        super(context, CUSTOMERS_TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCommand = "CREATE TABLE " + CUSTOMERS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + AGE + " INT, " + IS_ACTIVE + " BOOL)";

        sqLiteDatabase.execSQL(createCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertCustomer(String name, int age, boolean is_active){
        SQLiteDatabase sQlDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(AGE, age);
        cv.put(IS_ACTIVE, is_active);

        long res = sQlDb.insert(CUSTOMERS_TABLE, null, cv);

        sQlDb.close();

        return res != -1;
    }

    public StringBuffer getAll() {
        SQLiteDatabase sql = this.getWritableDatabase();
        Cursor res = sql.rawQuery("SELECT * FROM " + CUSTOMERS_TABLE, null);
        StringBuffer buffer = new StringBuffer();

        if(res.getCount() == 0)
            buffer.append("There is no customer yet click add to add a customer.");

        while (res.moveToNext()){
            buffer.append("id: " + res.getString(0) + "\nname: " + res.getString(1) + "\nage: " + res.getString(2) + "\nis active: " + (res.getInt(3) == 1 ? "YES" : "NO") + "\n\n");
        }
        res.close();
        sql.close();

        return buffer;
    }
}
