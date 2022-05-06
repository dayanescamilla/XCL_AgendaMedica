package com.example.xcl_agendamedica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Editar_perfil extends AppCompatActivity {

    Button subirDoc, guardar;
    EditText tel, telemer;
    FirebaseAuth cAuth;
    FirebaseFirestore cFirestore;
    //String idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        this.setTitle("Editar Perfil"); //TITULO MOSTRADO EN ACTION BAR

        subirDoc = findViewById(R.id.id_m13_btn1);
        guardar = findViewById(R.id.id_m13_btn2);
        tel = findViewById(R.id.id_m13_cjt3);
        telemer = findViewById(R.id.id_m13_cjt4);

        //firebase
        cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();
        // recuperar id de usuario
        //idUser = cAuth.getCurrentUser().getUid();

        //subir datos
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telefono = tel.getText().toString().trim();
                String emergencia = telemer.getText().toString().trim();

                if (telefono.isEmpty() || emergencia.isEmpty()){
                    Toast.makeText(Editar_perfil.this, "Completar los datos solicitados", Toast.LENGTH_SHORT).show();
                }else{
                    Registro(telefono, emergencia);
                }
            }
        });

        subirDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Editar_perfil.this,Documentos.class);
                startActivity(i);
            }
        });
    }


    //subir datos a firestore
    private void Registro(String telefono, String emergencia) {
        cAuth.createUserWithEmailAndPassword(telefono, emergencia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                String id = cAuth.getCurrentUser().getUid();
                Map<String,Object> Map2 = new HashMap<>();
                Map2.put("Telefono", telefono);
                Map2.put("Telefono de emergencia", emergencia);


                cFirestore.collection("Informacion").document(id).set(Map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(Editar_perfil.this, "Se registro con exito", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Editar_perfil.this, "Error al guardar sus datos", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Editar_perfil.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}