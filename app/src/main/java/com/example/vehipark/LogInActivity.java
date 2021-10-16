package com.example.vehipark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogInActivity extends AppCompatActivity {
    TextView loginEmail;
    TextView loginPassword;
    TextView loginBack;
    TextView clickHere;
    Button loginButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginBack = findViewById(R.id.loginBack);
        clickHere = findViewById(R.id.clickHere);
        loginButton =findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view ->{
            String email = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();

            if (TextUtils.isEmpty(email)){
                loginEmail.setError("Email cannot be empty");
                loginEmail.requestFocus();
            }else if(TextUtils.isEmpty(password)){
                loginPassword.setError("Password cannot be empty");
                loginPassword.requestFocus();
            }else{
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogInActivity.this,"Sucessful", Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(LogInActivity.this, HomeActivity.class));
                        }else{
                            Toast.makeText(LogInActivity.this, "Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loginBack.setOnClickListener(view ->{
            startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
        });
        clickHere.setOnClickListener(view ->{
            startActivity(new Intent(LogInActivity.this, ForgotPasswordActivity.class));
        });

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        }


    }

}