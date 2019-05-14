package com.charlottechia.cardatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "cars.db";

    private static final String TABLE_CAR = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_LITRE = "litre";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableSql = "CREATE TABLE " + TABLE_CAR +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BRAND + " TEXT,"
                + COLUMN_LITRE + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        // Create table(s) again
        onCreate(db);

    }

    public void insertCar(String brand, double litre){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_BRAND, brand);
        values.put(COLUMN_LITRE, litre);
        db.insert(TABLE_CAR, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Car> getCar() {
        ArrayList<Car> cars = new ArrayList<>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_BRAND + ", "
                + COLUMN_LITRE
                + " FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String brand = cursor.getString(1);
                double litre = cursor.getDouble(2);
                Car obj = new Car(id, brand, litre);
                cars.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cars;
    }

}
