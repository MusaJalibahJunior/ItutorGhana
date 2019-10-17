package com.mozay.itutorghana;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    TextView nationalityTextView;

    FirebaseDatabase database;
    DatabaseReference myRef;

    Button editProfileBtn;

    String name;
    String dateOfBirth;
    String nationality;
    String location;
    String phoneNumber;
    String email;
    String subjectOfSpecialization;
    String briefSummary;
    String yearsOfExperience;

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
        nationalityTextView = findViewById(R.id.nationality);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Teacher details");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PersonalInfo personalInfo = dataSnapshot.getValue(PersonalInfo.class);

                name = personalInfo.getTeacherName();
                dateOfBirth = personalInfo.getTeacherDateofbirth();
                nationality = personalInfo.getTeacherNationality();
                location = personalInfo.getTeacherlocation();
                phoneNumber = personalInfo.getPhonenum();
                email = personalInfo.getTeacherEmail();
                subjectOfSpecialization = personalInfo.getTeachersubjectofesp();
                briefSummary = personalInfo.getTeacherbefs();
                yearsOfExperience = personalInfo.getTeacheryearsofExp();

                nameTxtView.setText(name);
                dateofBirthTextView.setText(dateOfBirth);
                nationalityTextView.setText(nationality);
                locationTextView.setText(location);
                PhoneNumberTextView.setText(phoneNumber);
                emailTxtView.setText(email);
                SosTextView.setText(subjectOfSpecialization);
                BreifSumTextView.setText(briefSummary);
                YearsofTextView.setText(yearsOfExperience);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editProfileBtn = findViewById(R.id.editProfileBtn);

        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);

                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("dob", dateOfBirth);
                intent.putExtra("nationality", nationality);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("loc", location);
                intent.putExtra("sos", subjectOfSpecialization);
                intent.putExtra("yoe", yearsOfExperience);
                intent.putExtra("briefSum", briefSummary);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.logout) {
            mAuth.signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

