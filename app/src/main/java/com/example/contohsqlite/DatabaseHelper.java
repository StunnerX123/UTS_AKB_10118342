package com.example.contohsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "CATATAN";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "jcatatan";
    public static final String KATEGORI = "kategori";
    public static final String DESC = "description";
    public static final String DATE = "tanggal";

    // Database Information
    static final String DB_NAME = "DB_CATATAN_FINAL.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + KATEGORI + " TEXT, " + DESC + " TEXT, " + DATE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
//Pengerjaan 5/06/2021