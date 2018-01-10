package com.bcil.travellingvoucher.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.bcil.travellingvoucher.models.ActualBarcode;
import com.bcil.travellingvoucher.models.BLRMHATTRIBUTE;
import com.bcil.travellingvoucher.models.BLRMKATTRIBUTE;
import com.bcil.travellingvoucher.models.ExpectedBarcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by NG on 20-Jul-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "locationdb";

    // Contacts table name
    public static final String TABLE_ACTUAL_BARCODE = "actualbarcodetable";
    public static final String TABLE_EXPECTED_BARCODE = "expectedbarcodetable";
    // Contacts Table Columns names
    public static final String KEY_ZIP1 = "zip1";
    public static final String KEY_ZIP2 = "zip2";
    public static final String KEY_LOCATION1 = "location1";
    public static final String KEY_LOCATION2 = "location2";
    public static final String KEY_DBARCODEFROMCSV1 = "dbarcodecsv1";
    public static final String KEY_DBARCODEFROMCSV2 = "dbarcodecsv2";
    public static final String KEY_ID1 = "id1";
    public static final String KEY_ID2 = "id2";
    private static final String KEY_ZIPVALUE2 = "zipvalue2";
    private static final String KEY_ZIPVALUE1 = "zipvalue1";
    public static final String TABLE_LOCATION1 = "location1";
    public static final String TABLE_LOCATION2 = "location2";
    public static final String KEY_BLRMH_ID = "blrmhid";
    public static final String KEY_BLRMK_ID = "blrmkid";
    public static final String KEY_BLRMH_ZIP = "blrmhzip";
    public static final String KEY_BLRMH_LOCATION = "blrmhlocation";
    public static final String KEY_BLRMH_ZIP_VALUE = "blrmhzipvalue";
    public static final String KEY_BLRMK_ZIP = "blrmkzip";
    public static final String KEY_BLRMK_LOCATION = "blrmklocation";
    public static final String KEY_BLRMK_ZIP_VALUE = "blrmkzipvalue";
    private BLRMKATTRIBUTE blrmkattribute;
    private SQLiteDatabase db;
    private Cursor cursor;
    private BLRMHATTRIBUTE blrmhattribute;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
       /* String CREATE_CSV1_TABLE = "CREATE TABLE " + TABLE_LOCATION1 + "("
                + KEY_ID1 + " INTEGER PRIMARY KEY," + KEY_ZIP1 + " TEXT," + KEY_LOCATION1 + " TEXT," + KEY_DBARCODEFROMCSV1 + " TEXT" + ")";
        db.execSQL(CREATE_CSV1_TABLE);
        String CREATE_CSV2_TABLE = "CREATE TABLE " + TABLE_LOCATION2 + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY," + KEY_ZIP2 + " TEXT," + KEY_LOCATION2 + " TEXT," + KEY_DBARCODEFROMCSV2 + " TEXT" + ")";
        db.execSQL(CREATE_CSV2_TABLE);*/
        String CREATE_ACTUAL_BARCODE_TABLE = "CREATE TABLE " + TABLE_ACTUAL_BARCODE + "("
                + KEY_ID1 + " INTEGER PRIMARY KEY," +  KEY_ZIP1 + " TEXT," + KEY_LOCATION1 + " TEXT," + KEY_ZIPVALUE1 + " TEXT" + ")";
        db.execSQL(CREATE_ACTUAL_BARCODE_TABLE);
        String CREATE_EXPECTED_BARCODE_TABLE = "CREATE TABLE " + TABLE_EXPECTED_BARCODE + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY," +  KEY_ZIP2 + " TEXT," + KEY_LOCATION2 + " TEXT," + KEY_ZIPVALUE2 + " TEXT" + ")";
        db.execSQL(CREATE_EXPECTED_BARCODE_TABLE);
        String CREATE_CSV1_TABLE = "CREATE TABLE " + TABLE_LOCATION1 + "("
                + KEY_BLRMH_ID + " INTEGER PRIMARY KEY," +  KEY_BLRMH_ZIP + " TEXT," + KEY_BLRMH_LOCATION + " TEXT," + KEY_BLRMH_ZIP_VALUE + " TEXT" + ")";
        db.execSQL(CREATE_CSV1_TABLE);
        String CREATE_CSV2_TABLE = "CREATE TABLE " + TABLE_LOCATION2 + "("
                + KEY_BLRMK_ID + " INTEGER PRIMARY KEY," + KEY_BLRMK_ZIP + " TEXT," + KEY_BLRMK_LOCATION + " TEXT," + KEY_BLRMK_ZIP_VALUE + " TEXT" + ")";
        db.execSQL(CREATE_CSV2_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTUAL_BARCODE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPECTED_BARCODE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION2);
        // Create tables again
        onCreate(db);
    }


    public ArrayList<HashMap<String, String>> getAllLocation1Value() {
        ArrayList<HashMap<String, String>> locationList;
        locationList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM location1";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //Id, Company,Name,Price
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_BLRMH_ID, cursor.getString(0));
                map.put(KEY_BLRMH_ZIP, cursor.getString(1));
                map.put(KEY_BLRMH_LOCATION, cursor.getString(2));
                map.put(KEY_BLRMH_ZIP_VALUE, cursor.getString(3));
                locationList.add(map);
            } while (cursor.moveToNext());
        }

        return locationList;
    }

    public ArrayList<HashMap<String, String>> getAllLocation2Value() {
        ArrayList<HashMap<String, String>> locationList;
        locationList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM location2";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //Id, Company,Name,Price
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_BLRMK_ID, cursor.getString(0));
                map.put(KEY_BLRMK_ZIP, cursor.getString(1));
                map.put(KEY_BLRMK_LOCATION, cursor.getString(2));
                map.put(KEY_BLRMK_ZIP_VALUE, cursor.getString(3));
                locationList.add(map);
            } while (cursor.moveToNext());
        }

        return locationList;
    }


    public void addActualBarcodeValue(ActualBarcode actualBarcode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ZIP1, actualBarcode.getZip());
        values.put(KEY_LOCATION1, actualBarcode.getLocation());
        values.put(KEY_ZIPVALUE1, actualBarcode.getZipvalue());
        // Inserting Row
        db.insert(TABLE_ACTUAL_BARCODE, null, values);
        db.close(); // Closing database connection
    }

    public void addExceptedBarcodeValue(ExpectedBarcode expectedBarcode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ZIP2, expectedBarcode.getZip());
        values.put(KEY_LOCATION2, expectedBarcode.getLocation());
        values.put(KEY_ZIPVALUE2, expectedBarcode.getZipvalue());
        // Inserting Row
        db.insert(TABLE_EXPECTED_BARCODE, null, values);
        db.close(); // Closing database connection
    }



    public BLRMHATTRIBUTE getLocation1Info(String zipvalue) {
        try{
            db = this.getReadableDatabase();

            cursor = db.query(TABLE_LOCATION1, new String[] { KEY_BLRMH_ID,KEY_BLRMH_ZIP,
                            KEY_BLRMH_LOCATION, KEY_BLRMH_ZIP_VALUE }, KEY_BLRMH_ZIP_VALUE+ "=?",
                    new String[] { zipvalue }, null, null, null, null);
            if (cursor != null&& cursor.moveToFirst()){
                blrmhattribute = new BLRMHATTRIBUTE(cursor.getString(1), cursor.getString(2));
            }
//                cursor.moveToFirst();

        }catch (final Exception e){
            Log.d(DatabaseHandler.class.getSimpleName(),e.getMessage());
        }finally {
            if(cursor!=null)
                cursor.close();
            db.close();
        }
        return blrmhattribute;
        // return contact
       /* cursor.close();
        return blrmhattribute;*/
    }

    public BLRMKATTRIBUTE getLocation2Info(String zipvalue) {
        try {
             db = this.getReadableDatabase();

             cursor = db.query(TABLE_LOCATION2, new String[] { KEY_BLRMK_ID,KEY_BLRMK_ZIP,
                            KEY_BLRMK_LOCATION, KEY_BLRMK_ZIP_VALUE }, KEY_BLRMK_ZIP_VALUE + "=?",
                    new String[] {zipvalue }, null, null, null, null);
            if (cursor != null&& cursor.moveToFirst()){
                blrmkattribute = new BLRMKATTRIBUTE(cursor.getString(1), cursor.getString(2));

            }
        }catch (final Exception e){
            Log.d(DatabaseHandler.class.getSimpleName(),e.getMessage());
        }finally {
            if(cursor!=null)
            cursor.close();
            db.close();
        }
        return blrmkattribute;


//            cursor.moveToFirst();

//        cursor.close();
//        return blrmkattribute;
    }

    public List<ActualBarcode> getActualBarcodeList() {
        List<ActualBarcode> userList = new ArrayList<ActualBarcode>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ACTUAL_BARCODE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ActualBarcode locationInfo = new ActualBarcode();
                locationInfo.setId(Integer.parseInt(cursor.getString(0)));
                locationInfo.setZip(cursor.getString(1));
                locationInfo.setLocation(cursor.getString(2));
                locationInfo.setZipvalue(cursor.getString(3));

                // Adding contact to list
                userList.add(locationInfo);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }

    public List<ExpectedBarcode> getExpectedBarcodeList() {
        List<ExpectedBarcode> userList = new ArrayList<ExpectedBarcode>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXPECTED_BARCODE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ExpectedBarcode locationInfo = new ExpectedBarcode();
                locationInfo.setId(Integer.parseInt(cursor.getString(0)));
                locationInfo.setZip(cursor.getString(1));
                locationInfo.setLocation(cursor.getString(2));
                locationInfo.setZipvalue(cursor.getString(3));
                // Adding contact to list
                userList.add(locationInfo);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }


}

