package com.mozay.itutorghana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
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
    EditText nationalityEditText;
    EditText passwordEditText;

    TextInputLayout nameTextInputLayout;
    TextInputLayout emailTextInputLayout;
    TextInputLayout dobTextInputLayout;
    TextInputLayout locationTextInputLayout;
    TextInputLayout phoneTextInputLayout;
    TextInputLayout nationalityTextInputLayout;
    TextInputLayout sosTextInputLayout;
    TextInputLayout yearOfExpTextInputLayout;
    TextInputLayout briefSummaryTextInputLayout;
    TextInputLayout passwordTextInputLayout;

    String name;
    String email;
    String dateOfBirth;
    String location;
    String phoneNumber;
    String sos;
    String yearsOfExp;
    String briefSum;
    String nationality;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    boolean NameEmpty = true;
    boolean EmailEmpty = true;
    boolean dateOfBirthEmpty = true;
    boolean LocationEmpty = true;
    boolean PhoneEmpty = true;
    boolean SosEmpty = true;
    boolean YearOfExpEmpty = true;
    boolean BriefSumEmpty = true;
    boolean NationalityEmpty = true;
    boolean PasswordEmpty = true;

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
        nationalityEditText = findViewById(R.id.nationality1);
        passwordEditText = findViewById(R.id.passwordEditText);

        nameTextInputLayout = findViewById(R.id.firstNameInputLayout);
        emailTextInputLayout = findViewById(R.id.emailInputLayout);
        dobTextInputLayout = findViewById(R.id.dobInputLayout);
        locationTextInputLayout = findViewById(R.id.locationinputLayout);
        phoneTextInputLayout = findViewById(R.id.PhonenumberinputLayout);
        sosTextInputLayout = findViewById(R.id.SoSinputLayout);
        yearOfExpTextInputLayout = findViewById(R.id.ExPinputLayout);
        briefSummaryTextInputLayout = findViewById(R.id.brefsaminputLayout);
        nationalityTextInputLayout = findViewById(R.id.Nationalityinputlayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputlayout);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        summitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitUserInfo();
            }
        });
    }

    private void submitUserInfo() {

        name = nameEditText.getText().toString();
        email = emailEditText.getText().toString();
        dateOfBirth = dateofBirthEdithText.getText().toString();
        location = locationEditText.getText().toString();
        phoneNumber = PhoneNumberEditText.getText().toString();
        sos = SosEditText.getText().toString();
        yearsOfExp = YearsofExpEditText.getText().toString();
        briefSum = BriefSumEditText.getText().toString();
        nationality = nationalityEditText.getText().toString();

        String password = passwordEditText.getText().toString();

        if (!name.isEmpty()) {
            NameEmpty = false;

        } else {
            nameTextInputLayout.setError("Please enter your full name");
            nameTextInputLayout.setErrorEnabled(true);
        }

        if (!email.isEmpty()) {
            EmailEmpty = false;

        } else {
            emailTextInputLayout.setErrorEnabled(true);
            emailTextInputLayout.setError("Please enter valid email");
        }

        if (!dateOfBirth.isEmpty()) {
            dateOfBirthEmpty = false;

        } else {
            dobTextInputLayout.setErrorEnabled(true);
            dobTextInputLayout.setError("Please enter date of birth");
        }

        if (!location.isEmpty()) {
            LocationEmpty = false;

        } else {
            locationTextInputLayout.setErrorEnabled(true);
            locationTextInputLayout.setError("Please enter location");
        }

        if (!phoneNumber.isEmpty()) {
            PhoneEmpty = false;

        } else {
            phoneTextInputLayout.setErrorEnabled(true);
            phoneTextInputLayout.setError("Please enter phone number");
        }

        if (!sos.isEmpty()) {
            SosEmpty = false;

        } else {
            yearOfExpTextInputLayout.setErrorEnabled(true);
            yearOfExpTextInputLayout.setError("Please enter years of Experience");
        }

        if (!briefSum.isEmpty()) {
            BriefSumEmpty = false;

        } else {
            briefSummaryTextInputLayout.setErrorEnabled(true);
            briefSummaryTextInputLayout.setError("Please enter brief summary");
        }

        if (!nationality.isEmpty()) {
            NationalityEmpty = false;

        } else {
            nationalityTextInputLayout.setError("Please enter a nationality");
            nationalityTextInputLayout.setErrorEnabled(true);
        }

        if (!password.isEmpty()) {
            PasswordEmpty = false;

        } else {
            passwordTextInputLayout.setErrorEnabled(true);
            passwordTextInputLayout.setError("Please enter a password");
        }

        if (NameEmpty && EmailEmpty && LocationEmpty && dateOfBirthEmpty && PhoneEmpty
        && SosEmpty && YearOfExpEmpty && BriefSumEmpty && NationalityEmpty && PasswordEmpty) {

            Toast.makeText(this, "Field empty", Toast.LENGTH_LONG).show();
        } else {

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

            mDatabase.child("Teacher details").setValue(personalInfo);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registration complete", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), TeachersDetailActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
