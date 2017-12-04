package com.example.franco.moma;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by franco on 01/12/2017.
 */

public class DAOPinturas {

    public void obtenerPinturasDeInternet(ResultListener<List<Pintura>> listenerFromController){
        ObtenerListaDePinturasDesdeInternet tarea = new ObtenerListaDePinturasDesdeInternet(listenerFromController);
        tarea.execute();
    }

    private class ObtenerListaDePinturasDesdeInternet extends AsyncTask<String, Void, List<Pintura>>{

        ResultListener<List<Pintura>> listener;

        public ObtenerListaDePinturasDesdeInternet(ResultListener<List<Pintura>> listener) {
            this.listener = listener;
        }

        @Override
        protected List<Pintura> doInBackground(String... strings) {
            ContenedorPinturas contenedora = null;
            try {
                HTTPConnectionManager connectionManager = new HTTPConnectionManager();
                String leerElJsonDeInternet = connectionManager.getRequestString("https://api.myjson.com/bins/x858r");
                Gson gson = new Gson();
                contenedora = gson.fromJson(leerElJsonDeInternet, ContenedorPinturas.class);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return contenedora.getPinturaList();
        }

        protected void onPostExecute(List<Pintura> pinturas) {
            listener.finish(pinturas);
        }
    }
}

