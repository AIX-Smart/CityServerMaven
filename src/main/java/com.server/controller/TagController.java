package com.server.controller;

import com.server.entities.EventEntity;
import com.server.entities.LocationEntity;

import javax.ejb.Stateless;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class TagController {

    public LocationEntity[] getNextLocations( int id, int userId, int locationNum ) {
        return new LocationEntity[ 0 ];
    }



    public LocationEntity[] getNextLocations( int id, int userId, int postNum, int lastPostId ) {
        return new LocationEntity[ 0 ];
    }



    public EventEntity[] getNextPosts( int tagid, int userId, int postNum, int lastPostId ) {
        return new EventEntity[0];
    }



    public EventEntity[] getNextPosts( int id, int userId, int postNum ) {
        return new EventEntity[ 0 ];
    }
}
