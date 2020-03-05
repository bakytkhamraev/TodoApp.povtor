package com.example.todoapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.example.todoapp.ui.home.TransitionActivity;
import com.example.todoapp.ui.onBoard.OnBoardActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private final int RC_WRITE_EXTERNAL=101;
    EditText editText,editText2;
    TextView nameHeader, emailHeader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isShown=Prefs.getInstance(this).isShown();
        editText2=findViewById(R.id.editText);
        nameHeader=findViewById(R.id.nameHeader);
        nameHeader=findViewById(R.id.emailHeader);


        if (!isShown){startActivity(new Intent(this, OnBoardActivity.class));
        finish();
        return;
        }

        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(this, PhoneActivity.class));
            finish();
            return;

        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,FormActivity.class),100);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        initFile("");


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case (R.id.action_settings):
                Prefs.getInstance(this).delete();
                startActivity(new Intent(this, OnBoardActivity.class));
            case (R.id.action_size) :
                startActivityForResult(new Intent(MainActivity.this,SizeActivity.class),202);
            case R.id.imageView:
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));

        break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @AfterPermissionGranted(RC_WRITE_EXTERNAL)
    public void initFile(String notes){
        String permission= Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if(EasyPermissions.hasPermissions(this,permission)) {
            File folder = new File(Environment.getExternalStorageDirectory(), "ToDoApp6");
            folder.mkdirs();
            File file=new File(folder,"note.txt");
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(notes.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            EasyPermissions.requestPermissions(this,"Разреши!",RC_WRITE_EXTERNAL,permission);
        }
    }

    @Override
    public void onBackPressed() {
        initFile(editText.getText().toString());
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment navHostFragment=getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        for (Fragment fragment:navHostFragment.getChildFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode,resultCode,data);
        }
        if(requestCode==303){
            nameHeader.setText(data.getStringExtra("name"));
            emailHeader.setText(data.getStringExtra("email"));
        }
    }
    public void animateIntent(View view) {

        Intent intent = new Intent(this, TransitionActivity.class);
        String transitionName = getString(R.string.transition_string);
        View start = view.findViewById(R.id.underRecBtn);

        ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                start,   // Starting view
                transitionName    // The String
        );
        //Start the Intent
        ActivityCompat.startActivity(this, intent, options.toBundle());

    }

    public void onProfileClick(View view) {
        startActivity(new Intent(this,ProfileActivity.class));
    }

}


