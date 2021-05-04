package com.example.sitpoll.ui.showPolls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sitpoll.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ShowOnePoll extends AppCompatActivity {
    ListView listView;
    FirebaseAuth auth;
    ArrayAdapter arrayAdapter;
    Intent intent;

    ArrayList<String> options = new ArrayList<>();

    public void selectans(int position){

        listView.setItemChecked(position,true);

        String quesUuid= intent.getStringExtra("quesUuid");
        String user_uid= auth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("UserData").child(user_uid).child("pollsanswered").child(quesUuid).setValue(position);

    }

    public void saveanswers(int position){
        String user_uid= auth.getCurrentUser().getUid();
        String quesUuid= intent.getStringExtra("quesUuid");

        switch (position){
            case 0:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("A").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("A").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("A").setValue(1);
                        }


                    }
                });



                break;
            }
            case 1:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("B").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("B").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("B").setValue(1);
                        }


                    }
                });


                break;
            }case 2:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("C").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("C").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("C").setValue(1);
                        }


                    }
                });


                break;
            }
            case 3:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("D").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("D").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("D").setValue(1);
                        }


                    }
                });

                break;

            }
            default: {
                System.out.println("no vale saved");
            break;
            }


        }





    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_poll);
        TextView questiontopic= findViewById(R.id.textView5);

        TextView from= findViewById(R.id.textView7);
        listView = findViewById(R.id.listview1);
        intent = getIntent();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        ArrayList<String> options = new ArrayList<>();
        auth= FirebaseAuth.getInstance();
        String user_uid= auth.getCurrentUser().getUid();

        Intent intent = getIntent();
        String quesUuid= intent.getStringExtra("quesUuid");
         arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,options);
        FirebaseDatabase.getInstance().getReference()
                .child("Polls").child(quesUuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                options.clear();
                questiontopic.setText(Objects.requireNonNull(snapshot.child("question").getValue()).toString());
                from.setText(Objects.requireNonNull(snapshot.child("from").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option1").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option2").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option3").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option4").getValue()).toString());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);

        FirebaseDatabase.getInstance().getReference().child("UserData").child(user_uid).child("pollsanswered").child(quesUuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                long option= (long) snapshot.getValue();
                listView.setItemChecked((int) option,true);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectans(position);
                saveanswers(position);
            }
        });



    }
}
