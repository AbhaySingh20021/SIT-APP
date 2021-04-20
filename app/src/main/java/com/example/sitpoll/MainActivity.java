package com.example.sitpoll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
      public void loginactivity(View View){
        Intent intent= new Intent(getApplicationContext(),Loginactivity.class);
        startActivity(intent);

    }

    public void signupactivity(View View){
        Intent intent= new Intent(getApplicationContext(),SignupActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}