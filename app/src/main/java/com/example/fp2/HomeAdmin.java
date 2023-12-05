package com.example.fp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeAdmin extends AppCompatActivity {

    Button buttonStaff, buttonProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        buttonStaff = findViewById(R.id.buttonStaff);
        buttonProduk = findViewById(R.id.buttonProduk);

        buttonStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, AdminStaff.class);
                startActivity(intent);
            }
        });

        buttonProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crud = new Intent(getApplicationContext(), CrudAdmin.class);
                startActivity(crud);
            }
        });

    }
}
