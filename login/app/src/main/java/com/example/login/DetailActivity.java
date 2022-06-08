package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private  final  String TAG ="DetailActivity";
    private MainAdapter mainAdapter;
    private List<MainModel.Result> results = new ArrayList<>();
    private TextView textViewNama, textViewKeterangan, textViewHarga;
    private int intent_harga;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mainAdapter = new MainAdapter (results);
        textViewNama = (findViewById(R.id.textViewDetailNama));
        textViewKeterangan = (findViewById(R.id.textViewKeterangan));
        textViewHarga = (findViewById(R.id.textViewHarga));

        String nama_brg = getIntent().getStringExtra("intent_nama_brg");
//        String harga = getIntent().getStringExtra("intent_stok");
        String keterangan = getIntent().getStringExtra("intent_keterangan");
        String image = getIntent().getStringExtra("intent_image");
        textViewNama.setText(nama_brg);
        textViewKeterangan.setText(keterangan);
//        textViewHarga.setText(Integer.parseInt(harga));
        Picasso.get()
                .load(image)
                .fit().centerCrop()
                .into((ImageView) findViewById(R.id.imageView));

   }
}