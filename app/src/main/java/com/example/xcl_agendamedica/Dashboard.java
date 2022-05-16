package com.example.xcl_agendamedica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment {

    //REFERENCIAR DATOS

    //VIEWFLIPPER, CARDVIEW, VIEW, TEXTVIEW
    ViewFlipper vflipper;
    CardView cardAgendar,cardAcerca,cardAyuda, cardEncuesta;
    View vista;
    TextView nameUsuario;
    //BASE DE DATOS
    FirebaseFirestore cFirestore;
    FirebaseUser cUser;
    DocumentReference documentReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Dashboard() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static Dashboard newInstance(String param1, String param2) {
        Dashboard fragment = new Dashboard();
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
        vista = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //INSTANCIAR DATOS

        //IMAGEVIEW
        int images[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
        vflipper = vista.findViewById(R.id.v_flipper);
        //CARDVIEW
        cardAgendar = vista.findViewById(R.id.cardViewAgendar);
        cardAcerca = vista.findViewById(R.id.cardViewAcerca);
        cardAyuda = vista.findViewById(R.id.cardViewAyuda);
        cardEncuesta = vista.findViewById(R.id.cardViewEncuesta);
        //TEXTOS
        nameUsuario = vista.findViewById(R.id.id_m4_sub2);
        //BASE DE DATOS
        cUser = FirebaseAuth.getInstance().getCurrentUser();
        String ID = cUser.getUid();
        cFirestore = FirebaseFirestore.getInstance();

        //MOSTRAR EL NOMBRE DE USUARIO MEDIANTE EL ID ALMACENADO EN LA COLLECCION
        documentReference = cFirestore.collection("Usuarios").document(ID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    String nombreUsuario = task.getResult().getString("Nombre del usuario");
                    nameUsuario.setText(nombreUsuario);
                } else {
                    Toast.makeText(getContext(), "Error, revisa tu conexión a internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //MOSTRAR ALERTA DE DIALOGO
        cardAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertAgendar = new AlertDialog.Builder(getContext());
                alertAgendar.setTitle("Filtro de Preguntas");
                alertAgendar.setMessage("Para poder agendar una cita médica se necesita responder un cuestionario muy simple.");
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
        });

        //DIRIGIRSE A MODULO ACERCA DE
        cardAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cdAcerca = new Intent(getActivity(), Acerca.class);
                getActivity().startActivity(cdAcerca);
            }
        });

        //DIRIGIRSE A MODULO AYDUA
        cardAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cdAyuda = new Intent(getActivity(), Ayuda.class);
                getActivity().startActivity(cdAyuda);
            }
        });

        //DIRIGIRSE A MODULO ENUCESTA DE SATISFACCION
        cardEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cdEncuesta = new Intent(getActivity(), encuesta.class);
                getActivity().startActivity(cdEncuesta);
            }
        });


        //SENTENTICA DE VALIDACION CARRUSEL
        for (int image : images) {
            flipperImages(image);
        }
        return vista;
    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        vflipper.addView(imageView);
        vflipper.setFlipInterval(3000);
        vflipper.setAutoStart(true);
        //ACTIVAR ANIMACION DE CARRUSEL
        vflipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        vflipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

}