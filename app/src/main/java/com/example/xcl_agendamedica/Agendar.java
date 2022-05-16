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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Agendar extends AppCompatActivity implements View.OnClickListener {

    //REFERENCIAR DATOS

    //BOTONES, EDITTEXT, RADIOBUTTON, RADIOGROUP
    Button btnFecha, btnHora, btnAgendar;
    EditText eFecha, eHora, eNombre, eEdad, eRazon;
    RadioButton rdPareja, rdIndividual, rdFamiliar, rdPresencial, rdVirtual;
    RadioGroup rdGroupUno, rdGroupDos;
    //CALENDARIO
    private int dia, mes, year, hora, min;
    //BASE DE DATOS
    FirebaseAuth cAuth;
    FirebaseFirestore cFirestore;
    //BARRA DE CARGA
    ProgressDialog barraCargando;
    FirebaseUser cUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        this.setTitle("Agendar Cita"); //TITULO MOSTRADO EN ACTION BAR


        //INSTANCIAR DATOS

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
        rdPresencial = findViewById(R.id.id_m5_rdb4);
        rdVirtual = findViewById(R.id.id_m5_rdb5);
        //RADIGROUPS
        rdGroupUno = findViewById(R.id.radioGroupUno);
        rdGroupDos = findViewById(R.id.radioGroupDos);
        //BUTTONS
        btnAgendar = findViewById(R.id.id_m5_btn1);
        btnFecha = findViewById(R.id.id_m5_btn3);
        btnHora = findViewById(R.id.id_m5_btn4);

        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);

        //FUNCION DE BOTON
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fecha = eFecha.getText().toString().trim();
                String Hora = eHora.getText().toString().trim();
                String Nombre = eNombre.getText().toString().trim();
                String Edad = eEdad.getText().toString().trim();
                String Razon = eRazon.getText().toString().trim();
                String Pareja = rdPareja.getText().toString();
                String Individual = rdIndividual.getText().toString();
                String Familiar = rdFamiliar.getText().toString();
                String Presencial = rdPresencial.getText().toString();
                String Virtual = rdVirtual.getText().toString();


                //CONDICION IF PARA SABER SI LOS CAMPOS ESTAN VACIOS DE LO CONTRARIO LLAMARA A LA FUNCION
                if (Fecha.isEmpty() || Hora.isEmpty()  || Nombre.isEmpty()  || Edad.isEmpty() || Razon.isEmpty()){
                    Toast.makeText(Agendar.this, "Completar los campos solicitados.", Toast.LENGTH_LONG).show();
                } else {
                    agendarCita(Fecha, Hora, Nombre, Edad, Razon, Presencial, Virtual, Pareja, Individual, Familiar);
                }

            }
        });
    }

    //METODO PARA AGREGAR LOS DATOS A LA BASE DE DATOS
    private void agendarCita(String fecha, String hora, String nombre, String edad, String razon, String presencial, String virtual, String pareja, String individual, String familiar) {
        //PREOGRESS DIALOG
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

        //CONDICION RADIOBUTTONS
        if (rdGroupUno.getCheckedRadioButtonId() == R.id.id_m5_rdb1){
            citas.put("Tipo de servicio que solicita", pareja);
        } if (rdGroupUno.getCheckedRadioButtonId() == R.id.id_m5_rdb2){
            citas.put("Tipo de servicio que solicita", individual);
        }else {
            citas.put("Tipo de servicio que solicita", familiar);
        }

        if (rdGroupDos.getCheckedRadioButtonId() == R.id.id_m5_rdb4){
            citas.put("Tipo de consulta", presencial);
        } else {
            citas.put("Tipo de consulta", virtual);
        }

        //AGREGAR A COLLECION DE BASE DE DATOS FIREBASE MEDIANTE ID DEL USUARIO

        cFirestore.collection("Cita Medica").document(id).set(citas).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                barraCargando.dismiss();
                Intent citaAgregada = new Intent(Agendar.this,MenuPrincipal.class);
                startActivity(citaAgregada);
                Toast.makeText(Agendar.this,"Se agregó tu cita médica con éxito.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                barraCargando.dismiss();
                //toastMensajeError();
                Toast.makeText(Agendar.this,"Error al agendar tu cita medica", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //DIALOGO DE ALERTA BOTON CANCELAR MEDIANTE ONCLICK
    public void showAlertDialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Cancelar Cita");
        alert.setMessage("¿Estas seguro que deseas cancelar tu cita medica?");
        alert.setPositiveButton("SI", new DialogInterface.OnClickListener() { //ESTABLECER BOTON POSITIVO
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent cancelarCita = new Intent(Agendar.this,MenuPrincipal.class);
                startActivity(cancelarCita);
                Toast.makeText(Agendar.this, "Se cancelo cita medica", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() { //ESTABLECER BOTON NEGATIVO
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.create().show(); //MOSTRAR ALERTA
    }

   /* private void toastMensajeError() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_uno,(ViewGroup) findViewById(R.id.toast_error));


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM,0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }*/

    //FUNCION PARA AGREGAR FECHA MEDIANTE DATAPICKERDIALOG
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
        //FUNCION PARA AGREGAR HORA MEDIANTE TIMEPICKERDIALOG
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