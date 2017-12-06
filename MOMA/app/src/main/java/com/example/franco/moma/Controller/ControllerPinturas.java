package com.example.franco.moma.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.franco.moma.Model.DAOPinturas;
import com.example.franco.moma.Model.Pintura;
import com.example.franco.moma.Utils.ResultListener;
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

public class ControllerPinturas {

    public void obtenerPinturas(final ResultListener<List<Pintura>> listenerFromView){
        DAOPinturas daoPinturas = new DAOPinturas();
        daoPinturas.obtenerPinturasDeInternet(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                if(resultado != null) {
                    listenerFromView.finish(resultado);
                }
            }
        });
    }

    public void obtenerFotosPinturas(Pintura pintura, final ImageView imageView){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference reference = storage.getReference();
            reference = reference.child(pintura.getImage());
            try {
                final File file = File.createTempFile("fotos", "png");
                reference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        imageView.setImageBitmap(bitmap);
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
