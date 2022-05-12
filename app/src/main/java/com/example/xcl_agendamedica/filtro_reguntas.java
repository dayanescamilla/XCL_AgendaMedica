package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class filtro_reguntas extends AppCompatActivity {

    Button btnEnivar, btnCancelar;
    RadioGroup grupoUno;
    RadioButton siUno, noUno, siDos, noDos, siTres, noTres, siCuatro, noCuatro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_reguntas);

        btnEnivar = findViewById(R.id.id_m9_btn1);
        btnCancelar = findViewById(R.id.id_m9_btn2);
        siUno = findViewById(R.id.id_m9_rd1);
        noUno = findViewById(R.id.id_m9_rd2);
        siDos = findViewById(R.id.id_m9_rd3);
        noDos = findViewById(R.id.id_m9_rd4);
        siTres = findViewById(R.id.id_m9_rd5);
        noTres = findViewById(R.id.id_m9_rd6);
        siCuatro = findViewById(R.id.id_m9_rd7);
        noCuatro = findViewById(R.id.id_m9_rd8);
        grupoUno = findViewById(R.id.id_m9_radioGroup1);

        //DIRIGIRSE A MODULO DE AGENDAR CITAS
        btnEnivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noUno.isChecked() && noDos.isChecked() && (noTres.isChecked() || siTres.isChecked())  && noCuatro.isChecked()){
                    Intent enviardatos = new Intent(filtro_reguntas.this,Agendar.class);
                    startActivity(enviardatos);
                }else {
                   // finish();
                    //startActivity(new Intent(filtro_reguntas.this,MenuPrincipal.class));
                    showAlertDialog();
                  // Toast.makeText(filtro_reguntas.this, "No puedes agendar una cita medica", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelar = new Intent(filtro_reguntas.this,MenuPrincipal.class);
                startActivity(cancelar);
            }
        });
    }
    public void showAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Lo sentimos mucho");
        alert.setMessage("No calificas para poder realizar una cita medica, para saber mas acerca de este problema comunicate con la clínica");
        alert.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() { //ESTABLECER BOTON POSITIVO
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent alerta1 = new Intent(filtro_reguntas.this,MenuPrincipal.class);
                startActivity(alerta1);
            }
        });
        alert.create().show(); //MOSTRAR ALERTA
    }
}