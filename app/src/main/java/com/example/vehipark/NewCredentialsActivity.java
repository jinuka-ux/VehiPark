package com.example.vehipark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewCredentialsActivity extends AppCompatActivity {

    EditText newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_credentials);

        newPassword = findViewById(R.id.editTextNewConfirmPassword);

        Button btn1=findViewById(R.id.buttonSendCode);
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String newPassword = "SOME-SECURE-PASSWORD";

        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(NewCredentialsActivity.this,HomeActivity.class));
                        }
                    }
                });


    }
}