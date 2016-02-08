package com.server.datatype;

import com.server.entities.AppUserEntity;
import com.server.entities.LocationOwnerEntity;

import java.util.List;

/**
 * Created by jp on 15.11.2015.
 */
public class User {

    private int            id;
    private List<Integer> ownLocationIds;



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


    public List<Integer> getOwnLocationIds() {
        return ownLocationIds;
    }



    public void setOwnLocationIds(List<Integer> ownLocationIds) {
        this.ownLocationIds = ownLocationIds;
    }
}
