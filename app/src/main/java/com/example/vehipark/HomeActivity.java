package com.example.vehipark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView tvTimer1,tvTimer2, mail;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    FirebaseAuth mauth;
    FirebaseFirestore fstore;
    TextView textView;
    ArrayList<String> arrayList;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Hooks
        textView = findViewById(R.id.text_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        tvTimer1=findViewById(R.id.tv_timer1);
        tvTimer2=findViewById(R.id.tv_timer2);
        fstore = FirebaseFirestore.getInstance();

        mauth = FirebaseAuth.getInstance();
        String userID = mauth.getCurrentUser().getUid();

        String currentEmail = mauth.getCurrentUser().getEmail();
        Toast.makeText(getApplicationContext(),currentEmail,Toast.LENGTH_SHORT).show();

        /*fstore.collection("Users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                String currentName = task.getResult().getString("name");
                //name.setText(currentName);
                //
            }
        });*/
        arrayList = new ArrayList<>();
        //Add value in array list
        arrayList.add("Park A");
        arrayList.add("Park B");
        arrayList.add("Park C");
        arrayList.add("Park D");
        arrayList.add("Park E");
        arrayList.add("Park F");
        arrayList.add("Park G");
        arrayList.add("Park H");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                            //Initialize dialog
                                            dialog = new Dialog(HomeActivity.this);
                                            //Set custom dialog
                                            dialog.setContentView(R.layout.dialog_searchable_spinner);
                                            //Set custom height and width
                                            dialog.getWindow().setLayout(650, 800);
                                            //Set transparent background
                                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            //Show dialog
                                            dialog.show();

                                            //Initialize and assign variable
                                            EditText editText = dialog.findViewById(R.id.edit_text);
                                            ListView listView = dialog.findViewById(R.id.list_view);

                                            //Initialize array adapter
                                            ArrayAdapter adapter = new ArrayAdapter<>(HomeActivity.this,
                                                    android.R.layout.simple_list_item_1, arrayList);
                                            //Set adapter
                                            listView.setAdapter(adapter);

                                            editText.addTextChangedListener(new TextWatcher() {
                                                @Override
                                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                }

                                                @Override
                                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                    //Filter array list
                                                    adapter.getFilter().filter(s);

                                                }

                                                @Override
                                                public void afterTextChanged(Editable s) {

                                                }
                                            });
                                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                                                    //When item selected from list
                                                    //Set selected item on text view
                                                    textView.setText((String) adapter.getItem(i));
                                                    //Dismiss dialog
                                                    dialog.dismiss();
                                                }
                                            });


                                        }
                                    }
        );

        tvTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HomeActivity.this,
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
                        HomeActivity.this,
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
                startActivity(new Intent(HomeActivity.this,SlotSelectionActivity.class));
            }
        });
        ImageView btn2=findViewById(R.id.imageView11);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,NotificationActivity.class));
            }
        });

        //ToolBar
        setSupportActionBar(toolbar);
        //Navigation Drawer Menu

        //Hide or show items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        //mail = (TextView) menu.findItem(R.id.emailID);



        //name = findViewById(R.id.nameID);
        //mail = findViewById(R.id.emailID);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_bookings:
                Intent intent = new Intent(HomeActivity.this,BookingDispActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_history:
                Intent intent1 = new Intent(HomeActivity.this,HistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_favourites:
                Intent intent2 = new Intent(HomeActivity.this,FavouriteActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_settings:
                Intent intent3 = new Intent(HomeActivity.this,SettingssActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}