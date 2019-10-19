package com.mozay.itutorghana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.ViewHolder> {
    private Context context;
    private PersonalInfo personalInfo;
    private ArrayList<PersonalInfo> list;

    public TeachersAdapter(Context context, ArrayList<PersonalInfo> list) {
        this.context = context;
        this.list = list;
    }

    //    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        String name= mData.get(position);
//
//        holder
//
//
//    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.teacherN);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PersonalInfo personalInfo = list.get(position);

        holder.name.setText(personalInfo.getTeacherName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

