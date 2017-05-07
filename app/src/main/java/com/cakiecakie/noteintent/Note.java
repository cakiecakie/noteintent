package com.cakiecakie.noteintent;

import java.util.Date;
import java.util.UUID;

/**
 * @function 数据模型
 * Created by naehokushin on 17/5/4.
 */

public class Note {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Note() {
        this(UUID.randomUUID());
    }

    public Note(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Date getDate() {
        return this.mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return this.mSolved;
    }

    public void setSolved(boolean solved) {
        this.mSolved = solved;
    }

}
