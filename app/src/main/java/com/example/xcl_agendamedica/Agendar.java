package com.example.xcl_agendamedica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

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

        btnFecha = (Button)findViewById(R.id.id_m5_btn3);
        btnHora = (Button)findViewById(R.id.id_m5_btn4);
        eFecha = (EditText)findViewById(R.id.m5_etxt1);
        eHora = (EditText)findViewById(R.id.id_m5_etxt2);

        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);
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