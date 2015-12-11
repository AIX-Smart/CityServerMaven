package com.server.entities;

import javax.persistence.*;

/**
 * Created by Thomas on 15.11.2015.
 */
@NamedQueries( {
        @NamedQuery( name = City.GETCITY, query = "SELECT c FROM City c WHERE c.id = :cityId" ),
        @NamedQuery( name = City.GETALLCITIES, query ="SELECT c FROM City c")
} )

@Entity
public class City {

    public static final String GETCITY = "getCity";
    public static final String GETALLCITIES ="getAllCities";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
