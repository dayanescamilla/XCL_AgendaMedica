package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class filtro_reguntas extends AppCompatActivity {

    Button btnEnivar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_reguntas);

        btnEnivar = findViewById(R.id.id_m9_btn1);

        //DIRIGIRSE A MODULO DE AGENDAR CITAS
        btnEnivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enviardatos = new Intent(filtro_reguntas.this,Agendar.class);
                startActivity(enviardatos);
            }
        });

    }

}