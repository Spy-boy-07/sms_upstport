package com.spydison.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class updates extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    updateAdapter upAdapter;
    ArrayList<String> list;

    DatabaseReference chall,user;

    FirebaseAuth ji = FirebaseAuth.getInstance();
    FirebaseUser ko = ji.getCurrentUser();

    String name ="jk";
    String output="";
    HashMap<String,Object> mapp = new HashMap<String,Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        recyclerView = findViewById(R.id.updatesList);
        database = FirebaseDatabase.getInstance().getReference("chalenj").child("challenge");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        upAdapter = new updateAdapter(this,list);
        recyclerView.setAdapter(upAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    list.add(dataSnapshot.getKey());
                }
                upAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    interface FirebaseData
    {
        void oncallback(String name);
    }

//    private void readdata(FirebaseData firebaseData)
//    {
//        chall = FirebaseDatabase.getInstance().getReference("chalenj");
//        user = FirebaseDatabase.getInstance().getReference("users");
//        user.child(ko.getUid()).child("username").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                name = snapshot.getValue(String.class);
//
//                firebaseData.oncallback(name);
//
//                chall.child("challenge").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        output = (String)snapshot.getValue();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}