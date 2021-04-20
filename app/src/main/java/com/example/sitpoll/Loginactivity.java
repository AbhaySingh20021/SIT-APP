package com.example.sitpoll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Loginactivity extends AppCompatActivity {
    EditText username2;
    EditText password;


    public void login(View view){


        if(username2.getText().toString().matches("") || password.getText().toString().matches("") ){
            Toast.makeText(this, "Username or Password required", Toast.LENGTH_SHORT).show();

        }
        else {
            ParseUser.logInInBackground(username2.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Toast.makeText(Loginactivity.this, "Logged In", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),mainvoteactivity.class);
                        startActivity(intent);

                    } else Toast.makeText(Loginactivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        username2= findViewById(R.id.personName2);
        password= findViewById(R.id.password2);




    }
}