package com.example.scanqrcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

    private void cameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);

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

    public void scanner(View view) {
        startActivity(new Intent(getApplicationContext(), MediaBarcode.class));
    }
}