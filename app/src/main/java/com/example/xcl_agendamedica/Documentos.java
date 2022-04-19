package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Documentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
    }
}