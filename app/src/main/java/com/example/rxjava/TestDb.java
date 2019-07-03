package com.example.rxjava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Book.class, version = 1, exportSchema = false)
public abstract class TestDb extends RoomDatabase {
    private static TestDb INSTANCE;

    public abstract BooksDao booksDao();

    public static TestDb getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, TestDb.class, "book_database")
                    // To simplify the codelab, allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}