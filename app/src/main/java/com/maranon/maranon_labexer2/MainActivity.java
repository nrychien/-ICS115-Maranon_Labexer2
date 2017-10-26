package com.maranon.maranon_labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText etUname,etPwd;
    Button btnShared, btnInternal, btnNext;
    FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUname = (EditText)findViewById(R.id.username);
        etPwd = (EditText)findViewById(R.id.password);
        btnInternal = (Button)findViewById(R.id.btnInternal);
        btnShared = (Button)findViewById(R.id.btnShared);
        btnNext = (Button)findViewById(R.id.btnNext);

    }


    public void saveInternal(View view){
        String user = etUname.getText().toString();
        String password = etPwd.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write("User:".getBytes());
            fos.write(user.getBytes());
            fos.write("\nPassword:".getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully saved in Internal Storage ", Toast.LENGTH_LONG).show();
    }
    public void saveShared (View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("etUser", etUname.getText().toString());
        editor.putString("etPassword", etPwd.getText().toString());
        editor.commit();
        Toast.makeText(this, "Successfully saved in Shared Preferences", Toast.LENGTH_LONG).show();
    }


    public void displaymsg (View view) {
        Intent intent = new Intent (this,Main2Activity.class);
        startActivity(intent);
    }
}
