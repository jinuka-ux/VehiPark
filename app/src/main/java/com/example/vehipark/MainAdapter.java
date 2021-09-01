package com.example.vehipark;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAdapter extends SectionedRecyclerViewAdapter<MainAdapter.ViewHolder> {
    //Initialize variable
    Activity activity;
    ArrayList<String> sectionList;
    HashMap<String,ArrayList<String>> itemList = new HashMap<>();
    int selectedSection = -1;
    int selectedItem = -1;

    //Create constructor
    public MainAdapter(Activity activity,ArrayList<String> sectionList ,HashMap<String,ArrayList<String>> itemList){
        this.activity = activity;
        this.sectionList = sectionList;
        this.itemList= itemList;
        notifyDataSetChanged();

    }


    @Override
    public int getSectionCount() {
        //Return section list size
        return sectionList.size();
    }

    @Override
    public int getItemCount(int section) {
        //Return item list size
        return itemList.get(sectionList.get(section)).size();
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        //set section value on text view
        viewHolder.textView.setText(sectionList.get(i));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i, int i1, int i2) {
        //Initialize string item value
        String sItem = itemList.get(sectionList.get(i)).get(i1);
        //set item on text view
        viewHolder.textView.setText(sItem);

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display toast
                Toast.makeText(activity,sItem, Toast.LENGTH_SHORT).show();
                //Update both positions
                selectedSection = i;
                selectedItem = i1;
                //Notify adapter
                notifyDataSetChanged();
            }
        });
        //Check condition
        if (selectedSection == i && selectedItem ==i1){
            //When item selected
            //set Background
            viewHolder.textView.setBackground(ContextCompat.getDrawable(activity,R.drawable.rectangle_fill));
            //set text color
            viewHolder.textView.setTextColor(Color.WHITE);
        }else{
            //When item unselected
            //set background
            viewHolder.textView.setBackground(ContextCompat.getDrawable(activity,R.drawable.rectangle_outline));
            //set text color
            viewHolder.textView.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        //check condition
        if (section == 1){
            //When section is equal to 1
            //Return 0
            return 0;
        }
        //Return all three position
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Define layout
        int layout;
        //Check condition
        if (viewType == VIEW_TYPE_HEADER){
            //When view type is equal to header
            layout = R.layout.item_header;
        }else{
            //When view type is equal to item
            layout = R.layout.item_slot;
        }
        //Initialize view
        View view = LayoutInflater.from(parent.getContext()) .inflate(layout,parent, false);
        //Return view holder
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView textView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
