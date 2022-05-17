package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PantallaBienvenida extends AppCompatActivity {

    LottieAnimationView lottieTime;
    TextView textoBienvenida, textSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);

        lottieTime = findViewById(R.id.lottiean);
        textoBienvenida = findViewById(R.id.txtBienvenido);
        textSub = findViewById(R.id.textViewSub);


        lottieTime.animate().translationY(0).setDuration(4500).setStartDelay(0);
        //textoBienvenida.animate().translationX(300).setDuration(3000).setStartDelay(0);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.desvanecidodos, R.anim.desvanecido);
            }
        },5000);
    }
}