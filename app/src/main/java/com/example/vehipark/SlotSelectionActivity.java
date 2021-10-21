package com.example.vehipark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SlotSelectionActivity extends AppCompatActivity {
    //Initialize variable
    RecyclerView recyclerView;
    ArrayList<String> sectionList = new ArrayList<>();
    HashMap<String,ArrayList<String>> itemList = new HashMap<>();
    MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_selection);

        //Assign variable
        recyclerView = findViewById(R.id.recycler_view);

        //Add section values
        sectionList.add("1st Floor");
        sectionList.add("2nd Floor");
        sectionList.add("3rd Floor");
        sectionList.add("4th Floor");

        //Initialize array list
        ArrayList<String> arrayList = new ArrayList<>();

        //Initialize 1st floor values
        arrayList.add("1A1");
        arrayList.add("1A2");
        arrayList.add("1A3");
        arrayList.add("1A4");
        arrayList.add("1B1");
        arrayList.add("1B2");
        arrayList.add("1B3");

        //put 1st floor values in item list
        itemList.put(sectionList.get(0),arrayList);

        //Add 2nd floor values
        arrayList = new ArrayList<>();
        arrayList.add("2A1");
        arrayList.add("2A2");
        arrayList.add("2A3");
        arrayList.add("2A4");
        arrayList.add("2B1");
        arrayList.add("2B2");
        arrayList.add("2B3");

        //put 2nd floor values in item list
        itemList.put(sectionList.get(1),arrayList);

        //Add 3rd floor values
        arrayList = new ArrayList<>();
        arrayList.add("3A1");
        arrayList.add("3A2");
        arrayList.add("3A3");
        arrayList.add("3A4");
        arrayList.add("3B1");
        arrayList.add("3B2");
        arrayList.add("3B3");

        //put 2nd floor values in item list
        itemList.put(sectionList.get(2),arrayList);

        //Add 4th floor values
        arrayList = new ArrayList<>();
        arrayList.add("4A1");
        arrayList.add("4A2");
        arrayList.add("4A3");
        arrayList.add("4A4");
        arrayList.add("4B1");
        arrayList.add("4B2");
        arrayList.add("4B3");

        //put 2nd floor values in item list
        itemList.put(sectionList.get(3),arrayList);

        //Initialize adapter
        adapter = new MainAdapter(this,sectionList,itemList);
        //Initialize grid layout manager
        GridLayoutManager manager = new GridLayoutManager(this,4);
        //Set layout manager to recycler view
        recyclerView.setLayoutManager(manager);
        //Set layout manager to adapter
        adapter.setLayoutManager(manager);
        //Hide empty section
        adapter.shouldShowHeadersForEmptySections(false);
        //Set adapter
        recyclerView.setAdapter(adapter);



        ImageView btn=findViewById(R.id.imageView10);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SlotSelectionActivity.this,HomeActivity.class));
            }
        });
        Button btn1=findViewById(R.id.continueSlotSelection);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SlotSelectionActivity.this,BookingDetailsActivity.class));
            }
        });
    }
}