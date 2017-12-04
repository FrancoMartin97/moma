package com.example.franco.moma;

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
}
