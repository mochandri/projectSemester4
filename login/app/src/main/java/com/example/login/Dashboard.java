package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    private  final String TAG = "Dashboard";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdapter mainAdapter;

    private List<MainModel.Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupView();
        setupRecyclerView();
        getDataFromApi();
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }
    private void setupRecyclerView(){
        mainAdapter = new MainAdapter(results, new MainAdapter.OnAdapterListener() {
            @Override
            public void onClick(MainModel.Result result) {
//                Toast.makeText(Dashboard.this,
//                        result.getnama_brg,
//                        result.getketerangan,
//                        result.getkategori,
//                        result.getharga,
//                        result.getstok,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Dashboard.this,DetailActivity.class);
            intent.putExtra("intent_nama_brg",result.getnama_brg);
            intent.putExtra("intent_keterangan", result.getketeranga);
            intent.putExtra("intent_kategori", result.getketegori);
            intent.putExtra("intent_harga", result.getherga);
            intent.putExtra("intent_stok", result.getstok);
            intent.putExtra("intent_image",result.getimage);




            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private  void getDataFromApi(){
        progressBar.setVisibility(View.VISIBLE);
        ApiService.endpoint().getData()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        Log.d(TAG, response.toString());
                        if(response.isSuccessful()){
                            List<MainModel.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainAdapter.setData( results );
                        }
                    }

                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, t.toString());

                    }
                });
    }


}