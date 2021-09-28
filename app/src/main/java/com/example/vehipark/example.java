package com.example.vehipark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class example extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){
        HomePageActivity.openDrawer(drawerLayout);
    }
}