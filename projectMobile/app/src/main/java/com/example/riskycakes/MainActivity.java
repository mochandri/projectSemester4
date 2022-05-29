package com.example.riskycakes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    TextView tv_nama_brg, tv_kategori, tv_keterangan, tv_harga, tv_stok, tv_gambar;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_nama_brg = findViewById(R.id.tv_nama_brg);
        tv_kategori = findViewById(R.id.tv_kategori);
        tv_keterangan = findViewById(R.id.tv_keterangan);
        tv_harga = findViewById(R.id.tv_harga);
        tv_stok = findViewById(R.id.tv_stok);
        tv_gambar = findViewById(R.id.tv_gambar);

        tampilData();

    }
    private  void  tampilData(){
        loading = ProgressDialog.show(MainActivity.this,"Memuat Data","Harap Tunggu");
        RequestQueue queue= Volley.newRequestQueue(this);
        String url ="http://localhost/projectSemester4/ci3/api/barang";
        JSONObject jsonObject = new JSONObject();
        final String requestBody = jsonObject.toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response.toString());
                    String nama_brg = jo.getString("nama_brg");
                    String keterangan = jo.getString("keterangan");
                    String kategori = jo.getString("kategori");
                    String harga = jo.getString("harga");
                    String stok = jo.getString("stok");
                    String gambar = jo.getString("gambar");




                    tv_nama_brg.setText(nama_brg);
                    tv_kategori.setText(kategori);
                    tv_keterangan.setText(keterangan);
                    tv_stok.setText(stok);
                    tv_harga.setText(harga);
                    tv_gambar.setText(gambar);
                    loading.cancel();
                    Toast.makeText(MainActivity.this, "Berhasil Memuat", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.cancel();
                Toast.makeText(MainActivity.this, "Gagal Ambil Rest Api"+ error, Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(stringRequest);

    }
}