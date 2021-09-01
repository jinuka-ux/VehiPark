package com.example.vehipark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomePageActivity extends AppCompatActivity {
    //Initialize variables
    TextView tvTimer1,tvTimer2;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Assign variable
        tvTimer1=findViewById(R.id.tv_timer1);
        tvTimer2=findViewById(R.id.tv_timer2);
        drawerLayout = findViewById(R.id.drawer_layout);

        /*tvTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HomePageActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t1Hour=hourOfDay;
                                t1Minute=minute;
                                //Initialize calendar
                                Calendar calendar = Calendar.getInstance();
                                //set hour and minute
                                calendar.set(0,0,0,t1Hour,t1Minute);
                                //set selected time on text view
                                tvTimer1.setText(Format.format("hh:mm aa",calendar));
                            }
                        }, 12,0,false
                );
                //Displayed previous selected time
                timePickerDialog.updateTime(t1Hour,t1Minute);
                //show dialog
                timePickerDialog.show();

            }
        });*/
        tvTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HomePageActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                String time =t1Hour + ":" + t1Minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date=f24Hours.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    //set selected time on textView
                                    tvTimer1.setText(f12Hours.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed previous selected time
                timePickerDialog.updateTime(t1Hour,t1Minute);
                //show dialog
                timePickerDialog.show();
            }
        });
        tvTimer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HomePageActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t2Hour = hourOfDay;
                                t2Minute = minute;

                                String time =t2Hour + ":" + t2Minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date=f24Hours.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    //set selected time on textView
                                    tvTimer2.setText(f12Hours.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed previous selected time
                timePickerDialog.updateTime(t2Hour,t2Minute);
                //show dialog
                timePickerDialog.show();
            }
        });
        Button btn1=findViewById(R.id.buttonSendCode);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,SlotSelectionActivity.class));
            }
        });


        }
        public void ClickMenu(View view){
        //open drawer
            openDrawer(drawerLayout);
        }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        //Close drawer layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            // close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        //Recreate activity
        recreate();
    }
    public void ClickBookings(View view){
        //Redirect activity to bookings
        redirectActivity(this,BookingDispActivity.class);
    }
    public void ClickFavourites(View view){
        //Redirect activity to Favourites
        redirectActivity(this,FavouriteActivity.class);
    }
    public void ClickHistory(View view){
        //Redirect activity to Settings
        redirectActivity(this,HistoryActivity.class);
    }
    public void ClickLogout(View view){
        //close app
        logout(this);
    }

    public static void logout(Activity activity) {
        //Initialize alert box
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("Are you sure you want to logout?");
        //Positive YES button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish Activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent (activity,aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}
