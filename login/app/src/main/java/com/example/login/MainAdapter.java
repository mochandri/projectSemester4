package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainModel.Result> results ;
    private  OnAdapterListener listener ;
    private Context context;

    public MainAdapter(List<MainModel.Result> results, OnAdapterListener listener) {
        this.results = results;
        this.context =context;
        this.listener = listener;
    }

    public MainAdapter(List<MainModel.Result> results) {
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainModel.Result result = results.get(position);
        holder.textView.setText(result.getNama_brg());
        holder.textView2.setId(result.getHarga());
        Picasso.get()
                .load(result.getGambar_url())
                .fit().centerCrop()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(result);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);


        }
    }
    public void setData(List<MainModel.Result> newResults){
        results.clear();
        results.addAll(newResults);
        notifyDataSetChanged();
    }
    interface OnAdapterListener{
        void onClick(MainModel.Result result);
    }
}

