package com.maranon.maranon_labexer2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    Button btnLoadShared, btnLoadInternal, btnClear, btnBack;
    TextView displayET;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnLoadShared = (Button)findViewById(R.id.btnLoadShared);
        btnLoadInternal = (Button)findViewById(R.id.btnLoadInternal);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnBack = (Button)findViewById(R.id.btnBack);
        displayET = (TextView) findViewById(R.id.textView3);
    }

    public void loadShared (View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String  user = sharedPreferences.getString("etUser", "08:00") ;
        String  password = sharedPreferences.getString("etPassword", "08:00") ;
        displayET.setText("Username is:"+ user + " \nPassword is:" +password);
    }

    public void loadInternal (View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while((read =fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayET.setText(buffer.toString());
    }
    public void clear(View view){
        displayET.setText("");
    }


    public void mainpage (View view) {
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }
}
