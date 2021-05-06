package com.example.sitpoll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {

    ArrayList<String> userlist = new ArrayList<>();
    ArrayList<String> userid = new ArrayList<>();
    ArrayList<String> pollId = new ArrayList<>();


    ArrayList<String> polllist = new ArrayList<>();
    ArrayList<String> fromId = new ArrayList<String>();
    ArrayAdapter arrayadpapter;
    ListView listView;


    public void longpressdelete(int i){

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(Userlist.this)
                            .setTitle("Delete?")
                            .setMessage("Are you sure you want to Delete?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(i ==0)
                                    deletebase(position,0);

                                    else
                                        deletebase(position,1);
                                    arrayadpapter.notifyDataSetChanged();


                                }
                            }).create().show();

                    return true;
                }
            });
        }




    public void deletebase(int position,int i){
        if(i==0) {
            userlist.remove(position);

            FirebaseDatabase.getInstance().getReference().child("UserData").child(userid.get(position)).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error != null) {
                        Toast.makeText(Userlist.this, "User Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            userid.remove(position);
        }
        else if(i==1) {
            polllist.remove(position);

            FirebaseDatabase.getInstance().getReference().child("Polls").child(pollId.get(position)).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error != null) {
                        Toast.makeText(Userlist.this, "Poll Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            pollId.remove(position);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
         listView= findViewById(R.id.lists);
        Intent intent = getIntent();
        userid.clear();
        userlist.clear();
        pollId.clear();
        polllist.clear();
        fromId.clear();
        if(intent.getStringExtra("list").equals("polls")){

            FirebaseDatabase.getInstance().getReference().child("Polls").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    String question = snapshot.child("question").getValue().toString();
                    polllist.add(question);
                    pollId.add(snapshot.getKey().toString());

                    arrayadpapter= new ArrayAdapter( Userlist.this,android.R.layout.simple_list_item_1,polllist);
                    listView.setAdapter(arrayadpapter);

                    arrayadpapter.notifyDataSetChanged();

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

            longpressdelete(1);


        }
        else {

            FirebaseDatabase.getInstance().getReference().child("UserData").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    String username = snapshot.child("name").getValue().toString();
                    String useid = snapshot.getKey();
                    fromId.add(snapshot.child("email").getValue().toString());
                    userid.add(useid);
                    userlist.add(username);
                    arrayadpapter = new ArrayAdapter( Userlist.this,android.R.layout.simple_list_item_1,userlist);
                    listView.setAdapter(arrayadpapter);
                    arrayadpapter.notifyDataSetChanged();

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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent1= new Intent(getApplicationContext(),ViewStudentInfo.class);
                    intent1.putExtra("username",userid.get(position));
                    intent1.putExtra("from",fromId.get(position));

                    startActivity(intent1);
                }
            });

            longpressdelete(0);

        }


    }
}