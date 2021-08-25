package com.example.notes_roomdb.DL;

import android.app.Application;

import androidx.room.Room;

import com.example.notes_roomdb.DL.DB.NoteDataBase;

public class App extends Application {

    private NoteDataBase database;

    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this,
                NoteDataBase.class,
                "noteBook")
                .allowMainThreadQueries().build();
    }

    public static  App getInstance(){
        return instance;
    }

    public NoteDataBase getDatabase(){
        return database;
    }
}
