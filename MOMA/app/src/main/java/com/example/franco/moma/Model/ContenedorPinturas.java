package com.example.franco.moma.Model;

import com.example.franco.moma.Model.Pintura;

import java.util.List;

/**
 * Created by franco on 01/12/2017.
 */

public class ContenedorPinturas {

    private List<Pintura> paints;

    public List<Pintura> getPinturaList() {
        return paints;
    }

    @Override
    public String toString() {
        return "ClaseContenedora{" +
                "listaDePinturas=" + paints +
                '}';
    }
}
