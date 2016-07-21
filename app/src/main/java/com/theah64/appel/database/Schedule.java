package com.theah64.appel.database;

import android.content.Context;

/**
 * Created by theapache64 on 21/7/16.
 */
public class Schedule extends BaseTable {

    private static Schedule instance;

    private Schedule(Context context) {
        super(context);
    }

    public static Schedule getInstance(final Context context) {
        if (instance == null) {
            instance = new Schedule(context);
        }
        return instance
    }
}
