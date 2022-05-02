package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Editar_perfil extends AppCompatActivity {

    Button subirDoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        subirDoc = findViewById(R.id.id_m13_btn1);

        subirDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Editar_perfil.this,Documentos.class);
                startActivity(i);
            }
        });
    }
}