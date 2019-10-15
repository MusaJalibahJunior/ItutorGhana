package com.mozay.itutorghana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button summitBtn;

    EditText nameEditText;
    EditText emailEditText;
    EditText dateofBirthEdithText;
    EditText locationEditText;
    EditText PhoneNumberEditText;
    EditText SosEditText;
    EditText YearsofExpEditText;
    EditText BriefSumEditText;

    TextInputLayout nameTextInputLayout;
    TextInputLayout emailTextInputLayout;
    TextInputLayout dobTextInputLayout;
    TextInputLayout locationTextInputLayout;
    TextInputLayout phoneTextInputLayout;
    TextInputLayout nationalityTextInputLayout;
    TextInputLayout sosTextInputLayout;
    TextInputLayout yearOfExpTextInputLayout;
    TextInputLayout briefSummaryTextInputLayout;

    String name;
    String email;
    String dateOfBirth;
    String location;
    String phoneNumber;
    String sos;
    String yearsOfExp;
    String briefSum;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    boolean NameNotEmpty;
    boolean EmailNotEmpty;
    boolean dateOfBirthNotEmpty;
    boolean LocationNotEmpty;
    boolean PhoneNotEmpty;
    boolean SosNotEmpty;
    boolean YearOfExpNotEmpty;
    boolean BriefSumNotEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        summitBtn = findViewById(R.id.SummitBtn);
        nameEditText = findViewById(R.id.firstN1);
        emailEditText = findViewById(R.id.email);
        dateofBirthEdithText = findViewById(R.id.dateofB1);
        locationEditText = findViewById(R.id.loc1);
        PhoneNumberEditText = findViewById(R.id.phonenum1);
        SosEditText = findViewById(R.id.sos1);
        YearsofExpEditText = findViewById(R.id.yearofEx1);
        BriefSumEditText = findViewById(R.id.brefS1);

        nameTextInputLayout = findViewById(R.id.firstNameInputLayout);
        emailTextInputLayout = findViewById(R.id.emailInputLayout);
        dobTextInputLayout = findViewById(R.id.dobInputLayout);
        locationTextInputLayout = findViewById(R.id.locationinputLayout);
        phoneTextInputLayout = findViewById(R.id.PhonenumberinputLayout);
        sosTextInputLayout = findViewById(R.id.SoSinputLayout);
        yearOfExpTextInputLayout = findViewById(R.id.ExPinputLayout);
        briefSummaryTextInputLayout = findViewById(R.id.brefsaminputLayout);
        nationalityTextInputLayout = findViewById(R.id.Nationalityinputlayout);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        name = nameEditText.getText().toString();
        email = emailEditText.getText().toString();
        dateOfBirth = dateofBirthEdithText.getText().toString();
        location = locationEditText.getText().toString();
        phoneNumber = PhoneNumberEditText.getText().toString();
        sos = SosEditText.getText().toString();
        yearsOfExp = YearsofExpEditText.getText().toString();
        briefSum = BriefSumEditText.getText().toString();

        summitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitUserInfo(name, email, dateOfBirth, location, phoneNumber, sos, yearsOfExp,briefSum);
            }
        });
    }

    private void submitUserInfo(String name, String email, String dateOfBirth,
    String location, String phone, String sos, String yoe, String briefSum) {
//
//        if (!name.isEmpty()) {
//            NameNotEmpty = true;
//
//        } else {
//            nameTextInputLayout.setError("Please enter your full name");
//            nameTextInputLayout.setErrorEnabled(true);
//        }
//
//        if (!email.isEmpty()) {
//            EmailNotEmpty = true;
//
//        } else {
//            emailTextInputLayout.setErrorEnabled(true);
//            emailTextInputLayout.setError("Please enter valid email");
//        }
//
//        if (!dateOfBirth.isEmpty()) {
//            dateOfBirthNotEmpty = true;
//
//        } else {
//            dobTextInputLayout.setErrorEnabled(true);
//            dobTextInputLayout.setError("Please enter date of birth");
//        }
//
//        if (!location.isEmpty()) {
//            LocationNotEmpty = true;
//
//        } else {
//            locationTextInputLayout.setErrorEnabled(true);
//            locationTextInputLayout.setError("Please enter location");
//        }
//
//        if (!phoneNumber.isEmpty()) {
//            PhoneNotEmpty = true;
//
//        } else {
//            phoneTextInputLayout.setErrorEnabled(true);
//            phoneTextInputLayout.setError("Please enter phone number");
//        }
//
//        if (!sos.isEmpty()) {
//            SosNotEmpty = true;
//
//        } else {
//            yearOfExpTextInputLayout.setErrorEnabled(true);
//            yearOfExpTextInputLayout.setError("Please enter years of Experience");
//        }
//
//        if (!briefSum.isEmpty()) {
//            BriefSumNotEmpty = true;
//
//        } else {
//            briefSummaryTextInputLayout.setErrorEnabled(true);
//            briefSummaryTextInputLayout.setError("Please enter brief summary");
//        }
//
//        if (NameNotEmpty && EmailNotEmpty && LocationNotEmpty && dateOfBirthNotEmpty && PhoneNotEmpty
//        && SosNotEmpty && YearOfExpNotEmpty && BriefSumNotEmpty) {
//
//
//        }

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setTeacherName(name);
        personalInfo.setTeacherEmail(email);
        personalInfo.setTeacherlocation(location);
        personalInfo.setPhonenum(phone);
        personalInfo.setTeachersubjectofesp(sos);
        personalInfo.setTeacheryearsofExp(yoe);
        personalInfo.setTeacherbefs(briefSum);

        mDatabase.child("Teacher details").setValue(personalInfo);
    }
}
