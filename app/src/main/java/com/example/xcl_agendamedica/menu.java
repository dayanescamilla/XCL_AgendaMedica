package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class menu extends AppCompatActivity {

    //visor carrusel
    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //constructor carrusel
        int images[] = {R.drawable.b1, R.drawable.b2, R.drawable.b3};

        v_flipper = findViewById(R.id.v_flipper);

        //sentencia de validación carrusel
        for (int image : images) {
            flipperImages(image);
        }
    }

        public void flipperImages(int image){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);

            v_flipper.addView(imageView);
            v_flipper.setFlipInterval(3000);
            v_flipper.setAutoStart(true);

            //activar animación carrusel
            v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
            v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        }

    }
