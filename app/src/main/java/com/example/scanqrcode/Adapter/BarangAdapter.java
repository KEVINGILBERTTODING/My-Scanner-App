package com.example.scanqrcode.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scanqrcode.MainActivity;
import com.example.scanqrcode.Model.BarangModel;
import com.example.scanqrcode.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {
    Context context;
    List<BarangModel> barangModels;



    public BarangAdapter(Context context,  List<BarangModel> barangModels) {
         this.context = context;
         this.barangModels= barangModels;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_barang, parent,
                        false);

        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {


        holder.kd_brg.setText(barangModels.get(position).getKode());
        holder.nm_brg.setText(barangModels.get(position).getNama());
        holder.hrg_brg.setText(formatRupiah(Double.parseDouble(barangModels.get(position).getHarga())));


    }

    @Override
    public int getItemCount() {
        return barangModels.size();
    }

    public void filterList(ArrayList<BarangModel> filteredList) {

        barangModels = filteredList;
        notifyDataSetChanged();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView kd_brg,nm_brg,hrg_brg;

        public MyViewHolder(View itemView) {
            super(itemView);
            kd_brg = itemView.findViewById(R.id.tv_kdbrg);
            nm_brg = itemView.findViewById(R.id.tv_nmbrg);
            hrg_brg = itemView.findViewById(R.id.tv_harga);



        }
    }

    private String formatRupiah(Double number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);

    }
}
