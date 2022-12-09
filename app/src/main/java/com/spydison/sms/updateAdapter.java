package com.spydison.sms;

import static android.graphics.Color.parseColor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class updateAdapter extends RecyclerView.Adapter<updateAdapter.ViewHolderUP> {

    Context context;
    ArrayList<String> list;
    String challenger = "";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser ABC = mAuth.getCurrentUser();

    CardView cardbg;

    DatabaseReference getUser = FirebaseDatabase.getInstance().getReference("users").child(ABC.getUid());
    DatabaseReference getInfo = FirebaseDatabase.getInstance().getReference("chalenj").child("challenge");


    public updateAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public updateAdapter.ViewHolderUP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.updateitems,parent,false);
        return new ViewHolderUP(view);
    }

    @Override
    public void onBindViewHolder(@NonNull updateAdapter.ViewHolderUP holder, int position) {
        String info = list.get(position);

        getUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                challenger = user.username;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getInfo.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(challenger.equals(info)) {
                            holder.updetails.setText("You have a challenge from " + snapshot.child(info).getValue(String.class) );
                            holder.cardView.setBackgroundColor(parseColor("#FFDCAA"));
                        }
                        else{
                            if(challenger.equals(snapshot.child(info).getValue(String.class))){
                                holder.updetails.setText("You have challenged " + info );
                                holder.cardView.setBackgroundColor(parseColor("#AAECFF"));
                            }
                            else {
                                delete(holder.getAdapterPosition());
                            }
                        }

                        //reciever
//                        holder.updetails.setText("You have challenged " + snapshot.child(info).getValue(String.class) );
                            //sender
//                        holder.updetails.setText("You have challenged " + info );
                        //currenUser
//                        holder.updetails.setText("You have challenged " + challenger );

                    }

                    private void delete(int position) {
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());
                        holder.itemView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public int getItemCount() { return list.size(); }

    public class ViewHolderUP extends RecyclerView.ViewHolder {

        TextView Heading,updetails;
        CardView cardView;

        public ViewHolderUP(@NonNull View itemView) {
            super(itemView);
            Heading = itemView.findViewById(R.id.challHead);
            updetails = itemView.findViewById(R.id.challDetail);
            cardView = itemView.findViewById(R.id.updateItems);
        }
    }
}
