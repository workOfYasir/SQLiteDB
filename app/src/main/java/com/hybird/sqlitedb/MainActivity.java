package com.hybird.sqlitedb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText imageDetailET;
    private ImageView objectImageView;

    private static final int PICK_IMAGES_REQUEST=100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    DBHandler objectDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            imageDetailET= findViewById(R.id.imageNameET);
            objectImageView = findViewById(R.id.image);

            objectDBHandler = new DBHandler(this);


        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void chooseImage(View objectView)
    {
        try
        {
            Intent objectIntent=new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGES_REQUEST);

        }
        catch (Exception e)
        {
            Toast.makeText(this, "chooseImage"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==PICK_IMAGES_REQUEST && resultCode==RESULT_OK && data != null && data.getData()!=null)
            {

                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                objectImageView.setImageBitmap(imageToStore);

            }
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void storeImage(View view)
    {
        try
        {
            if (!imageDetailET.getText().toString().isEmpty() && objectImageView.getDrawable()!=null && imageToStore!=null)
            {

                objectDBHandler.storeImage(new ModelClass(imageDetailET.getText().toString(),imageToStore));
            }
            else
            {

                Toast.makeText(this, "Please Select the image name and image", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Something went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void moveToShowActivity(View view)
    {
        startActivity(new Intent(this, ShowImages.class));

    }
}
