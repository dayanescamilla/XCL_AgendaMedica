package com.example.xcl_agendamedica;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Documentos extends AppCompatActivity {

    //REFERENCIAR DATOS
    private ImageView imagenUno;
    public Uri imageUr;
    //BASE DE DATOS
    private FirebaseStorage sFirebase;
    private StorageReference storageReference;
    FirebaseAuth cAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //BOTON DE RETROCESO DE ACTION BAR
        this.setTitle("Documentos"); //TITULO MOSTRADO EN ACTION BAR

        //INSTANCIAR DATOS

        //BASE DE DATOS
        sFirebase = FirebaseStorage.getInstance();
        storageReference = sFirebase.getReference();
        cAuth = FirebaseAuth.getInstance();

        //IMAGEVIEW
        imagenUno = findViewById(R.id.imageView2);

        //EVENTO ONCLICK A IMAGEVIEW
        imagenUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });
    }

    private void choosePicture() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!= null){
            imageUr = data.getData();
            imagenUno.setImageURI(imageUr);
            uploadPicture();
        }
    }

    //FUNCION PARA SUBIR IMAGEN
    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Subiendo");
        pd.setMessage("Se esta subiendo su archivo, espere un momento por favor");
        pd.show();

        //GENERARA UN ID ALEATORIO PARA CADA IMAGEN
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUr)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Se subió con éxito.", Snackbar.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure (@NonNull Exception e){
                    pd.dismiss();
                    Snackbar.make(findViewById(android.R.id.content),"Error al subir la imagen.", Snackbar.LENGTH_LONG).show();
                }
        });
    }
}