package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by jp on 05.11.15.
 */
@NamedQueries( {
        @NamedQuery( name = TagEntity.GET, query = "SELECT t FROM TagEntity t WHERE t.id = :id" ),
        @NamedQuery( name = TagEntity.GETALL, query = "SELECT t FROM TagEntity t" )
} )
@Entity
public class TagEntity {

    public static final String GET      = "Tag.get";
    public static final String GETALL   = "Tag.getAll";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;


    private String name;



    public String getName() {
        return name;
    }


    public void setName( String name ) {
        this.name = name;
    }


    public int getId() {
        return id;
    }


    public void setId( int id ) {
        this.id = id;
    }


}
