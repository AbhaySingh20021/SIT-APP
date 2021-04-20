package com.example.sitpoll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText email;
    Intent intent;

    EditText prn;

    public void signup(View view){

        if(username.getText().toString().matches("") || password.getText().toString().matches("") ){
            Toast.makeText(SignupActivity.this, "Username or Password required", Toast.LENGTH_SHORT).show();


        }
        else{
            ParseUser user= new ParseUser();
            System.out.println("Signup code");
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());
            user.put("PRN",prn.getText().toString());
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        System.out.println("PRN updated");
                    }
                    else  e.printStackTrace();
                }
            });

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Toast.makeText(SignupActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(SignupActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());

                    }
                }
            });
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username= findViewById(R.id.personName);
        password= findViewById(R.id.password);
        prn= findViewById(R.id.prn1);
        email = findViewById(R.id.email);
         intent= new Intent(getApplicationContext(),Loginactivity.class);



    }
}