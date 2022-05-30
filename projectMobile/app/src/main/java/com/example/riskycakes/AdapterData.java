package com.example.riskycakes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import  java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    List <DataModel> listData;
    LayoutInflater inflater;
    Context context;

    public  AdapterData(Context context, List<DataModel> listData) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_data, parent, false);
        return new HolderData(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        holder.txtData.setText(listData.get(position).getJudul());
        Glide.with(context).load(listData.get(position).getGambar()).into(holder.img);
    }

    @Override
    public  int getItemCount() { return listData.size(); }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView txtData;
        ImageView img;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.idImg);
                    txtData = itemView.findViewById(R.id.dataText);
        }
    }

}
