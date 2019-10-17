package com.mozay.itutorghana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    DatabaseReference database;

    EditText nameEditText;
    EditText emailEditText;
    EditText dateofBirthEdithText;
    EditText locationEditText;
    EditText PhoneNumberEditText;
    EditText SosEditText;
    EditText YearsofExpEditText;
    EditText BriefSumEditText;
    EditText NationalityEditText;

    Button submitProfileBtn;

    ArrayList<PersonalInfo> list;
    PersonalInfo personalInfo;

    String name;
    String email;
    String dateOfBirth;
    String location;
    String phoneNumber;
    String sos;
    String yearsOfExp;
    String briefSum;
    String nationality;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        nameEditText = findViewById(R.id.firstN1);
        emailEditText = findViewById(R.id.email);
        dateofBirthEdithText = findViewById(R.id.dateofB1);
        locationEditText = findViewById(R.id.loc1);
        PhoneNumberEditText = findViewById(R.id.phonenum1);
        SosEditText = findViewById(R.id.sos1);
        YearsofExpEditText = findViewById(R.id.yearofEx1);
        BriefSumEditText = findViewById(R.id.brefS1);
        NationalityEditText = findViewById(R.id.nationality1);

        submitProfileBtn = findViewById(R.id.editProfileBtn);

        database = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        email =intent.getStringExtra("email");
        location = intent.getStringExtra("loc");
        phoneNumber = intent.getStringExtra("phone");
        yearsOfExp = intent.getStringExtra("yoe");
        sos = intent.getStringExtra("sos");
        briefSum = intent.getStringExtra("briefSum");
        dateOfBirth = intent.getStringExtra("dob");
        nationality = intent.getStringExtra("nationality");

        nameEditText.setText(name);
        emailEditText.setText(email);
        dateofBirthEdithText.setText(dateOfBirth);
        locationEditText.setText(location);
        PhoneNumberEditText.setText(phoneNumber);
        SosEditText.setText(sos);
        YearsofExpEditText.setText(yearsOfExp);
        BriefSumEditText.setText(briefSum);
        NationalityEditText.setText(nationality);

        submitProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadUserInfo();
            }
        });
    }

    private void uploadUserInfo() {

        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String phoneNumber = PhoneNumberEditText.getText().toString();
        String sos = SosEditText.getText().toString();
        String yearsOfExp = YearsofExpEditText.getText().toString();
        String nationality = NationalityEditText.getText().toString();
        String dateOfBirth = dateofBirthEdithText.getText().toString();
        String briefSum = BriefSumEditText.getText().toString();

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setTeacherName(name);
        personalInfo.setTeacherEmail(email);
        personalInfo.setTeacherlocation(location);
        personalInfo.setPhonenum(phoneNumber);
        personalInfo.setTeachersubjectofesp(sos);
        personalInfo.setTeacheryearsofExp(yearsOfExp);
        personalInfo.setTeacherbefs(briefSum);
        personalInfo.setTeacherNationality(nationality);
        personalInfo.setTeacherDateofbirth(dateOfBirth);

        database.child("Teacher details").setValue(personalInfo);
        finish();
    }
}
