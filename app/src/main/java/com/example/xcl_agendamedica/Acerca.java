package com.example.xcl_agendamedica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Acerca extends AppCompatActivity {

    Button btn;
    TextView textoUno;
   /* FirebaseAuth cAuth;
    FirebaseFirestore cFirestore;
    FirebaseUser User;

    DatabaseReference databaseReference;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        this.setTitle("Acerca de"); //TITULO MOSTRADO EN ACTION BAR


       /* FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        DocumentReference reference;

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        textoUno = findViewById(R.id.id_m7_tit1);*/

       // databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");

        /*reference = firestore.collection("Usuarios").document(id);

        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    String correoUsuario = task.getResult().getString("Correo");

                    textoUno.setText(correoUsuario);
                }else {
                    Toast.makeText(Acerca.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
   /*     databaseReference.child(User.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String correoUsuario = ""+snapshot.child("Correo").getValue();

                    textoUno.setText(correoUsuario);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/





      /*  cFirestore = FirebaseFirestore.getInstance();
        cAuth = FirebaseAuth.getInstance();

        textoUno = findViewById(R.id.id_m7_tit1);
        btn = findViewById(R.id.btnacerca);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = cAuth.getCurrentUser().getUid();
            }
        });  */
    }


}