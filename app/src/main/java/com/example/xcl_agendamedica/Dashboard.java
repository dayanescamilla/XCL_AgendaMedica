package com.example.xcl_agendamedica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment {

    ViewFlipper vflipper;
    CardView cardAgendar,cardAcerca,cardAyuda,cardDocumentos;
    View vista;

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
        cardAgendar = vista.findViewById(R.id.cardViewAgendar);
        cardAcerca = vista.findViewById(R.id.cardViewAcerca);
        cardAyuda = vista.findViewById(R.id.cardViewAyuda);
       // cardDocumentos = vista.findViewById(R.id.cardViewDocumentos);

        //MOSTRAR ALERTA DE DIALOGO
        cardAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        //DIRIGIRSE A MODULO ACERCA DE
        cardAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Acerca.class);
                getActivity().startActivity(i);
            }
        });

        //DIRIGIRSE A MODULO AYDUA
        cardAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Ayuda.class);
                getActivity().startActivity(i);
            }
        });

      /*  cardDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Documentos.class);
                getActivity().startActivity(i);
            }
        }); */


        int images[] = {R.drawable.b1, R.drawable.b2, R.drawable.b3};
        vflipper = vista.findViewById(R.id.v_flipper);
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