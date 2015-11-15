package com.server.controller;

import com.server.datatype.Event;
import com.server.entities.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

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
        post.setAppuserid( userId );

        entityManager.persist( post );


    }



    public Event[] getFirstPosts(int cityId, int userId, int postNum ) {

        TypedQuery<Post> query = entityManager.createNamedQuery(Post.GETALL, Post.class);
        query.setMaxResults(postNum);
        List<Post> postList = query.getResultList();
        if (postList == null) {
            throw new NullPointerException();
        }


        Event[] events = new Event [postList.size()];
        for (int i = 0; i < postList.size(); i++){
            events[i]= new Event(postList.get(i));
        }

        return events;

        /*return getNextPosts2(cityId, userId, postNum, Integer.MAX_VALUE);*/
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

    public Event[] getNextPosts2( int cityId, int userId, int postNum, int lastPostId ) {
        TypedQuery<Post> query = entityManager.createNamedQuery(Post.GETCITY, Post.class);
        query.setParameter("cityId", cityId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);

        List<Post> postList = query.getResultList();
        if(postList == null){
            throw new NullPointerException(  );
        }

        Event[] events = new Event [postList.size()];
        for (int i = 0; i < postList.size(); i++){
            events[i]= new Event(postList.get(i));
        }


        return events;
    }
}
