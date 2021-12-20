package com.example.android12;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    ImageView imImageGallery;
    EditText etTransData;
    Button btnSenDataToMainActivity;
    Uri uriimage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        listener();

    }

    private void initView() {
        imImageGallery = findViewById(R.id.in_image_gallery);
        etTransData = findViewById(R.id.trans_data);
        btnSenDataToMainActivity = findViewById(R.id.btn_trans_data_to_main_activity);

    }

    private void listener() {
        imImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultLauncher.launch("image/*");
            }

        });
        btnSenDataToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etTransData.getText().toString();
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("title", data);
                intent.putExtra("image", uriimage);
                startActivity(intent);
            }
        });
    }

    ActivityResultLauncher<String> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    imImageGallery.setImageURI(uri);
                    uriimage = uri;
                }
            });
}