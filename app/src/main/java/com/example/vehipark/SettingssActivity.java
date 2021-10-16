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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SettingssActivity extends AppCompatActivity {

    EditText regEmail;
    EditText regName;
    EditText regContact;
    EditText regAddress;
    Button applyButton;
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
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        //Display Data
        String userID = mAuth.getCurrentUser().getUid();
        DocumentReference docRef = fstore.collection("Users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Users user = documentSnapshot.toObject(Users.class);
                //String  nam = ;
                regName.setText(user.name);
                regAddress.setText(user.address);
                regContact.setText(user.contact);
                regEmail.setText(user.email);
               // String contact = regContact.getText().toString();
                //String address = regAddress.getText().toString();
            }
        });

        String email = regEmail.getText().toString();
        String name = regName.getText().toString();
        String contact = regContact.getText().toString();
        String address = regAddress.getText().toString();

        //Update data
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DocumentReference doc= fstore.collection("Users").document(userID);
                doc
                        .update("address", address)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SettingssActivity.this, "DocumentSnapshot successfully updated!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SettingssActivity.this, "Failes", Toast.LENGTH_LONG).show();
                            }
                        });*/
                final DocumentReference doc= fstore.collection("Users").document(userID);
                fstore.runTransaction(new Transaction.Function<Void>() {
                    @Override
                    public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                        DocumentSnapshot snapshot = transaction.get(sfDocRef);
                        transaction.update(sfDocRef,"name",name);
                        transaction.update(sfDocRef,"address",address);
                        transaction.update(sfDocRef,"email",email);
                        transaction.update(sfDocRef,"contact",contact);
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
}