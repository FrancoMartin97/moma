package com.example.franco.moma;

import com.google.gson.annotations.SerializedName;

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
