package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;


public class SizeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);
        RadioGroup radioGroup=findViewById(R.id.radio_group_size);
        radioGroup.setOnCheckedChangeListener(this);
}

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int size=14;
        switch (checkedId){
            case R.id.size_14:
                size=14;
                break;
            case R.id.size_22:
                size=22;
                break;
            case R.id.size_28:
                size=28;
                break;
        }
        Intent intent=new Intent();
        intent.putExtra("sizeOfText",size);
        setResult(RESULT_OK,intent);
        finish();
    }

}
