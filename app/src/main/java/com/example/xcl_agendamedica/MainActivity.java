package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button id_m1_btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_XCL_AgendaMedica);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LLAMAR A ACTIVITY LOGIN DESDE ACTIVITY MAINACTIVITY
        id_m1_btn1 = findViewById(R.id.id_m1_btn1);
        id_m1_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ilogin = new Intent(MainActivity.this,Login.class);
                startActivity(ilogin);
            }
        });
    }
}