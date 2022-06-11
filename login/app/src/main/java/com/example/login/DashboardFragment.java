package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.login.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends AppCompatActivity {
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

    @Override
    protected void onStart() {
        super.onStart();
        showLoading(false);
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

                Intent intent = new Intent(DashboardFragment.this, DetailActivity.class);
                intent.putExtra("intent_nama_brg",result.getNama_brg());
                intent.putExtra("intent_keterangan", result.getKeterangan());
//            intent.putExtra("intent_kategori", result.getKategori());
                intent.putExtra("intent_harga",result.getHarga());
                intent.putExtra("intent_stok", result.getStok());
                intent.putExtra("intent_image",result.getGambar_url());
                startActivity(intent);




            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void getDataFromApi() {
        showLoading( true );
        ApiService.endpoint().getData()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        showLoading( false );
                        Log.d( TAG, "Result" + response.toString());
                        if (response.isSuccessful()) {
                            List<MainModel.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainAdapter.setData( results );

                        }
                    }
                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {
                        showLoading( false );
                        Log.d( TAG, t.toString());
                    }
                });
    }
    private void showLoading(Boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}