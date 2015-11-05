package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by jp on 02.11.15.
 */


@NamedQueries( {
        @NamedQuery( name = Location.GET, query = "SELECT l FROM Location l " )
} )
@Entity
public class Location {


    public static final String GET = "Location.get";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String name;

    private String stadt;

    private String straße;

    private int hausnummer;

    private long telefon;

    private int plz;

    private int likes;

    private String GPS;



    public String getGPS() {
        return GPS;
    }



    public void setGPS( String GPS ) {
        this.GPS = GPS;
    }



    public int getPlz() {
        return plz;
    }



    public void setPlz( int plz ) {
        this.plz = plz;
    }



    public int getHausnummer() {
        return hausnummer;
    }



    public void setHausnummer( int hausnummer ) {
        this.hausnummer = hausnummer;
    }



    public String getStraße() {
        return straße;
    }



    public void setStraße( String straße ) {
        this.straße = straße;
    }



    public static String getGET() {
        return GET;
    }



    public long getId() {
        return id;
    }



    public void setId( long locationId ) {
        this.id = locationId;
    }



    public String getName() {
        return name;
    }



    public void setName( String name ) {
        this.name = name;
    }



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }



    public String getStadt() {
        return stadt;
    }



    public void setStadt( String stadt ) {
        this.stadt = stadt;
    }



    public long getTelefon() {
        return telefon;
    }



    public void setTelefon( long telefon ) {
        this.telefon = telefon;
    }


}
