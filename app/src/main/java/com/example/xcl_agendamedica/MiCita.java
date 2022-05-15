package com.example.xcl_agendamedica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiCita#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiCita extends Fragment {

    TextView fechaCita, horaCita, nombreCita, edadCita, razonCita, servicioCita, consultaCita;
    View vista;
    //BASE DE DATOS
    FirebaseFirestore cFirestore;
    FirebaseAuth cAuth;
    DocumentReference datosCita ;
    FirebaseUser cUser;
    // variable usuario
    String idUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiCita() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiCita.
     */
    // TODO: Rename and change types and number of parameters
    public static MiCita newInstance(String param1, String param2) {
        MiCita fragment = new MiCita();
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
        // Inflate the layout for this fragment

        vista =  inflater.inflate(R.layout.fragment_mi_cita, container, false);

        cUser = FirebaseAuth.getInstance().getCurrentUser();
        String ID = cUser.getUid();
        cFirestore = FirebaseFirestore.getInstance();

        fechaCita = vista.findViewById(R.id.id_m14_txt1);
        horaCita = vista.findViewById(R.id.id_m14_txt2);
        nombreCita = vista.findViewById(R.id.id_m14_txt3);
        edadCita = vista.findViewById(R.id.id_m14_txt4);
        razonCita = vista.findViewById(R.id.id_m14_txt5);
        servicioCita = vista.findViewById(R.id.id_m14_txt6);
        consultaCita = vista.findViewById(R.id.id_m14_txt7);

        datosCita = cFirestore.collection("Cita Medica").document(ID);
        datosCita.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    String fechaUsuario = task.getResult().getString("Fecha");
                    fechaCita.setText(fechaUsuario);
                    String horaUsuario = task.getResult().getString("Hora");
                    horaCita.setText(horaUsuario);
                    String nombreUsuario = task.getResult().getString("Nombre");
                    nombreCita.setText(nombreUsuario);
                    String edadUsuario = task.getResult().getString("Edad");
                    edadCita.setText(edadUsuario);
                    String razonUsuario = task.getResult().getString("Razon");
                    razonCita.setText(razonUsuario);
                    String servicioUsuario = task.getResult().getString("Tipo de consulta");
                    servicioCita.setText(servicioUsuario);
                    String consultaUsuario = task.getResult().getString("Tipo de servicio que solicita");
                    consultaCita.setText(consultaUsuario);

                } else {
                    alerta();
                }
            }
        });


    return vista;
    }

    public void alerta(){
            AlertDialog.Builder alertAgendar = new AlertDialog.Builder(getContext());
            alertAgendar.setTitle("Filtro de Preguntas");
            alertAgendar.setMessage("Para poder agendar una cita medica se necesita responder un cuestionario muy simple.");
            //METODO DE ACCION POSITIVA
            alertAgendar.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent i2 = new Intent(getActivity(),filtro_reguntas.class);
                    getActivity().startActivity(i2);
                }
            });

            //METODO DE ACCION NEGATIVA
            alertAgendar.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getContext(), "Se cancelo la cita medica", Toast.LENGTH_SHORT).show();
                }
            });
            alertAgendar.show();
    }
}