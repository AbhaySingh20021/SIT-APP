package com.example.sitpoll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button userlistbutton= findViewById(R.id.button4);
        Button polllist= findViewById(R.id.button5);
        Button logout= findViewById(R.id.button6);

        polllist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Userlist.class);
                intent.putExtra("list","polls");
                startActivity(intent);

            }
        });

        userlistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Userlist.class);
                intent.putExtra("list","students");
                startActivity(intent);
            }
        });


    }
}