package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        this.setTitle("Ayuda"); //TITULO MOSTRADO EN ACTION BAR
    }
}