package com.example.notes_roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes_roomdb.DL.App;
import com.example.notes_roomdb.DL.DAO.INoteDao;
import com.example.notes_roomdb.DL.DB.NoteDataBase;
import com.example.notes_roomdb.DL.Entity.Note;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NoteEditorActivity extends AppCompatActivity implements View.OnClickListener {


    private NoteDataBase db;
    private INoteDao noteDao;

    private EditText etNoteSubject;
    private EditText etNoteBody;
    private Button btnSaveNote;
    private Button btnDeleteNote;
    private Button btnCancelNote;
    private Note noteToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        initViews();
        setOnClickListeners();
        takeNote();

        db = App.getInstance().getDatabase();
        noteDao = db.noteDao();
    }

    private void takeNote() {
        Bundle intentData = this.getIntent().getExtras();

        if(intentData != null){
            noteToEdit = (Note)intentData.get("note");

            etNoteSubject.setText(noteToEdit.getSubject());
            etNoteBody.setText(noteToEdit.getBody());

        }
    }

    private void setOnClickListeners() {
        btnSaveNote.setOnClickListener(this);
        btnDeleteNote.setOnClickListener(this);
        btnCancelNote.setOnClickListener(this);
    }

    private void initViews() {
        etNoteSubject = findViewById(R.id.etNoteSubject);
        etNoteBody = findViewById(R.id.etNoteBody);
        btnSaveNote = findViewById(R.id.btnSaveNote);
        btnDeleteNote = findViewById(R.id.btnDeleteNote);
        btnCancelNote = findViewById(R.id.btnCancelNote);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSaveNote:{
                saveNote();
                break;
            }
            case R.id.btnDeleteNote:{
                deleteNote();
                break;
            }
            case R.id.btnCancelNote:{
                break;
            }
        }
        finish();
    }

    private void deleteNote() {

        if(noteToEdit != null){
            noteDao.delete(noteToEdit);
            final String msg = "Note with #id" + noteToEdit.getId() + " has been deleted!";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
        final String msg = "Note with #id" + noteToEdit.getId() + " has been not find!";
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

    private void saveNote() {

       if(noteToEdit == null){
           noteToEdit = new Note();
           long id = 0;
           List<Note> notes = noteDao.getAllNotes();
           if(notes.size() > 0){
              id = notes.get(notes.size()-1).getId() + 1;
           }

           noteToEdit.setId(id);
           noteToEdit.setSubject(etNoteSubject.getText().toString());
           noteToEdit.setBody(etNoteBody.getText().toString());

           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();

           noteToEdit.setDate(dtf.format(now));

           noteDao.put(noteToEdit);
       }else{
           noteToEdit.setSubject(etNoteSubject.getText().toString());
           noteToEdit.setBody(etNoteBody.getText().toString());

           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();

           noteToEdit.setDate(dtf.format(now));

           noteDao.update(noteToEdit);
       }



    }

}