package com.aix.server.entities;

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
        @NamedQuery( name = LocationBean.GET, query = "SELECT d FROM Location d " ),
} )
@Entity
public class LocationBean {


    public static final String GET = "Location.get";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long locationId;

    private String name;

    private long cityId;

    private String Straße;

    private int housenr;

    private int plz;

    private long phone;

    private int likes;



    public long getGPS() {
        return GPS;
    }



    public void setGPS( long GPS ) {
        this.GPS = GPS;
    }



    public long getPhone() {
        return phone;
    }



    public void setPhone( long phone ) {
        this.phone = phone;
    }



    public int getPlz() {
        return plz;
    }



    public void setPlz( int plz ) {
        this.plz = plz;
    }



    public int getHousenr() {
        return housenr;
    }



    public void setHousenr( int housenr ) {
        this.housenr = housenr;
    }



    public String getStraße() {
        return Straße;
    }



    public void setStraße( String straße ) {
        Straße = straße;
    }



    public long getCityId() {
        return cityId;
    }



    public void setCityId( long cityId ) {
        this.cityId = cityId;
    }



    public static String getGET() {
        return GET;
    }



    public long getLocationId() {
        return locationId;
    }



    public void setLocationId( long locationId ) {
        this.locationId = locationId;
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



    private long GPS;


}
