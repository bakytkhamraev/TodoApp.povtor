package com.example.todoapp.ui.onBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.MainActivity;
import com.example.todoapp.Prefs;
import com.example.todoapp.R;
import com.google.android.material.tabs.TabLayout;

public class OnBoardActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayoutDots;
    Button btnSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
       viewPager= findViewById(R.id.viewPager);
       viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayoutDots=findViewById(R.id.tabLayoutDots);
        tabLayoutDots.setupWithViewPager(viewPager,true);

        btnSkip=findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.getInstance(OnBoardActivity.this).saveShown();
                startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
                finish();
            }
        });






    }




    }

