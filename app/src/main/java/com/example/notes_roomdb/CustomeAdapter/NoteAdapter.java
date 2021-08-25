package com.example.notes_roomdb.CustomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.notes_roomdb.DL.Entity.Note;
import com.example.notes_roomdb.R;


import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {
    List<Note> notes;

    private Context mContext;
    private int mResource;


    public NoteAdapter(Context context, int resource, List<Note> noteList) {
        super(context, resource);
        this.mContext = context;
        this.mResource = resource;
        this.notes = noteList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String subj = getItem(position).getSubject();
        String date = getItem(position).getDate();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvNoteSubject =(TextView) convertView.findViewById(R.id.tvNoteSubject);
        TextView tvNoteDate = (TextView)convertView.findViewById(R.id.tvNoteDate);

        tvNoteSubject.setText(subj);
        tvNoteDate.setText(date);

        return convertView;
    }

    @Override
    public int getCount() {

        return notes.size();
    }

    @Nullable
    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notes.get(position).getId();
    }
}
