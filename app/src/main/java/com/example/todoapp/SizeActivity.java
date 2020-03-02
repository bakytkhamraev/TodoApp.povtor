package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SizeActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton button14,button22,button28;
    EditText editText;
    Intent intent;
    float sizeOfText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);
        intent=new Intent();
        editText=findViewById(R.id.editText);
        radioGroup=findViewById(R.id.radio_group_size);
        button14=findViewById(R.id.size_14);
        button22=findViewById(R.id.size_22);
        button28=findViewById(R.id.size_28);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton button=(RadioButton) v;
                switch(button.getId()){
                    case R.id.size_14:editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
                        intent.putExtra("sizeOfText", R.dimen.first_size);
                        setResult(RESULT_OK,intent);
                        startActivity(intent);
                        break;
                    case R.id.size_22:
                        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,22);
                        sizeOfText = editText.getTextSize();
                        intent.putExtra("sizeOfText", R.dimen.second_size);
                        setResult(RESULT_OK,intent);
                        startActivity(intent);
                        break;
                    case R.id.size_28:
                        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,28);
                        sizeOfText = editText.getTextSize();
                        intent.putExtra("sizeOfText", R.dimen.third_size);
                        setResult(RESULT_OK,intent);
                        startActivity(intent);
                        break; }
                startActivityForResult(intent,500);
                finish();


            }
        };

        button14.setOnClickListener(onClickListener);
        button22.setOnClickListener(onClickListener);
        button28.setOnClickListener(onClickListener);



    }

}
