package com.example.notes_roomdb.DL.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notes_roomdb.DL.DAO.INoteDao;
import com.example.notes_roomdb.DL.Entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase{
    public abstract INoteDao noteDao();
}
