package com.spydison.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class Tournament extends AppCompatActivity {



    Button buttont;
    final String jio = "registered";
    HashMap<String,Object> abb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);


    }

    String name;
    public void dothis(View view)
    {
        DatabaseReference tref = FirebaseDatabase.getInstance().getReference("Tournament");
        DatabaseReference uref = FirebaseDatabase.getInstance().getReference("users");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        uref.child(user.getUid()).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = (String)snapshot.getValue();




//                    tref.addValueEventListener(new ValueEventListener() {
//                        @Override
////                        public void onDataChange(@NonNull DataSnapshot snapshot) {
////                            abb = (HashMap<String,Object>)snapshot.getValue();
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                Toast.makeText(Tournament.this, "Registered", Toast.LENGTH_SHORT).show();

                tref.push().setValue(name);
//                    abb.put(name,jio);
//                    tref.setValue(abb);
                }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}