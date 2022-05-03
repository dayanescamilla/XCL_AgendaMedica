package com.example.xcl_agendamedica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class modulo_registro extends AppCompatActivity {

    Button agregarRegistro;
    EditText nombre, correo, contra;
    CheckBox casillaEdad;
    FirebaseAuth cAuth;
    FirebaseFirestore cFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_registro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        
        cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();
        
        nombre = findViewById(R.id.id_m3_edittxt1);
        correo = findViewById(R.id.id_m3_edittxt2);
        contra = findViewById(R.id.id_m3_edittxt3);
        agregarRegistro = findViewById(R.id.id_m3_btn1);


        
        agregarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = nombre.getText().toString().trim();
                String correoUsuario = correo.getText().toString().trim();
                String contraUsuario = contra.getText().toString().trim();
                
                if (nombreUsuario.isEmpty() || correoUsuario.isEmpty() || contraUsuario.isEmpty()){
                    Toast.makeText(modulo_registro.this, "Completar los datos solicitados", Toast.LENGTH_SHORT).show();
                }else {
                    Registro(nombreUsuario, correoUsuario, contraUsuario);
                }
            }
        });
    }

    private void Registro(String nombreUsuario, String correoUsuario, String contraUsuario) {
    cAuth.createUserWithEmailAndPassword(correoUsuario, contraUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            String id = cAuth.getCurrentUser().getUid();
            Map<String,Object> Map1 = new HashMap<>();
            Map1.put("ID", id);
            Map1.put("Nombre del usuario", nombreUsuario);
            Map1.put("Correo", correoUsuario);
            Map1.put("Contraseña", contraUsuario);

            cFirestore.collection("Usuarios").document(id).set(Map1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(modulo_registro.this, "Se registro con exito", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(modulo_registro.this, "Error al guardar sus datos", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(modulo_registro.this, "Errorrrr", Toast.LENGTH_SHORT).show();
        }
    });
    }


}