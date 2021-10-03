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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        regBackLogin = findViewById(R.id.regBackLogin);
        regButton = findViewById(R.id.signUpButton);
        regName = findViewById(R.id.regName);
        regContact = findViewById(R.id.regContact);
        regAddress = findViewById(R.id.regAddress);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");


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

    public void createUser() {
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
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user =  FirebaseAuth.getInstance().getCurrentUser();
                        String uId =user.getUid();
                        Users users = new Users(email,name,contact,address,uId);
                        databaseReference.child(uId).setValue(users);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                Toast.makeText(SignUpActivity.this, "Sucessful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(SignUpActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //database.child("Users").setValue(users);

                    } else {
                        Toast.makeText(SignUpActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
