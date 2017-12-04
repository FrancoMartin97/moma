package com.example.franco.moma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterRecycle.Notificador{

    List<Pintura> pinturas = new ArrayList<>();
    AdapterRecycle adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
