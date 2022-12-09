package com.spydison.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class equipment extends AppCompatActivity {


    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    DatabaseReference uref,eref;
    EditText hioj;
    Button abc;
    String inp,name;
    TextView no;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        hioj = findViewById(R.id.equip_txt);

//    Log.d("tagg",hioj.getText().toString());




        abc = findViewById(R.id.SUbmitbutt);
        abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inp = hioj.getText().toString();
                uref = FirebaseDatabase.getInstance().getReference("users");
                eref = FirebaseDatabase.getInstance().getReference("Equipment");
                no = findViewById(R.id.exttd);
                if(count==0){
                    no.setText("Request has been sent to the server");
                    count ++;
                }
                else if (count>0){
                    no.setText("Request has been updated to the server");
                    count ++;
                }

                uref.child(user.getUid()).child("username").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name = (String)snapshot.getValue();
                        eref.child("Request").child(name).setValue(inp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });


    }
    public void jji(View view)
    {        inp = hioj.getText().toString();

        uref = FirebaseDatabase.getInstance().getReference("users");
        eref = FirebaseDatabase.getInstance().getReference("Equipment");
                uref.child(user.getUid()).child("username").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name = (String)snapshot.getValue();
                        eref.child("Request").child(name).setValue(inp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
//        mapp.put(name,inp);
//        eref.child("Request").setValue(mapp);
            }






    }

