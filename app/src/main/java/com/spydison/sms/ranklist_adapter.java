//package com.spydison.sms;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ranklist_adapter extends RecyclerView.Adapter<ranklist_adapter.RListHolder> {
//
//    Context context;
//    ArrayList<User>list;
//    public ranklist_adapter(Context context, ArrayList<User>list)
//    {
//        this.context=context;
//        this.list=list;
//    }
//
////    @NonNull
////    @Override
////    public Myvieww onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View v = LayoutInflater.from(context).inflate(R.layout.rank_item,parent,false);
////        return Myvieww(v);
////    }
//
//    @NonNull
//    @Override
//    public RListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.rank_item,parent,false);
//        return new RListHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RListHolder holder, int position) {
//        User user = list.get(position);
//        holder.name.setText(user.getName());
//        holder.username.setText(user.getUsername());
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//    public class RListHolder extends RecyclerView.ViewHolder{
//        TextView name,username;
//        public RListHolder(@NonNull View view) {
//            super(view);
//
//            name = view.findViewById(R.id.nameRL);
//            username = view.findViewById(R.id.userRL);
//        }
//
//
//
//    }
//}