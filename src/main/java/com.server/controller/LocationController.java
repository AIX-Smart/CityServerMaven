package com.server.controller;

import com.server.entities.Location;
import com.server.entities.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class LocationController {

    @PersistenceContext
    private EntityManager entityManager;



    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Post aus der Tabelle wieder
    public Location[] getAllLocation() {
        TypedQuery<Location> query = entityManager.createNamedQuery( Location.GET, Location.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException(  );
        }
        List<Location> locationList = query.getResultList();
        return locationList.toArray(new Location[ locationList.size()]);

    }



    public void createPost( int id, int userId, String text ) {
    }



    public Post[] getNextPosts( int id, int userId, int postNum ) {
        return new Post[ 0 ];
    }



    public Post[] getNextPosts( int id, int userId, int postNum, int lastPostId ) {
        return new Post[ 0 ];
    }



}
