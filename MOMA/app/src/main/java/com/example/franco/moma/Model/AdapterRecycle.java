package com.example.franco.moma.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franco.moma.Controller.ControllerPinturas;
import com.example.franco.moma.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by franco on 01/12/2017.
 */

public class AdapterRecycle extends RecyclerView.Adapter {

    private Context contexto;
    private List<Pintura> pinturas;
    private Notificador notificador;
    ControllerPinturas controllerPinturas = new ControllerPinturas();

    public AdapterRecycle(Context context, List<Pintura> pinturas) {
        this.contexto = context;
        this.pinturas = pinturas;
        this.notificador =(Notificador) context;
    }

    public void cargarNuevaLista(List<Pintura> pinturas1){
        pinturas.addAll(pinturas1);
        notifyDataSetChanged();
    }

    private class ProductoHolder extends RecyclerView.ViewHolder {

        private View celda;
        private ImageView foto;
        private TextView nombre;


        public ProductoHolder(View itemView) {
            super(itemView);
            celda = itemView;
            foto = (ImageView) itemView.findViewById(R.id.fotoPinturaCard);
            nombre = (TextView) itemView.findViewById(R.id.nombrePinturaCard);
        }


        public void bindProducto (Pintura pintura){
            nombre.setText(pintura.getName());
            controllerPinturas.obtenerFotosPinturas(pintura,foto);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(contexto);
        View view = layoutInflater.inflate(R.layout.detalle_pinturas,parent,false);
        ProductoHolder productoHolder = new ProductoHolder(view);
        return productoHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Pintura pintura = pinturas.get(position);
        ProductoHolder productoHolder = (ProductoHolder) holder;
        productoHolder.bindProducto(pintura);
        productoHolder.celda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pintura p1 = pinturas.get(position);
                notificador.notificarClick(p1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pinturas.size();
    }

    public interface Notificador{
        public void notificarClick(Pintura pinturaClickeada);
    }

}
