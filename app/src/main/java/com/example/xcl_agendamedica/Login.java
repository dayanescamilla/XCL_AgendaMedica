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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button btnAcceso, btnRegistro;
    EditText correo, contra;

    FirebaseAuth cAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cAuth = FirebaseAuth.getInstance();


        btnAcceso = findViewById(R.id.id_m2_btn1);
        correo = findViewById(R.id.id_m2_cj1);
        contra = findViewById(R.id.id_m2_cjt2);

        //LLAMAR A CLASE MENU DESDE CLASE LOGIN
        btnAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoUsuario = correo.getText().toString().trim();
                String contraUsuario = contra.getText().toString().trim();

                if (correoUsuario.isEmpty() || contraUsuario.isEmpty()){
                    Toast.makeText(Login.this, "Completar campos correo y contraseña", Toast.LENGTH_SHORT).show();
                }else {
                    Acceso(correoUsuario,contraUsuario);
                }
            }
        });


        //LLAMAR A CLASE REGISTRO DESDE CLASE LOGIN
        btnRegistro = findViewById(R.id.id_m2_btn2);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,modulo_registro.class);
                startActivity(i);
            }
        });
    }

    private void Acceso(String correoUsuario, String contraUsuario) {
        cAuth.signInWithEmailAndPassword(correoUsuario,contraUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this,MenuPrincipal.class));
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Verifique sus datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //METODO PARA PERMANECER ABIERTA LA SESION
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuario = cAuth.getCurrentUser();
        if (usuario != null){
            startActivity(new Intent(Login.this, MenuPrincipal.class));
            finish();
        }
    }

}