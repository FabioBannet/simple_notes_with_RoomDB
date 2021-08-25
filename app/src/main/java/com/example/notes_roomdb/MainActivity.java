package com.example.notes_roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.notes_roomdb.CustomeAdapter.NoteAdapter;
import com.example.notes_roomdb.DL.App;
import com.example.notes_roomdb.DL.DAO.INoteDao;
import com.example.notes_roomdb.DL.DB.NoteDataBase;
import com.example.notes_roomdb.DL.Entity.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnAddNote;
    private ListView gvNotes;
    List<Note> notes;

    private NoteDataBase db;
    private INoteDao noteDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        db = App.getInstance().getDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDB();
        readNotesFromDB();
    }

    private void initDB(){

        noteDao = db.noteDao();
        notes = noteDao.getAllNotes();
    }



    private void initViews() {
        btnAddNote = findViewById(R.id.btnAddNote);
        gvNotes = findViewById(R.id.gvNotes);
        gvNotes.setOnItemClickListener(this);
        btnAddNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddNote:{
                openNoteEditor(-1);
                break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long note_id = parent.getAdapter().getItemId(position);
        openNoteEditor(note_id);
    }




    private void openNoteEditor(long id) {
        Intent noteEditor = new Intent(this, NoteEditorActivity.class);

        if(id >= 0){
            Note note = noteDao.getNoteByID(id);
            noteEditor.putExtra("note", note);
        }

        startActivity(noteEditor);
    }

    private void readNotesFromDB() {

        NoteAdapter adapter = new NoteAdapter(this, R.layout.note_item_layout, notes);

        gvNotes.setAdapter(adapter);
    }
}