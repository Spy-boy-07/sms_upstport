package com.spydison.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    static boolean fff;

    TextView tvname, tvusername, tvemail;
    CardView challenge,profile,request,updates,ranklist,tournament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
//
//        FirebaseDatabase database12 = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database12.getReference("message");
//        myRef.setValue("Hello, World!");
//
        if(currentUser == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

//        tvname = findViewById(R.id.tvName);
//        tvusername = findViewById(R.id.tvUN);
//        tvemail = findViewById(R.id.tvEmail);


//        Button buttlogout = findViewById(R.id.LogOut);
//        buttlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LogoutUser();
//            }
//        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                User user = snapshot.getValue(User.class);
////                if(user != null){
////                    tvname.setText("Name: = " + user.name);
////                    tvemail.setText("E mail: = " + user.email);
////                    tvusername.setText("User name: = " + user.username);
////
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

        //edited
        challenge = (CardView) findViewById(R.id.challenge);
        challenge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                fff = true;
                openUserList();
            }
        });

        profile = (CardView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });

        updates = (CardView) findViewById(R.id.updates);
        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdates();
            }
        });
        request = (CardView) findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest();
            }
        });
        ranklist = findViewById(R.id.ranklist1);
        ranklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fff = false;
                openRlist();
            }
        });
        tournament = (CardView) findViewById(R.id.tournament1);
        tournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTour();
            }
        });




    }

    public void openRequest() {
        Intent neww = new Intent(this,equipment.class);
        startActivity(neww);
    }

    public void openUpdates() {
        Intent ii = new Intent(this,updates.class);
        startActivity(ii);
    }

    private void openProfile() {
        Intent log = new Intent(this,Profile.class);
        startActivity(log);
    }

    public void openUserList() {
        fff=true;
        Intent intent = new Intent(this, UserList.class);

        startActivity(intent);
    }
    public void openRlist() {
        fff = false;
        Intent intent = new Intent(this, UserList.class);

        startActivity(intent);
    }
    public void openTour() {
        Intent intent = new Intent(this, Tournament.class);
        startActivity(intent);
    }

    private void LogoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}