package com.example.franco.moma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetallePinturaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    Artista artistaSeleccionado;
    private List<Artista> personaList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pintura);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Pintura pintura = (Pintura) bundle.getSerializable("Pintura");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        readDatabase();
        encontrarArtista(pintura);
        ImageView imageView = (ImageView) findViewById(R.id.fotoDetalle);
        TextView textView = (TextView) findViewById(R.id.textoDetalle);
        textView.setText(artistaSeleccionado.getArtistId());

    }

    public void readDatabase(){

        DatabaseReference referencia = mDatabase.child("artist");

        referencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot personaSnapshot: dataSnapshot.getChildren()) {

                    Artista persona = personaSnapshot.getValue(Artista.class);
                    personaList.add(persona);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void readOnePerson(View view){
        DatabaseReference referencia = mDatabase.child("artists").child("0");

        referencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Artista persona = dataSnapshot.getValue(Artista.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public void writeDatabase(View view){

        //SI EL CHILD NO EXISTE, LO CREA

        DatabaseReference referencia = mDatabase.child("artists").child("5");

        Artista persona = new Artista();
        persona.setName("Maxi");
        persona.setInfluenced_by("");
        referencia.setValue(persona);
        referencia = mDatabase.child("artists").child("4");

    }


    public void writeListDatabase(View view){

    }

    public void writePushDatabase(View view){


    }



    public  void encontrarArtista(Pintura pintura){
        String id = pintura.getArtistId();
        for (Artista unArtista: personaList) {
            if(unArtista.getArtistId().equals(id)){
                artistaSeleccionado = unArtista;
            }
        }
    }
    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
    }

}
