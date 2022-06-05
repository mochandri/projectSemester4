package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private  final  String TAG ="DetailActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String nama_brg = getIntent().getStringExtra("intent_nama_brg");
        String image = getIntent().getStringExtra("intent_image");
        getSupportActionBar().setTitle(nama_brg);
        Picasso.get()
                .load(image)
                .into((ImageView) findViewById(R.id.imageView));
    }
}