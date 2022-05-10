package com.example.xcl_agendamedica;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class EncuestaSatisfaccion extends Fragment {

    Button btnenviar;
    RadioButton Rd1, Rd2, Rd3, Rd4, Rd5, Rd6, Rd7, Rd8, Rd9, Rd10, Rd11, Rd12, Rd13, Rd14, Rd15;
    View vista;
    RadioGroup Rdg1, Rdg2;
    private FirebaseFirestore cFirestore;
    FirebaseAuth cAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_encuesta_satisfaccion, container, false);
        //firebase
        cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();

        btnenviar = vista.findViewById(R.id.id_an_btn_1);
        Rd1 = vista.findViewById(R.id.radioButton);
        Rd2 = vista.findViewById(R.id.radioButton2);
        Rd3 = vista.findViewById(R.id.radioButton3);
        Rd4 = vista.findViewById(R.id.radioButton4);
        Rd5 = vista.findViewById(R.id.radioButton5);
        Rd6 = vista.findViewById(R.id.radioButton6);
        Rd7 = vista.findViewById(R.id.radioButton7);
        Rd8 = vista.findViewById(R.id.radioButton8);
        Rd9 = vista.findViewById(R.id.radioButton9);
        Rd10 = vista.findViewById(R.id.radioButton10);
        Rd11 = vista.findViewById(R.id.radioButton11);
        Rd12 = vista.findViewById(R.id.radioButton12);
        Rd13 = vista.findViewById(R.id.radioButton13);
        Rd14 = vista.findViewById(R.id.radioButton14);
        Rd15 = vista.findViewById(R.id.radioButton15);
        Rdg1 = vista.findViewById(R.id.radiogroup1);
        Rdg2 = vista.findViewById(R.id.radiogroup2);

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
        return vista;
    }


    private void Encuesta(String opc1, String opc2, String opc3, String opc4, String opc5, String opc6, String opc7, String opc8, String opc9, String opc10, String opc11, String opc12, String opc13, String opc14, String opc15) {

                String id = cAuth.getCurrentUser().getUid();

                Map<String,Object> Map3 = new HashMap<>();
                Map3.put("1-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?", opc1);
                Map3.put("1.1-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?", opc2);
                Map3.put("1.2-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?", opc3);
                Map3.put("1.3-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?", opc4);
                Map3.put("1.4-En general ¿cómo calificalificaría la calidad y su experiencia con el servicio mediente la aplicación?", opc5);

                Map3.put("2.-¿Tuviste problemas al agendar una cita?", opc6);
                Map3.put("2.1-¿Tuviste problemas al agendar una cita?", opc7);
                Map3.put("3.-¿Cómo te parecio la consulta virtual?", opc8);
                Map3.put("3.1-¿Cómo te parecio la consulta virtual?", opc9);
                Map3.put("3.2-¿Cómo te parecio la consulta virtual?", opc10);
                Map3.put("3.3-¿Cómo te parecio la consulta virtual?", opc11);
                Map3.put("4.-¿Volverias agendar tu cita por medio de la aplicación?", opc12);
                Map3.put("4.1-¿Volverias agendar tu cita por medio de la aplicación?", opc13);
                Map3.put("5.-¿Recomendarias esta aplicación a tus conocidos?", opc14);
                Map3.put("5.1-¿Recomendarias esta aplicación a tus conocidos?", opc15);



        cFirestore.collection("Encuesta de satisfacción").document(id).set(Map3).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Se registro con exito tu encuesta", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al guardar sus datos", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}