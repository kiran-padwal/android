package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "product_details";

    private static final String COL1 = "id";
    private static final String COL2 = "p_name";
    private static final String COL3 = "p_price";
    private static final String COL4 = "p_quantity";
    private static final String COL5 = "is_bought";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3+ " TEXT, " + COL4+ " TEXT, " + COL5 +" INTEGER DEFAULT 0)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addProduct(String p_name, String p_price, String p_quantity, Boolean is_bought) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, p_name);
        contentValues.put(COL3, p_price);
        contentValues.put(COL4, p_quantity);

        int b;
        if (is_bought) {
            b=1;
        }else{
            b=0;
        }

        contentValues.put(COL5, b);

        Log.d(TAG, "addProduct: Adding " + p_name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void updateProduct(String p_name, String p_price, String p_quantity, Boolean is_bought, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(COL2, p_name);
        newValues.put(COL3, p_price);
        newValues.put(COL4, p_quantity);
        newValues.put(COL5, is_bought);
        db.update(TABLE_NAME, newValues, "id="+id,null);

    }

    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME + " WHERE " + COL1 + " = " + id);

    }
}