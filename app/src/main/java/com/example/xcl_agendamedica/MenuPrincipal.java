package com.example.xcl_agendamedica;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //Boton navegacion
        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView);
        btnNav.setOnNavigationItemSelectedListener(navlistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1,new Dashboard()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.id_ic_nav_dashboard:
                            selectedFragment = new Dashboard();
                            break;

                        case R.id.id_ic_nav_encuesta:
                            selectedFragment = new EncuestaSatisfaccion();
                            break;

                        case R.id.id_ic_nav_salir:
                            selectedFragment = new Salir();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1,selectedFragment).commit();
                    return true;
                }
            };
}
