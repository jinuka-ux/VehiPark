package com.example.vehipark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SettingssActivity extends AppCompatActivity {

    EditText regEmail;
    EditText regName;
    EditText regContact;
    EditText regAddress;
    Button applyButton;
    TextView click;
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingss);
        ImageView btn1=findViewById(R.id.imageView10);

        regEmail = findViewById(R.id.regEmail);
        regName = findViewById(R.id.regName);
        regContact = findViewById(R.id.regContact);
        regAddress = findViewById(R.id.regAddress);
        applyButton =findViewById(R.id.applyButton);
        click =findViewById(R.id.click);
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingssActivity.this,NewCredentialsActivity.class));
            }
        });

        /*TextView btn2=findViewById(R.id.clickHere);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingssActivity.this,ChangePasswordActivity.class));
            }
        });*/

    }

    public void update(){
        String userID = mAuth.getCurrentUser().getUid();
        String name = regName.getText().toString();
        String contact = regContact.getText().toString();
        String address = regAddress.getText().toString();

            final DocumentReference doc= fstore.collection("Users").document(userID);
            fstore.runTransaction(new Transaction.Function<Void>() {
                @Override
                public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                    DocumentSnapshot snapshot = transaction.get(doc);
                    transaction.update(doc,"name",name);
                    transaction.update(doc,"address",address);
                    transaction.update(doc,"contact",contact);
                    return null;
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid)
                {
                    Toast.makeText(getApplicationContext(),"Update Successfull",Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Update Unsuccessfull",Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    public void displayData() {
        String mail = mAuth.getCurrentUser().getEmail();
        regEmail.setText(mail);
        String userID = mAuth.getCurrentUser().getUid();
        fstore.collection("Users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String currentName = task.getResult().getString("name");
                    String currentAddress = task.getResult().getString("address");
                    String currentContact = task.getResult().getString("contact");

                    regName.setText(currentName);
                    regAddress.setText(currentAddress);
                    regContact.setText(currentContact);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }

}