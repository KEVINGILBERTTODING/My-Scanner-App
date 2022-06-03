package com.example.scanqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScanQrCode extends AppCompatActivity {

    Button scan;
    FloatingActionButton fabScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan_qr_code);


        sembunyiinNavbar();
        init();

        scan.setOnClickListener(view -> {
            moveToScanQrCode();
        });

        fabScan.setOnClickListener(view -> {
           moveToReadDataBarang();
        });


    }

    private void sembunyiinNavbar() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void init() {
        scan = findViewById(R.id.btn_scannow);
        fabScan = findViewById(R.id.btn_scan);
    }

    private void moveToScanQrCode() {
        startActivity(new Intent(getApplicationContext(), MediaBarcode.class));
    }

    private void moveToReadDataBarang() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}