package com.cakiecakie.noteintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cakiecakie.noteintent.database.NoteBaseHelper;
import com.cakiecakie.noteintent.database.NoteCursorWrapper;
import com.cakiecakie.noteintent.database.NoteDBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by naehokushin on 17/5/4.
 */

public class NoteLab {
    private static NoteLab sNoteLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private static ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteDBSchema.NoteTable.Cols.UUID, note.getId().toString());
        values.put(NoteDBSchema.NoteTable.Cols.TITLE, note.getTitle());
        values.put(NoteDBSchema.NoteTable.Cols.DATE, note.getDate().getTime());
        values.put(NoteDBSchema.NoteTable.Cols.SOLVED, note.isSolved() ? 1 : 0);
        return values;
    }

    private NoteLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();

    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        NoteCursorWrapper cursor = queryNote(null, null);//取出所有
        try {
            cursor.moveToFirst();
            while (! cursor.isAfterLast()) {
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return notes;
    }

    public Note getNote(UUID id) {
        NoteCursorWrapper cursor = queryNote(NoteDBSchema.NoteTable.Cols.UUID + "= ?",
                new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getNote();
        } finally {
            cursor.close();
        }
    }

    private NoteCursorWrapper queryNote(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(NoteDBSchema.NoteTable.NAME,
                null, //代表select全部
                whereClause,
                whereArgs,
                null,
                null,
                null
                );
        return new NoteCursorWrapper(cursor);
    }

    public void addNote(Note note) {
        ContentValues values = getContentValues(note);
        mDatabase.insert(NoteDBSchema.NoteTable.NAME, null, values);
    }

    public void update(Note note) {
        String uuidString = note.getId().toString();
        ContentValues values = getContentValues(note);
        mDatabase.update(NoteDBSchema.NoteTable.NAME, values, NoteDBSchema.NoteTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteNote(Note note) {
        String uuidString = note.getId().toString();
        mDatabase.delete(NoteDBSchema.NoteTable.NAME, NoteDBSchema.NoteTable.Cols.UUID + "= ?", new String[]{uuidString});
    }
}
