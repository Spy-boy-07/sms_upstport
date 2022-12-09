package com.spydison.sms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;
    int checkedPosition = -1;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.useritems,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.name.setText(user.name);
        holder.username.setText(user.username);
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    TextView name,username;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,username;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.NameoUser_ULL);
            username = itemView.findViewById(R.id.Username_ULL);
            imageView = itemView.findViewById(R.id.checkIt);
        }

        void bind(final User user){
            if (checkedPosition == -1){
                imageView.setVisibility(View.GONE);
            }
            else {
                if (checkedPosition == getAdapterPosition()){
                    imageView.setVisibility(View.VISIBLE);
                }
                else{
                    imageView.setVisibility(View.GONE);
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()){
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }

    public User getSelected(){
        if (checkedPosition != -1){
            return list.get(checkedPosition);
        }
        return null;
    }
}