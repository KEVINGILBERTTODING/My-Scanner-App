package com.example.scanqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scanqrcode.Model.BarangModel;
import com.example.scanqrcode.Utill.DataApi;
import com.example.scanqrcode.Utill.InterfaceBarang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBarang extends AppCompatActivity {
    EditText xkd_brg, xnm_brg, xhrg_brg;
    InterfaceBarang interfaceBarang;
    Button btnsimpan, btlview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);
        xkd_brg = findViewById(R.id.xkode);
        xnm_brg = findViewById(R.id.xnmbrg);
        xhrg_brg = findViewById(R.id.xharga);
        interfaceBarang = DataApi.getClient().create(InterfaceBarang.class);
    }
    public void simpandata(View view) {
        String kode_brg = xkd_brg.getText().toString();
        String nama_brg = xnm_brg.getText().toString();
        String harga_brg = xhrg_brg.getText().toString();


        Call<BarangModel> postBarang = interfaceBarang.postBarang(kode_brg,
                nama_brg, harga_brg);
        postBarang.enqueue(new Callback<BarangModel>() {
            @Override
            public void onResponse(Call<BarangModel> call, Response<BarangModel>
                    response) {
                Toast.makeText(TambahBarang.this, "Save data Success",
                        Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TambahBarang.this,MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<BarangModel> call, Throwable t) {
                Toast.makeText(TambahBarang.this, "Save data not Success",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnback(View view) {
        startActivity(new Intent(TambahBarang.this,MainActivity.class));
        finish();
    }
}