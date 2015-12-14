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
        @NamedQuery( name = CityEntity.GETCITY, query = "SELECT c FROM City c WHERE c.id = :cityId" ),
        @NamedQuery( name = CityEntity.GETALLCITIES, query = "SELECT c FROM City c" ),
} )

@Entity
public class CityEntity {

    public static final String GETCITY      = "getCity";
    public static final String GETALLCITIES = "getAllCities";

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
