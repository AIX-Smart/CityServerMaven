package com.server.datatype;

import com.server.entities.TagEntity;

/**
 * Created by jp on 15.11.2015.
 */
public class Tag {

    private int    id;
    private String name;



    public Tag() {

    }



    public Tag( TagEntity tagEntity ) {
        this.id = tagEntity.getId();
        this.name = tagEntity.getName();
    }



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
