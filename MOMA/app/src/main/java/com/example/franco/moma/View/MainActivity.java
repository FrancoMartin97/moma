package com.example.franco.moma.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.franco.moma.Controller.ControllerPinturas;
import com.example.franco.moma.Model.AdapterRecycle;
import com.example.franco.moma.Model.Pintura;
import com.example.franco.moma.R;
import com.example.franco.moma.Utils.ResultListener;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterRecycle.Notificador{

    List<Pintura> pinturas = new ArrayList<>();
    AdapterRecycle adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIntent();

        obtenerPinturas();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);

        //Si NO varia nada de la visual se puede utilizar esta opcion.
        recyclerView.setHasFixedSize(true);

        //Le pedimos que muestre las cosas en forma de lista
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        //Creamos el adaptador del recycler
        AdapterRecycle adapterRecycle = new AdapterRecycle(this,pinturas);
        adapter = adapterRecycle;
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void notificarClick(Pintura pinturaClickeada) {
        Intent intent = new Intent(this,DetallePinturaActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("Pintura",pinturaClickeada);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public void obtenerPinturas(){
        ControllerPinturas controlerPinturas = new ControllerPinturas();
        controlerPinturas.obtenerPinturas(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
            adapter.cargarNuevaLista(resultado);
            }
        });
    }

    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this,Logeo.class));
    }


}
