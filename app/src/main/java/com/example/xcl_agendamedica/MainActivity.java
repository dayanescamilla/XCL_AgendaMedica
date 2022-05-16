package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    //REFERENICAR DATOS
    Button id_m1_btn1;

    FirebaseAuth cAuth;
    FirebaseFirestore cFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_XCL_AgendaMedica);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();

        //INSTANCIAR DATOS

        //BOTON
        id_m1_btn1 = findViewById(R.id.id_m1_btn1);

        //LLAMAR A ACTIVITY LOGIN DESDE ACTIVITY MAINACTIVITY
        id_m1_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ilogin = new Intent(MainActivity.this,Login.class);
                startActivity(ilogin);
            }
        });
    }
}