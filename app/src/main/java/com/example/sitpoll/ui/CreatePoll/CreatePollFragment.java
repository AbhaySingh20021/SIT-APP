package com.example.sitpoll.ui.CreatePoll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sitpoll.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreatePollFragment extends Fragment {
   EditText question;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;
    TextView createtv;

    FirebaseDatabase mAuth;
    String pollId= UUID.randomUUID().toString();

    public void createpoll(){
        Map<String,String> maps= new HashMap<>();
        maps.put("from", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        maps.put("question",question.getText().toString());
        maps.put("option1",option1.getText().toString());
        maps.put("option2",option2.getText().toString());
        maps.put("option3",option3.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Polls").child(pollId).setValue(maps);

    }

    private CreatePOllModel createPOllModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        createPOllModel = new ViewModelProvider(this).get(CreatePOllModel.class);

        View root = inflater.inflate(R.layout.fragment_poll, container, false);

         option1 = root.findViewById(R.id.option1);
        option2= root.findViewById(R.id.option2);
        option3= root.findViewById(R.id.option3);
        question= root.findViewById(R.id.question);
        mAuth= FirebaseDatabase.getInstance();
        createtv =root.findViewById(R.id.createtv);
        createtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createpoll();
            }
        });
        createPOllModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;

    }




}