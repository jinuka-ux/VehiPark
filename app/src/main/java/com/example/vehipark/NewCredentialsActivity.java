package com.example.vehipark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

public class NewCredentialsActivity extends AppCompatActivity {

    EditText newPassword;
    EditText newPasswordRe;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_credentials);

        newPasswordRe = findViewById(R.id.editTextNewConfirmPassword);
        newPassword = findViewById(R.id.editTextNewPassword);
        mAuth = FirebaseAuth.getInstance();

        Button btn1=findViewById(R.id.buttonUpdatePassword);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                //startActivity(new Intent(NewCredentialsActivity.this,PasswordUpdatedActivity.class));
            }
        });
    }
    public void update(){
        String password = newPassword.getText().toString();
        String passwordRe = newPasswordRe.getText().toString();
        FirebaseUser user = mAuth.getCurrentUser();
        //String newPassword = "SOME-SECURE-PASSWORD";

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(NewCredentialsActivity.this, "password cannot be empty", Toast.LENGTH_SHORT).show();//not working
        }
        if (TextUtils.isEmpty(passwordRe)) {
            Toast.makeText(NewCredentialsActivity.this, "password cannot be empty", Toast.LENGTH_SHORT).show();//not working
        }

        if (!passwordRe.equals(password)) {
            Toast.makeText(NewCredentialsActivity.this, "Error", Toast.LENGTH_SHORT).show();
            //newPassword.setError("Passwords does not Match");
            //newPassword.requestFocus();
        }else {
            user.updatePassword(password)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //if (task.isSuccessful()) {
                                startActivity(new Intent(NewCredentialsActivity.this, PasswordUpdatedActivity.class));
                            //}
                        }
                    });


        }



    }
}