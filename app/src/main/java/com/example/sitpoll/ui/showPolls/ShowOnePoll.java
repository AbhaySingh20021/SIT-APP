package com.example.sitpoll.ui.showPolls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sitpoll.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ShowOnePoll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_poll);
        TextView questiontopic= findViewById(R.id.textView5);
        TextView from= findViewById(R.id.textView7);
        ListView listView = findViewById(R.id.listview1);
        ArrayList<String> options = new ArrayList<>();
        Intent intent = getIntent();
        String quesUuid= intent.getStringExtra("quesUuid");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,options);
        FirebaseDatabase.getInstance().getReference()
                .child("Polls").child(quesUuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questiontopic.setText(Objects.requireNonNull(snapshot.child("question").getValue()).toString());
                from.setText(Objects.requireNonNull(snapshot.child("from").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option1").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option2").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option3").getValue()).toString());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);


    }
}