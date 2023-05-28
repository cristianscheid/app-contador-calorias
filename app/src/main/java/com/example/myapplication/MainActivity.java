package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static BancoController crud;
    public static Date data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new Date();
        crud = new BancoController(getBaseContext());

        startActivity(new Intent(MainActivity.this, DiarioAlimentar.class));
    }
}