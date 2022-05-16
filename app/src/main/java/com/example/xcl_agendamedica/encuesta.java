package com.example.xcl_agendamedica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class encuesta extends AppCompatActivity {

    //REFERENCIAR DATOS

    //BOTONES, RADIOBUTTONS, RADIO GROUP
    Button btnenviar;
    RadioButton Rd1, Rd2, Rd3, Rd4, Rd5, Rd6, Rd7, Rd8, Rd9, Rd10, Rd11, Rd12, Rd13, Rd14, Rd15;
    RadioGroup Rdg1, Rdg2, Rdg3, Rdg4, Rdg5;

    //BASE DE DATOS
    FirebaseFirestore cFirestore;
    FirebaseAuth cAuth;

    //BARRA DE CARGA
    ProgressDialog barraCargando;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR


        cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();
        //barra de cargado
        barraCargando = new ProgressDialog(this);

        btnenviar = findViewById(R.id.id_m10_btn1);

        //radiobuttons
        Rd1 = findViewById(R.id.radioButton);
        Rd2 = findViewById(R.id.radioButton2);
        Rd3 = findViewById(R.id.radioButton3);
        Rd4 = findViewById(R.id.radioButton4);
        Rd5 = findViewById(R.id.radioButton5);
        Rd6 = findViewById(R.id.radioButton6);
        Rd7 = findViewById(R.id.radioButton7);
        Rd8 = findViewById(R.id.radioButton8);
        Rd9 = findViewById(R.id.radioButton9);
        Rd10 = findViewById(R.id.radioButton10);
        Rd11 = findViewById(R.id.radioButton11);
        Rd12 = findViewById(R.id.radioButton12);
        Rd13 = findViewById(R.id.radioButton13);
        Rd14 = findViewById(R.id.radioButton14);
        Rd15 = findViewById(R.id.radioButton15);
        //radiogroups
        Rdg1 = findViewById(R.id.radiogroup1);
        Rdg2 = findViewById(R.id.radiogroup2);
        Rdg3 = findViewById(R.id.radiogroup3);
        Rdg4 = findViewById(R.id.radiogroup4);
        Rdg5 = findViewById(R.id.radiogroup5);

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opc1 = Rd1.getText().toString();
                String opc2 = Rd2.getText().toString();
                String opc3 = Rd3.getText().toString();
                String opc4 = Rd4.getText().toString();
                String opc5 = Rd5.getText().toString();
                String opc6 = Rd6.getText().toString();
                String opc7 = Rd7.getText().toString();
                String opc8 = Rd8.getText().toString();
                String opc9 = Rd9.getText().toString();
                String opc10 = Rd10.getText().toString();
                String opc11 = Rd11.getText().toString();
                String opc12 = Rd12.getText().toString();
                String opc13 = Rd13.getText().toString();
                String opc14 = Rd14.getText().toString();
                String opc15 = Rd15.getText().toString();

                Encuesta (opc1, opc2, opc3, opc4,opc5, opc6, opc7, opc8, opc9,opc10, opc11, opc12, opc13, opc14,opc15);
            }
        });

    }

    private void Encuesta(String opc1, String opc2, String opc3, String opc4, String opc5, String opc6, String opc7, String opc8, String opc9, String opc10, String opc11, String opc12, String opc13, String opc14, String opc15) {

        barraCargando.setTitle("Cargando");
        barraCargando.setMessage("Tu encuesta esta siedo procesada");
        barraCargando.show();

        String id = cAuth.getCurrentUser().getUid();
        Map<String,Object> Encuesta = new HashMap<>();

        if (Rdg1.getCheckedRadioButtonId() == R.id.radioButton){
            Encuesta.put("1.-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?", opc1);
        } else
        if (Rdg1.getCheckedRadioButtonId() == R.id.radioButton2){
            Encuesta.put("1.-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?",opc2);
        } else
        if (Rdg1.getCheckedRadioButtonId() == R.id.radioButton3){
            Encuesta.put("1.-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?",opc3);
        }else
        if (Rdg1.getCheckedRadioButtonId() == R.id.radioButton4){
            Encuesta.put("1.-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?",opc4);
        }else {
            Encuesta.put("1.-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?", opc5);
        }

        if (Rdg2.getCheckedRadioButtonId() == R.id.radioButton6){
            Encuesta.put("2.-¿Tuviste problemas al agendar una cita?", opc6);
        }
        else {
            Encuesta.put("2.-¿Tuviste problemas al agendar una cita?", opc7);
        }

        if (Rdg3.getCheckedRadioButtonId() == R.id.radioButton8){
            Encuesta.put("3.-¿Cómo te parecio la consulta virtual?", opc8);
        } else
        if (Rdg3.getCheckedRadioButtonId() == R.id.radioButton9){
            Encuesta.put("3.-¿Cómo te parecio la consulta virtual?",opc9);
        } else
        if (Rdg3.getCheckedRadioButtonId() == R.id.radioButton10){
            Encuesta.put("3.-¿Cómo te parecio la consulta virtual?",opc10);
        }
        else {
            Encuesta.put("3.-¿Cómo te parecio la consulta virtual?", opc11);
        }

        if (Rdg4.getCheckedRadioButtonId() == R.id.radioButton12){
            Encuesta.put("4.-¿Volverias agendar tu cita por medio de la aplicación?", opc12);
        }
        else {
            Encuesta.put("4.-¿Volverias agendar tu cita por medio de la aplicación?", opc13);
        }

        if (Rdg5.getCheckedRadioButtonId() == R.id.radioButton14){
            Encuesta.put("5.-¿Recomendarias esta aplicación a tus conocidos?", opc14);
        }
        else {
            Encuesta.put("5.-¿Recomendarias esta aplicación a tus conocidos?", opc15);
        }



        cFirestore.collection("Encuesta de satisfacción").document(id).set(Encuesta).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                barraCargando.dismiss();
                Toast.makeText(encuesta.this, "Se registro con exito tu encuesta", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                barraCargando.dismiss();
                Toast.makeText(encuesta.this, "Error al subir encuesta", Toast.LENGTH_SHORT).show();
            }
        });
    }
}