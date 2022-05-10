package com.example.xcl_agendamedica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Agendar extends AppCompatActivity implements View.OnClickListener {

    Button btnFecha, btnHora, btnAgendar;
    EditText eFecha, eHora, eNombre, eEdad, eRazon;
    RadioButton rdPareja, rdIndividual, rdFamiliar, rdPresencial, rdVirtual;
    //CALENDARIO
    private int dia, mes, year, hora, min;
    //BASE DE DATOS
    FirebaseAuth cAuth;
    FirebaseFirestore cFirestore;
    //BARRA DE CARGA
    ProgressDialog barraCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        this.setTitle("Agendar Cita"); //TITULO MOSTRADO EN ACTION BAR

        //FIREBASE
        cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();
        //BARRA DE ALERTA
        barraCargando = new ProgressDialog(this);
        //EDITTEXT
        eFecha = findViewById(R.id.m5_etxt1);
        eHora = findViewById(R.id.id_m5_etxt2);
        eNombre = findViewById(R.id.id_m5_etxt3);
        eEdad = findViewById(R.id.id_m5_etxt4);
        eRazon = findViewById(R.id.id_m5_etxt5);
        //RADIOBUTTONS
        rdPareja = findViewById(R.id.id_m5_rdb1);
        rdIndividual = findViewById(R.id.id_m5_rdb2);
        rdFamiliar = findViewById(R.id.id_m5_rdb3);
        //BUTTONS
        btnAgendar = findViewById(R.id.id_m5_btn1);
        btnFecha = findViewById(R.id.id_m5_btn3);
        btnHora = findViewById(R.id.id_m5_btn4);

        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);


        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fecha = eFecha.getText().toString().trim();
                String Hora = eHora.getText().toString().trim();
                String Nombre = eNombre.getText().toString().trim();
                String Edad = eEdad.getText().toString().trim();
                String Razon = eRazon.getText().toString().trim();
                /*String Pareja = rdPareja.getText().toString();
                String Individual = rdIndividual.getText().toString();
                String Familiar = rdFamiliar.getText().toString();
                String Presencial = rdPresencial.getText().toString();
                String Virtual = rdVirtual.getText().toString(); */


                if (Fecha.isEmpty() && Hora.isEmpty() && Nombre.isEmpty() && Edad.isEmpty() && Razon.isEmpty() /* && (Pareja.isEmpty() || Individual.isEmpty() || Familiar.isEmpty()) && (Presencial.isEmpty() || Virtual.isEmpty()) */ ){
                    Toast.makeText(Agendar.this, "Llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    agendarCita(Fecha, Hora, Nombre, Edad, Razon /*, Pareja, Individual, Familiar, Presencial, Virtual*/);
                }

            }
        });
    }

    private void agendarCita(String fecha, String hora, String nombre, String edad, String razon /*, String pareja, String individual, String familiar, String presencial, String virtual */) {
        barraCargando.setTitle("Cargando");
        barraCargando.setMessage("Tu cita esta siendo procesada");
        barraCargando.show();

        String id = cAuth.getCurrentUser().getUid();

        Map<String,Object> citas = new HashMap<>();
        citas.put("Fecha", fecha);
        citas.put("Hora", hora);
        citas.put("Nombre", nombre);
        citas.put("Edad", edad);
        citas.put("Razon", razon);
       /* citas.put("Tipo de Servicio", pareja);
        citas.put("Tipo de Servicio", individual);
        citas.put("Tipo de Servicio", familiar);
        citas.put("Tipo de Consulta", presencial);
        citas.put("Tipo de Consulta", virtual); */


        cFirestore.collection("Cita Medica").document(id).set(citas).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                barraCargando.dismiss();
                Snackbar.make(findViewById(android.R.id.content), "Se agrego la cita", Snackbar.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                barraCargando.dismiss();
                Toast.makeText(Agendar.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }


    //DIALOGO DE ALERTA BOTON CANCELAR
    public void showAlertDialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Cancelar Cita");
        alert.setMessage("¿Estas seguro que deseas cancelar tu cita medica?");
        alert.setPositiveButton("SI", new DialogInterface.OnClickListener() { //ESTABLECER BOTON POSITIVO
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent i2 = new Intent(Agendar.this,MenuPrincipal.class);
                startActivity(i2);

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() { //ESTABLECER BOTON NEGATIVO
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Agendar.this,"Continua agendado tu cita medica", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show(); //MOSTRAR ALERTA
    }

    @Override
    public void onClick(View view) {
        if (view == btnFecha){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    eFecha.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                }
            },dia,mes,year);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() -1000); //NO SE PODRAN ELEGIR FECHAS PASADAS A LA ACTUAL
            datePickerDialog.show();
        }
        if (view == btnHora){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            min = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour_OfDay, int minute) {
                    eHora.setText(hour_OfDay+":00");
                }
            },hora,min,false);
            timePickerDialog.show();
        }
    }




}