package com.cakiecakie.noteintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.cakiecakie.noteintent.Note;

import java.util.Date;
import java.util.UUID;

/**
 * @function Cursor的封装类，用于添加新的方法
 *
 * Created by naehokushin on 17/5/7.
 */

public class NoteCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {
        String uuidString = getString(getColumnIndex(NoteDBSchema.NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteDBSchema.NoteTable.Cols.TITLE));
        long date = getLong(getColumnIndex(NoteDBSchema.NoteTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(NoteDBSchema.NoteTable.Cols.SOLVED));
        Note note = new Note(UUID.fromString(uuidString));
        note.setTitle(title);
        note.setDate(new Date(date));
        note.setSolved(isSolved != 0);
        return note;
    }
}
