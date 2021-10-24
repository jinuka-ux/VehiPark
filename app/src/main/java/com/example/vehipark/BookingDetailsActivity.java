package com.example.vehipark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookingDetailsActivity extends AppCompatActivity {
    TextView bname;
    TextView bprice;
    TextView bPark;
    TextView bSlot;
    TextView bduration;
    TextView bPPrice;

    FirebaseFirestore fstore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        ImageView btn=findViewById(R.id.imageView10);

        bname = findViewById(R.id.bname);
        bprice = findViewById(R.id.bprice);
        bPark = findViewById(R.id.bpark);
        bSlot = findViewById(R.id.bslot);
        bduration = findViewById(R.id.bduration);
        bPPrice = findViewById(R.id.bpprice);

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingDetailsActivity.this,SlotSelectionActivity.class));
            }
        });
        Button btn1=findViewById(R.id.confirmBookingDetails);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingDetailsActivity.this,BookingConfirmedActivity.class));
            }
        });
        Button btn2=findViewById(R.id.cancelBookingDetails);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingDetailsActivity.this,HomeActivity.class));
            }
        });

        TextView btn3=findViewById(R.id.textViewHelp);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingDetailsActivity.this,FAQActivity.class));
            }
        });
        }

    public void displayData() {
        //String mail = mAuth.getCurrentUser().getEmail();
        //regEmail.setText(mail);
        String userID = mAuth.getCurrentUser().getUid();
        fstore.collection("Users").document(userID).collection("Bookings").document("book1").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String ppName = task.getResult().getString("pName");
                    String ppPrice = task.getResult().getString("pPrice");
                    String ppPark = task.getResult().getString("pPark");
                    String ppSlot = task.getResult().getString("pSlot");
                    String ppDuration = task.getResult().getString("duration");
                    String ppPPrice = task.getResult().getString("pPPrice");
                    //String currentContact = task.getResult().getString("contact");

                    bname.setText(ppName);
                    bprice.setText(ppPrice);
                    bPark.setText(ppPark);
                    bduration.setText(ppDuration);
                    bPPrice.setText(ppPPrice);
                    bSlot.setText(ppSlot);
                    //regContact.setText(currentContact);
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