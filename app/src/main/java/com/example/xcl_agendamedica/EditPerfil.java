package com.example.xcl_agendamedica;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class EditPerfil extends DialogFragment {

    Button guardar, btnDocumento;
    EditText tel, telemer;
    FirebaseAuth cAuth;
    FirebaseFirestore cFirestore;
    View vista;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_edit_perfil, container, false);

        guardar = vista.findViewById(R.id.id_mm13_btn);
        tel = vista.findViewById(R.id.id_mm13_cjt3);
        telemer = vista.findViewById(R.id.id_mm13_cjt4);
        btnDocumento = vista.findViewById(R.id.id_mm13_btn1);

        //firebase
        cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();
        // recuperar id de usuario
        //idUser = cAuth.getCurrentUser().getUid();




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telefono = tel.getText().toString().trim();
                String emergencia = telemer.getText().toString().trim();

                if (telefono.isEmpty() || emergencia.isEmpty()){
                    Toast.makeText(getContext(), "Completar los datos solicitados", Toast.LENGTH_SHORT).show();
                }else{
                    Registro(telefono, emergencia);
                }
            }
        });

        btnDocumento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Documentos.class);
                getActivity().startActivity(i);
            }
        });

        return vista;

    }

   private void Registro(String telefono, String emergencia) {
        cAuth.createUserWithEmailAndPassword(telefono, emergencia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = cAuth.getCurrentUser().getUid();
                Map<String,Object> Map2 = new HashMap<>();
                Map2.put("Teléfono", telefono);
                Map2.put("Teléfono de emergencia", emergencia);


                cFirestore.collection("Información").document(id).set(Map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Se registro con éxito", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al guardar sus datos", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}