package com.mozay.itutorghana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

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

    ImageButton profileImage;

    String name;
    String email;
    String dateOfBirth;
    String location;
    String phoneNumber;
    String sos;
    String yearsOfExp;
    String briefSum;
    String nationality;

    Uri photoUri;

    private FirebaseAuth mAuth;
    private StorageReference mReference;
    private StorageTask uploadTask;

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

        profileImage = findViewById(R.id.image1);

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
        mReference = FirebaseStorage.getInstance().getReference("users");

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
            }
        });

        summitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitUserInfo();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            photoUri = data.getData();

            Picasso.get().load(photoUri).into(profileImage);
        }
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

        final String password = passwordEditText.getText().toString();

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

            Map<String, Object> teacher = new HashMap<>();
            teacher.put("name",name);
            teacher.put("email",email);
            teacher.put("location",location);
            teacher.put("phoneNumber",phoneNumber);
            teacher.put("sos",sos);
            teacher.put("yearsOfExp",yearsOfExp);
            teacher.put("briefSum",briefSum);
            teacher.put("nationality",nationality);
            teacher.put("dateOfBirth",dateOfBirth);


            FirebaseFirestore db = FirebaseFirestore.getInstance();

            if (photoUri != null) {
                StorageReference storageReference = mReference.child(System.currentTimeMillis()
                + "." + getFileExtention(photoUri));

                uploadTask = storageReference.putFile(photoUri);
            }

            db.collection("teachers")
                    .add(teacher)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
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
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Registration Failed. Try again!", Toast.LENGTH_LONG).show();
                            Log.e("REGFF", e.getMessage());
                        }
                    });
        }
    }

    private String getFileExtention(Uri uri){

        android.content.ContentResolver Cr = getContentResolver();
        android.webkit.MimeTypeMap mime= android.webkit.MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(Cr.getType(uri));
    }
}
