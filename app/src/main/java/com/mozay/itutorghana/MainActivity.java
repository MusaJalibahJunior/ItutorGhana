package com.mozay.itutorghana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button findaTacherBtn;
    Button becomeaTeacherBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findaTacherBtn = findViewById(R.id.findateacherbutton);
        becomeaTeacherBtn = findViewById(R.id.becomeateacherbutton);

        becomeaTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginOrSignUpActivity.class);
                startActivity(intent);
            }
        });

    }

//    private void checkForAuth() {
//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                if (user != null) {
//                    becomeaTeacherBtn.setVisibility(View.GONE);
//                    viewProfileBtn.setVisibility(View.VISIBLE);
//                } else {
//                    becomeaTeacherBtn.setVisibility(View.VISIBLE);
//                    viewProfileBtn.setVisibility(View.GONE);
//                }
//            }
//        };
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
//            Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
//            Intent teacherdetails = new Intent(this,TeachersDetailActivity.class);
//            startActivity(teacherdetails);
//
//
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        mAuth.removeAuthStateListener(mAuthListener);
//    }
}
