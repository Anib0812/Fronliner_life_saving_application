package com.example.nfrontliner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

public class registern extends AppCompatActivity {
    EditText fname,lname,age1,bg1,gen1,email,ph1,emph1;
    Button register;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_FNAME="name";
    private static final String KEY_LNAME="lname";
    private static final String KEY_AGE1="age1";
    private static final String KEY_BG1="bg1";
    private static final String KEY_GEN1="gen";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PH1="ph1";
    private static final String KEY_EMPH1="emph1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registern);
        fname=findViewById(R.id.name);
        lname=findViewById(R.id.ln);
        age1=findViewById(R.id.age);
        bg1=findViewById(R.id.bg);
        gen1=findViewById(R.id.gen);
        email=findViewById(R.id.em);
        ph1=findViewById(R.id.ph);
        emph1=findViewById(R.id.emph);
        register=findViewById(R.id.regi);
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name=sharedPreferences.getString(KEY_FNAME,null);
        if(name!=null) {
            Intent intent = new Intent(registern.this, emerb.class);
            startActivity(intent);
        }
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(KEY_FNAME,fname.getText().toString());
                    editor.putString(KEY_LNAME,lname.getText().toString());
                    editor.putString(KEY_AGE1,age1.getText().toString());
                    editor.putString(KEY_BG1,bg1.getText().toString());
                    editor.putString(KEY_EMAIL,email.getText().toString());
                    editor.putString(KEY_GEN1,gen1.getText().toString());
                    editor.putString(KEY_PH1,ph1.getText().toString());
                    editor.putString(KEY_EMPH1,emph1.getText().toString());
                    editor.apply();
                    Intent intent=new Intent(registern.this,emerb.class);
                    startActivity(intent);
                    Toast.makeText(registern.this, "Success", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
