package com.server.controller;

import com.server.entities.Location;
import com.server.entities.Post;

import javax.ejb.Stateless;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class TagController {

    public Location[] getNextLocations( int id, int userId, int locationNum ) {
        return new Location[ 0 ];
    }



    public Location[] getNextLocations( int id, int userId, int postNum, int lastPostId ) {
        return new Location[ 0 ];
    }



    public Post[] getNextPosts( int tagid, int userId, int postNum, int lastPostId ) {
        return new Post[0];
    }



    public Post[] getNextPosts( int id, int userId, int postNum ) {
        return new Post[ 0 ];
    }
}
