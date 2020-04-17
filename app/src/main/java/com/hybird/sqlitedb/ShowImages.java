package com.hybird.sqlitedb;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowImages extends AppCompatActivity {

    private DBHandler objectDBHandler;
    private RecyclerView objectRecyclerView;

    RVAdapter objectRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        try
        {
            objectRecyclerView=findViewById(R.id.imagesRV);
            objectDBHandler =new DBHandler(this);

        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void getData(View view)
    {
        try
        {
            objectRVAdapter=new RVAdapter(objectDBHandler.getAllImagesData());
            objectRecyclerView.setHasFixedSize(true);

            objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerView.setAdapter(objectRVAdapter);
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
