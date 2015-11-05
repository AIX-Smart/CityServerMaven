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
    public Post[] getFirstPostOfCity(){
        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GET, Post.class );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Post> postList = query.getResultList();
        return postList.toArray(new Post[ postList.size()]);

    }

    public Post getExamplePost(){


        Post post = new Post();
        post.setContent("BeispielPost");
        Calendar today = Calendar.getInstance();
        post.setDate(today);
        post.setLikes( 2 );
        post.setLocationId(1);
        post.setId(1);
        post.setUserId( 1 );
        return post;
//
//        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GET, Post.class );
//        Post post = query.getSingleResult();
//        return post;
    }

}
