package com.example.xcl_agendamedica;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    Button btnEdit, btnSalir;
    TextView usernombre, usercorreo, usertelefono, useremergencia;
    View vista;
    //BASE DE DATOS
    FirebaseFirestore cFirestore;
    FirebaseAuth cAuth;
    DocumentReference documentReference, documentReference1;
    FirebaseUser cUsers;
    // variable usuario
    String idUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // intancias de firestore
        cAuth = FirebaseAuth.getInstance();
        cFirestore = FirebaseFirestore.getInstance();
        //obtener id
        idUser = cAuth.getCurrentUser().getUid();
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnEdit = vista.findViewById(R.id.id_m12_btn1);
        btnSalir = vista.findViewById(R.id.id_m12_btn2);
        //txt donde se guardaran los datos
        usernombre = vista.findViewById(R.id.id_m12_cjt1);
        usercorreo = vista.findViewById(R.id.id_m12_cjt2);
        usertelefono = vista.findViewById(R.id.id_m12_cjt3);
        useremergencia= vista.findViewById(R.id.id_m12_cjt4);
        //BASE DE DATOS
        cUsers = FirebaseAuth.getInstance().getCurrentUser();
        String ID = cUsers.getUid();
        cFirestore = FirebaseFirestore.getInstance();

        documentReference = cFirestore.collection("Usuarios").document(ID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    String nombreUsuario = task.getResult().getString("Nombre del usuario");
                    String correoUsuario = task.getResult().getString("Correo");

                    usernombre.setText(nombreUsuario);
                    usercorreo.setText(correoUsuario);

                } else {
                    Toast.makeText(getContext(), "Error, revisa tu conexion a internet", Toast.LENGTH_SHORT).show();
                }

            }
        });


        documentReference1 = cFirestore.collection("Informacion").document(ID);
        documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    String telefonoUsuario = task.getResult().getString("Telefono");
                    String emergenciaUsuario = task.getResult().getString("Telefono de emergencia");

                    usertelefono.setText(telefonoUsuario);
                    useremergencia.setText(emergenciaUsuario);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Editar_perfil.class);
                getActivity().startActivity(i);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cAuth.signOut();
                Toast.makeText(getActivity(),"Se cerro Sesion con exito", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(getActivity(), Login.class);
                getActivity().startActivity(i2);
            }
        });

        return vista;

    }
}