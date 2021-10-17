package com.example.vehipark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSpinner extends AppCompatActivity {
    //Initialize variable
    TextView textView;
    ArrayList<String> arrayList;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_spinner);

        //Assign variable
        textView = findViewById(R.id.text_view);

        //Initialize array list
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
                dialog= new Dialog(CustomSpinner.this);
                //Set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                //Set custom height and width
                dialog.getWindow().setLayout(650,800);
                //Set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Show dialog
                dialog.show();

                //Initialize and assign variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                //Initialize array adapter
                ArrayAdapter adapter = new ArrayAdapter<>(CustomSpinner.this,
                        android.R.layout.simple_list_item_1,arrayList);
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
                        textView.setText((Integer) adapter.getItem(i));
                        //Dismiss dialog
                        dialog.dismiss();
                    }
                });


            }
        }

        );
    }
}