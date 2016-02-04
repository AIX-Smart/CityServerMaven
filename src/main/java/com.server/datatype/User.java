package com.server.datatype;

import com.server.entities.AppUserEntity;

import java.util.List;

/**
 * Created by jp on 15.11.2015.
 */
public class User {

    private int            id;
    private List<Location> ownLocations;



    public User( AppUserEntity user ) {
        this.id = user.getId();
    }



    public User( int id ) {
        this.id = id;
    }



    public int getId() {
        return id;
    }



    public void setId( int id ) {
        this.id = id;
    }



    public List<Location> getOwnLocations() {
        return ownLocations;
    }



    public void setOwnLocations( List<Location> ownLocations ) {
        this.ownLocations = ownLocations;
    }
}
