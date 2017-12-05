package com.example.franco.moma;

import android.net.Uri;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by franco on 01/12/2017.
 */

public class Pintura implements Serializable {

    private String image;
    private String name;
    private String artistId;

    public Pintura(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }
}
