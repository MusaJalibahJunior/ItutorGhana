package com.mozay.itutorghana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TeachersDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseAuth mAuth;
    FirebaseUser user;

    TextView nameTxtView;
    TextView emailTxtView;
    TextView dateofBirthTextView;
    TextView locationTextView;
    TextView PhoneNumberTextView;
    TextView SosTextView;
    TextView YearsofTextView;
    TextView BreifSumTextView;

    FirebaseDatabase database;
    DatabaseReference myRef;

    Button editProfileBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherdetails);

        toolbar = findViewById(R.id.toolbar);
        nameTxtView = findViewById(R.id.name);
        emailTxtView = findViewById(R.id.email);
        dateofBirthTextView = findViewById(R.id.dateofB);
        locationTextView = findViewById(R.id.loc);
        PhoneNumberTextView = findViewById(R.id.phonenum);
        SosTextView = findViewById(R.id.sos);
        YearsofTextView= findViewById(R.id.yearofEx);
        BreifSumTextView = findViewById(R.id.brefS);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Userinfo");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PersonalInfo personalInfo = dataSnapshot.getValue(PersonalInfo.class);
                String name = personalInfo.getTeacherDateofbirth();

                Toast.makeText(TeachersDetailActivity.this, name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editProfileBtn = findViewById(R.id.editProfileBtn);

        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        final String name = user.getDisplayName();
        final String email = user.getEmail();

        nameTxtView.setText(name);
        emailTxtView.setText(email);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditProfile.class);

                intent.putExtra("name", name);
                intent.putExtra("email", email);

                startActivity(intent);
            }
        });
    }
}

