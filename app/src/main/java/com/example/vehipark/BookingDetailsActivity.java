package com.example.vehipark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BookingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        ImageView btn=findViewById(R.id.imageView10);
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
                startActivity(new Intent(BookingDetailsActivity.this,HomePageActivity.class));
            }
        });
        }
}