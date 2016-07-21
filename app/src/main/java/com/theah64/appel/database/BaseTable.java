package com.theah64.appel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.theah64.appel.utils.FileUtils;

import java.io.IOException;

/**
 * Created by theapache64 on 21/7/16.
 */
public abstract class BaseTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appel.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_SCRIPT_FILE_NAME = "appel.sql";
    private static final String X = BaseTable.class.getSimpleName();
    private final Context context;

    protected BaseTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            final String databaseScript = FileUtils.readTextualAsset(context, SQL_SCRIPT_FILE_NAME);
            final String[] statements = databaseScript.split(";");
            for (final String stmt : statements) {
                if (!stmt.trim().isEmpty()) {
                    Log.d(X, "Executing : " + stmt);
                    db.execSQL(stmt);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to create database: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS schedules;");

        onCreate(db);
    }
}
