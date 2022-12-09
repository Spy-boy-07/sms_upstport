package com.spydison.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UserList extends AppCompatActivity {
    boolean fff = false;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    MainActivity obj = new MainActivity();
    //    FloatingActionButton button ;
    FirebaseAuth authcurr = FirebaseAuth.getInstance();
    FirebaseUser currentUser = authcurr.getCurrentUser();
//    String username= currentUser.getEmail();
//    DatabaseReference challref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.userlist_UL);
        database = FirebaseDatabase.getInstance().getReference("users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
//creates challenge branch
//        challref = FirebaseDatabase.getInstance().getReference("challenges");

//        User.class = (String) FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid());

//        FirebaseDatabase database123 = FirebaseDatabase.getInstance();
//
//        DatabaseReference reference = database123.getReference("users").child(currentUser.getUid());


        if(obj.fff)
        {
            FloatingActionButton button = findViewById(R.id.challButt);
            button.setVisibility(View.VISIBLE);

//            ImageView tick = findViewById(R.id.checkIt);
//            tick.setVisibility(View.VISIBLE);
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        User user = dataSnapshot.getValue(User.class);
                        if (!Objects.equals(user.email, currentUser.getEmail()))
                            list.add(user);
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        else
        {
            FloatingActionButton button = findViewById(R.id.challButt);
            ImageView tick = findViewById(R.id.checkIt);
            TextView txt = findViewById(R.id.updatetxt);
            txt.setText("Rank List");
            button.setVisibility(View.GONE);
//            tick.setVisibility(View.VISIBLE);
            database.orderByChild("MatchesWon").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        User user = dataSnapshot.getValue(User.class);
                        list.add(0,user);
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

//726492
//If you uncomment the portion after this and then run it, IT crashes...
        //Please someone find a way
//        button.findViewById(R.id.challButt);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (myAdapter.getSelected() != null){
//                    Toast.makeText(UserList.this, myAdapter.getSelected().getName()+" Challenged", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(UserList.this, "No Selection", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("chalenj");
    DatabaseReference oldRef = FirebaseDatabase.getInstance().getReference("users");
    //    Challenges challenge = new Challenges(currentUser, myAdapter.getSelected());
    String name = "";
    public void challenged(View objectView) {
        if (obj.fff){
            oldRef.child(currentUser.getUid()).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    name = snapshot.getValue(String.class);
//                if(obj.fff) {
                    add_data(name);
//                }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }
    }

    HashMap<String,Object> aa = new HashMap<String,Object>();

    private void add_data(String name){
        if (myAdapter.getSelected() != null){
            Toast.makeText(UserList.this, "Challenged to "+myAdapter.getSelected().name, Toast.LENGTH_SHORT).show();
            myRef.child("challenge").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    aa = (HashMap<String,Object>)snapshot.getValue();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            aa.put(myAdapter.getSelected().getUsername(),name);
            myRef.child("challenge").updateChildren(aa);
        }
        else{
            Toast.makeText(UserList.this, "No Selection", Toast.LENGTH_SHORT).show();
        }
    }
}