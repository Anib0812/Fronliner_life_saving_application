/**
        * This is an single click live saving app which will help the user to send the message to near ones also to the database
        *
        *It sends the user's longitude and latidude along with the message
        * This project was selected for Smart India hackathon 2022
        *
        *
        * Author: Aniket Biswal
        * Date: March 22,2022
        */
package com.example.nfrontliner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MainActivity.class==null){
                    return;
                }

                Intent intent = new Intent(getApplicationContext(),registern.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }
}