package com.example.myfirstroomdb;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Note.class},version = 1)
public abstract  class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDAO noteDAO();
    public static volatile NoteRoomDatabase noteRoomDatabaseInstance;

    static NoteRoomDatabase getInstance(final Context ctx) {
        if (noteRoomDatabaseInstance == null ) {
            synchronized (NoteRoomDatabase.class) {
                if (noteRoomDatabaseInstance == null) {
                    noteRoomDatabaseInstance =
                            Room.databaseBuilder(ctx.getApplicationContext(),NoteRoomDatabase.class,"note_database").build();
                }
            }
        }
        return noteRoomDatabaseInstance;
    }
}
