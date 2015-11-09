package com.server.controller;

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
public class CityController {

    @PersistenceContext
    private EntityManager entityManager;

    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Post aus der Tabelle wieder
    public Post[] getAllPost(){
        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GETALL, Post.class );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Post> postList = query.getResultList();
        return postList.toArray(new Post[ postList.size()]);

    }



    public void createPost(int cityId, int userId, String text ) {

        //zurzeit nur Aachen deswegen ist cityId egal

        Post post = new Post();
        post.setContent( text );
        post.setDate( Calendar.getInstance() );
        post.setUserId( userId );

        entityManager.persist( post );


    }



    public Post[] getFirstPosts( int cityId, int userId, int postNum ) {

        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GETALL, Post.class );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Post> postList = query.getResultList();

        List<Post> returnList = postList.subList( 0, postNum );

        return returnList.toArray(new Post[ returnList.size()]);

    }



    public Post[] getNextPosts( int cityid, int userId, int postNum, int lastPostId ) {
        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GETALL, Post.class );
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
