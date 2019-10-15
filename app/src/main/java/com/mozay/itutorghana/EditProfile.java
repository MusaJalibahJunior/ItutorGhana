package com.mozay.itutorghana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    EditText nameEditText;
    EditText emailEditText;
    EditText dateofBirthEdithText;
    EditText locationEditText;
    EditText PhoneNumberEditText;
    EditText SosEditText;
    EditText YearsofExpEditText;
    EditText BriefSumEditText;

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

        submitProfileBtn = findViewById(R.id.editProfileBtn);

        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("Userinfo");

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        email =intent.getStringExtra("email");

        dateOfBirth = dateofBirthEdithText.getText().toString();
        location = locationEditText.getText().toString();
        phoneNumber = PhoneNumberEditText.getText().toString();
        sos = SosEditText.getText().toString();
        yearsOfExp = YearsofExpEditText.getText().toString();
        briefSum = BriefSumEditText.getText().toString();

        nameEditText.setText(name);
        emailEditText.setText(email);
        dateofBirthEdithText.setText(dateOfBirth);
        locationEditText.setText(location);
        PhoneNumberEditText.setText(phoneNumber);
        SosEditText.setText(sos);
        YearsofExpEditText.setText(yearsOfExp);
        BriefSumEditText.setText(briefSum);

        submitProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadUserInfo();
            }
        });
    }

    private void uploadUserInfo() {

       list = new ArrayList<>();
       personalInfo = new PersonalInfo();

       personalInfo.setTeacherName(name);
       personalInfo.setTeacherEmail(email);
       personalInfo.setTeacherDateofbirth(dateOfBirth);
       personalInfo.setTeacherlocation(location);
       personalInfo.setPhonenum(phoneNumber);
       personalInfo.setTeachersubjectofesp(sos);
       personalInfo.setTeacheryearsofExp(yearsOfExp);
       personalInfo.setTeacherbefs(briefSum);

       list.add(personalInfo);

       myRef.setValue(personalInfo);
    }
}
