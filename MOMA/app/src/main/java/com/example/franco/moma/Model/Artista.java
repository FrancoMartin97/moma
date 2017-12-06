package com.example.franco.moma.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ma on 04/12/17.
 */

public class Artista {


    private String Influenced_by;
    private String artistId;
    private String name;
    private String nationality;

    public String getArtistId() {
        return artistId;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setInfluenced_by(String influenced_by) {
        Influenced_by = influenced_by;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
