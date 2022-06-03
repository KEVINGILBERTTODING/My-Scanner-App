package com.example.scanqrcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.scanqrcode.Adapter.BarangAdapter;
import com.example.scanqrcode.Model.BarangModel;
import com.example.scanqrcode.Utill.DataApi;
import com.example.scanqrcode.Utill.InterfaceBarang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private BarangAdapter barangAdapter;
    private List<BarangModel> barangModelList;
    private InterfaceBarang interfaceBarang;
    SearchView searchView;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        setContentView(R.layout.activity_main);


        fabAdd = findViewById(R.id.btn_add);
        recyclerView    =   findViewById(R.id.rcylrBarang);


        // Mengatur warna tint fab

        fabAdd.setColorFilter(getResources().getColor(R.color.white));


        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        interfaceBarang= DataApi.getClient().create (InterfaceBarang.class);
        tampilkanData();


        // Inisialisasi searchview

        searchView = findViewById(R.id.search_barr);
        searchView.clearFocus();

        // Fungsi saat memasukkan kata ke dalam searchview

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String querry) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        fabAdd.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TambahBarang.class));
        });



    }

    // Method untuk realtime searchview

    private void filter(String newText) {

        ArrayList<BarangModel> filteredList = new ArrayList<>();

        for (BarangModel item : barangModelList) {
            if (item.getNama().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);

            }
        }


        barangAdapter.filterList(filteredList);


        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        } else {
            barangAdapter.filterList(filteredList);
        }



    }





    private void tampilkanData() {
        Call<List<BarangModel>> call = interfaceBarang.getBarang();

        call.enqueue(new Callback<List<BarangModel>>() {

            @Override
            public void onResponse(Call<List<BarangModel>> call, Response<List<BarangModel>> response) {

                barangModelList = response.body();
                barangAdapter= new BarangAdapter(MainActivity.this, barangModelList);
                recyclerView.setAdapter(barangAdapter);
            }


            @Override
            public void onFailure(Call<List<BarangModel>> call, Throwable t) {

                // Menampilkan toast saat no connection

                Toast.makeText(MainActivity.this, "No connection, please try again", Toast.LENGTH_LONG).show();


//                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    }



