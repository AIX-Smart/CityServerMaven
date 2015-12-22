package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by Thomas on 15.11.2015.
 */
@NamedQueries( {
        @NamedQuery( name = CityEntity.GETCITYBYID, query = "SELECT c FROM CityEntity c WHERE c.id = :cityId" ),
        @NamedQuery( name = CityEntity.GETCITYBYNAME, query = "SELECT c FROM CityEntity c WHERE c.name = :cityName" ),
        @NamedQuery( name = CityEntity.GETALL, query = "SELECT c FROM CityEntity c" ),
} )

@Entity
public class CityEntity {

    public static final String GETCITYBYID = "getCityById";
    public static final String GETCITYBYNAME = "getCityByName";
    public static final String GETALL      = "getAll";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int    id;
    private String name;



    public int getId() {
        return id;
    }



    public void setId( int id ) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName( String name ) {
        this.name = name;
    }
}
