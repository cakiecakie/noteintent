package com.cakiecakie.noteintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by naehokushin on 17/5/7.
 */

public class NoteBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public NoteBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NoteDBSchema.NoteTable.NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NoteDBSchema.NoteTable.Cols.UUID + ", " +
                NoteDBSchema.NoteTable.Cols.TITLE + ", " +
                NoteDBSchema.NoteTable.Cols.DATE + ", " +
                NoteDBSchema.NoteTable.Cols.SOLVED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
