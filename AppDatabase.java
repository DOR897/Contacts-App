package com.example.contactsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class, Contacts.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ContactDao contactDao();

    public static AppDatabase getInstance(Context context) {
        AppDatabase instance = null;
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return instance;
    }
}
