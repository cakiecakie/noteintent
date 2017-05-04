package com.cakiecakie.noteintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

public class NotePagerActivity extends FragmentActivity {

    private static final String EXTRA_NOTE_ID =
            "com.cakiecakie.noteintent.note_id";

    private ViewPager mViewPager;
    private List<Note> mNotes;

    public static Intent newIntent(Context packageContext, UUID noteId) {
        Intent intent = new Intent(packageContext, NotePagerActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pager);
        UUID noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_note_pager_view_pager);
        mNotes = NoteLab.get(this).getNotes();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Note note = mNotes.get(position);
                return NoteFragment.newInstance(note.getId());
            }

            @Override
            public int getCount() {
                return mNotes.size();
            }
        });
    }

}
