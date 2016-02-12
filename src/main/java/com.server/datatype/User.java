package com.server.datatype;

import com.server.controller.Utils;
import com.server.entities.AppUserEntity;
import com.server.entities.LocationEntity;
import com.server.entities.LocationOwnerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jp on 15.11.2015.
 */
public class User {

    private int            id;
    private List<Integer> ownLocationIds;



    public User( AppUserEntity user ) {
        this.id = user.getId();

        List<Integer> locationIds = new ArrayList<Integer>();

        if (user.getOwnerEntityList()!= null) {
            for (LocationOwnerEntity locationOwnerEntity : user.getOwnerEntityList()) {
                for (LocationEntity locationEntity : locationOwnerEntity.getLocationEntities()){
                    locationIds.add( locationEntity.getId());
                }
            }
        }

        this.ownLocationIds = locationIds;

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
