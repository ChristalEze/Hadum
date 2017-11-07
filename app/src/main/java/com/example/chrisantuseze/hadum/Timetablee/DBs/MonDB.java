package com.example.chrisantuseze.hadum.Timetablee.DBs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.chrisantuseze.hadum.Timetablee.Days.Contract.DayEntry.*;

/**
 * Created by CHRISANTUS EZE on 30/10/2017.
 */

public class MonDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "mon";
    public static final String DATABASE = "monday.db";
        private static final int VERSION = 1;


        public MonDB(Context context) {
            super(context, DATABASE, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            final String CREATE_TABLE = "CREATE TABLE "  + TABLE_NAME + " (" +
                    _ID                + " INTEGER PRIMARY KEY, " +
                    COURSE + " TEXT , " +
                    VENUE    + " TEXT, " +
                    START_TIME    + " TEXT, " +
                    END_TIME    + " TEXT);";

            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
}
