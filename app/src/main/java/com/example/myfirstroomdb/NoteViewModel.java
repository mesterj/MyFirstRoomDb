package com.example.myfirstroomdb;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class NoteViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private NoteDAO noteDAO;
    private NoteRoomDatabase noteRoomDatabase;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRoomDatabase = NoteRoomDatabase.getInstance(application);
        noteDAO = noteRoomDatabase.noteDAO();
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDAO).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"ViewModel Destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void,Void> {

        NoteDAO mNoteDao;

        public InsertAsyncTask(NoteDAO mNoteDao) {
            this.mNoteDao = mNoteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }
}
