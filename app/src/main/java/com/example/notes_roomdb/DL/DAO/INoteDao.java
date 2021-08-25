package com.example.notes_roomdb.DL.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes_roomdb.DL.Entity.Note;

import java.util.List;

@Dao
public interface INoteDao {

    @Query("SELECT * FROM  note")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE id = :id ")
    Note getNoteByID(long id);

    @Insert
    void put(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}
