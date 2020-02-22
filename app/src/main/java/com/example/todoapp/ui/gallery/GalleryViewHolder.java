package com.example.todoapp.ui.gallery;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showMessage();

            }
        });
    }
    public void showMessage() {
        String s=textView.getText().toString();
        Toast.makeText(itemView.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void onBind(String s) {
        textView.setText(s);
    }
}
