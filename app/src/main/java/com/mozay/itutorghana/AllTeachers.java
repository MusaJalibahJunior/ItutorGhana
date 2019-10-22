package com.mozay.itutorghana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllTeachers extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Query query;

    TeachersAdapter adapter;
    ArrayList<PersonalInfo> list;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_teachers);
        recyclerView = findViewById(R.id.mRecyclerView);

        list = new ArrayList<>();

        adapter = new TeachersAdapter(this, list);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Teacher details");
//        query = databaseReference;

        listAllTeachers();
    }

    public void listAllTeachers() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("teachers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PersonalInfo teacher = new PersonalInfo();
                                teacher.setTeacherName(document.getData().get("name").toString());
                                teacher.setTeacherDateofbirth(document.getData().get("dateOfBirth").toString());
                                teacher.setTeacherNationality(document.getData().get("nationality").toString());
                                teacher.setTeacheryearsofExp(document.getData().get("yearsOfExp").toString());
                                teacher.setTeacherlocation(document.getData().get("location").toString());
                                teacher.setTeachersubjectofesp(document.getData().get("sos").toString());
                                teacher.setTeacherbefs(document.getData().get("briefSum").toString());
                                teacher.setTeacherEmail(document.getData().get("email").toString());
                                teacher.setPhonenum(document.getData().get("phoneNumber").toString());
                                Log.i("TTT", teacher.toString());
                                list.add(teacher);




                                Log.d("TINFO", document.getId() + " => " + document.getData());
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w("TINFO", "Error getting documents.", task.getException());
                        }
                    }
                });

//        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}


