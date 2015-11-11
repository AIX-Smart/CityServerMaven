package com.server.controller;

import com.server.entities.AppUser;
import com.server.entities.Location;
import com.server.entities.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
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
        TypedQuery<Location> query = entityManager.createNamedQuery( Location.GETALL, Location.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException(  );
        }
        List<Location> locationList = query.getResultList();
        return locationList.toArray(new Location[ locationList.size()]);

    }



    public void createPost( Location id, AppUser userId, String text ) {
        
        Post post = new Post();
        post.setContent( text );
        post.setDate( Calendar.getInstance() );
        post.setUser( userId );
        post.setLocation( id );

        entityManager.persist( post );


    }



    public Post[] getFirstPosts( int id, int userId, int postNum ) {
        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GETLOCATION, Post.class );
        query.setParameter( "locationId", id );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Post> postList = query.getResultList();

        List<Post> returnList = postList.subList( 0, postNum );

        return returnList.toArray(new Post[ returnList.size()]);
    }



    public Post[] getNextPosts( int id, int userId, int postNum, int lastPostId ) {

        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GETLOCATION, Post.class );
        query.setParameter( "locationId", id );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Post> postList = query.getResultList();

        int i = 0;
        for( ; i < postList.size(); i++){
            if (postList.get( i ).getId() == lastPostId){
                break;
            }
        }
        if (i == postList.size()){
            return new Post[0];
        }

        List<Post> returnList = postList.subList( i, i + postNum );

        return returnList.toArray(new Post[ returnList.size()]);

    }



}
