package com.example.sitpoll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {

    ArrayList<String> userlist = new ArrayList<>();
    ArrayList<String> polllist = new ArrayList<>();
    ArrayList<String> quesUuid = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        ListView listView= findViewById(R.id.lists);


        Intent intent = getIntent();
        if(intent.getStringExtra("list").equals("polls")){

            FirebaseDatabase.getInstance().getReference().child("Polls").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    String question = snapshot.child("question").getValue().toString();
                    polllist.add(question);
                    ArrayAdapter arrayadpapter= new ArrayAdapter( Userlist.this,android.R.layout.simple_list_item_1,polllist);
                    listView.setAdapter(arrayadpapter);

                    arrayadpapter.notifyDataSetChanged();
                    quesUuid.add(snapshot.getKey().toString());

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                @Override
                public void onCancelled(@NonNull DatabaseError error) { }
            });


        }
        else {
            FirebaseDatabase.getInstance().getReference().child("UserData").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    String question = snapshot.child("name").getValue().toString();
                    userlist.add(question);
                    ArrayAdapter arrayadpapter= new ArrayAdapter( Userlist.this,android.R.layout.simple_list_item_1,userlist);
                    listView.setAdapter(arrayadpapter);
                    arrayadpapter.notifyDataSetChanged();
                    quesUuid.add(snapshot.getKey().toString());

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                @Override
                public void onCancelled(@NonNull DatabaseError error) { }
            });

        }
    }
}