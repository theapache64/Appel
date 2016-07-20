package com.theah64.appel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by theapache64 on 21/7/16.
 */
public abstract class BaseTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appel.db";
    private static final int DATABASE_VERSION = 1;

    protected BaseTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
