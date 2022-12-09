//package com.spydison.sms;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import org.checkerframework.checker.units.qual.A;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class ranklist extends AppCompatActivity {
//
//    RecyclerView recyclerView;
//    DatabaseReference uref;
//    ranklist_adapter adapter;
//    ArrayList<User> list;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ranklist);
//
//        recyclerView = findViewById(R.id.rank_acti);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        uref = FirebaseDatabase.getInstance().getReference().child("users");
//
//        list = new ArrayList<>();
//        adapter = new ranklist_adapter(this,list);
//        recyclerView.setAdapter(adapter);
//
////        uref.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for(DataSnapshot abc:snapshot.getChildren())
////                {
////                    User user = abc.getValue(User.class);
////                    list.add(user);
////                }
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
////        uref.addChildEventListener(new ChildEventListener() {
////            @Override
////            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////            }
////
////            @Override
////            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////            }
////
////            @Override
////            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
////
////            }
////
////            @Override
////            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
//
//
//    }
//}