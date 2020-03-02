package com.example.todoapp.ui.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.todoapp.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class NotesFragment extends Fragment {
    EditText editText;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText=view.findViewById(R.id.editText);
        Intent intent=getActivity().getIntent();
        if(intent!=null){
            int size = intent.getIntExtra("sizeOfText",0);
            editText.setTextSize(size);

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG","onPause");
        save();
    }


    private void save(){
        String text=editText.getText().toString();
        File folder=new File(Environment.getExternalStorageDirectory(),"ToDoApp");
        folder.mkdir();
        File file=new File(folder,"note.txt");
         try {
             FileUtils.writeStringToFile(file,text,"utf-8");
         } catch (IOException e) {
             e.printStackTrace();
         }
     }


}