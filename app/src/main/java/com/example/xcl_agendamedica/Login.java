package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //LLAMAR A CLASE MENU DESDE CLASE LOGIN
        btn1 = (Button)findViewById(R.id.id_m2_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,MenuPrincipal.class);
                startActivity(i);
            }
        });


        //LLAMAR A CLASE REGISTRO DESDE CLASE LOGIN
        btn2 = (Button)findViewById(R.id.id_m2_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,modulo_registro.class);
                startActivity(i);
            }
        });
    }
}