package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Agendar extends AppCompatActivity implements View.OnClickListener {

    Button btnFecha, btnHora;
    EditText eFecha, eHora;
    private int dia, mes, year, hora, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        this.setTitle("Agendar Cita"); //TITULO MOSTRADO EN ACTION BAR


        btnFecha = (Button)findViewById(R.id.id_m5_btn3);
        btnHora = (Button)findViewById(R.id.id_m5_btn4);
        eFecha = (EditText)findViewById(R.id.m5_etxt1);
        eHora = (EditText)findViewById(R.id.id_m5_etxt2);

        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);
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
                    eHora.setText(hour_OfDay+":"+minute);
                }
            },hora,min,false);
            timePickerDialog.show();
        }
    }
}