package com.example.vehipark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUpActivity extends AppCompatActivity {

    EditText regEmail;
    EditText regPassword;
    EditText regConfirmPassword;
    TextView regBackLogin;
    TextView regName;
    TextView regContact;
    TextView regAddress;
    Button regButton;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        regBackLogin = findViewById(R.id.regBackLogin);
        regButton = findViewById(R.id.applyButton);
        regName = findViewById(R.id.regName);
        regContact = findViewById(R.id.regContact);
        regAddress = findViewById(R.id.regAddress);
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        regButton.setOnClickListener(view -> {
            createUser();

        });
        regBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
            }
        });
    }

    private void createUser() {

        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        String confirmPassword = regConfirmPassword.getText().toString();
        String name = regName.getText().toString();
        String contact = regContact.getText().toString();
        String address = regAddress.getText().toString();

        if (TextUtils.isEmpty(name)) {
            regName.setError("Name cannot be empty");
            regName.requestFocus();
        }
        if (TextUtils.isEmpty(address)) {
            regEmail.setError("address cannot be Empty");
            regEmail.requestFocus();
        }
        // TODO: wrong text field
        if (TextUtils.isEmpty(contact)) {
            regEmail.setError("contact cannot be Empty");
            regEmail.requestFocus();
        }
        if (TextUtils.isEmpty(email)) {
            regEmail.setError("Email cannot be Empty");
            regEmail.requestFocus();
        }
        if (TextUtils.isEmpty(password)) {
            regPassword.setError("Password cannot be Empty");
            regPassword.requestFocus();
        }
        if (!confirmPassword.equals(password)) {
            regConfirmPassword.setError("Passwords does not Match");
            regConfirmPassword.requestFocus();
        }

        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Users users = new Users(name, address, contact, email);
                            String userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("Users").document(userID);
                            documentReference.set(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(SignUpActivity.this, HomeActivity.class);
                                    startActivity(in);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /*try{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Users users = new Users(name,address,contact,email);
                        mDatabase
                                .child("Users").child(mAuth.getCurrentUser().getUid())
                                .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "Sucessful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignUpActivity.this, HomeActivity.class));

                                }else{
                                    Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }*/
        }

    }

