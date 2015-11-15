package com.server.controller;

import com.server.entities.AppUser;
import com.server.entities.Comment;
import com.server.entities.Event;

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
public class PostController {

    @PersistenceContext
    private EntityManager entityManager;



    public Event[] allOwnPosts( int userId ) {

        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GETUSER, Event.class );
        query.setParameter( "appuserId", userId );

        List<Event> eventList = query.getResultList();
        return eventList.toArray(new Event[ eventList.size()]);
    }



    public void createComment(int id, int userId, String text ) {
        Comment comment = new Comment();
        comment.setContent( text );
        comment.setDate( Calendar.getInstance() );
        comment.setAppuserid( userId );
        comment.setEventId(id);

        entityManager.persist( comment );
    }



    public Comment[] getNextComments( int id, int userId, int comNum ) {

        TypedQuery<Comment> query = entityManager.createNamedQuery( Comment.GETPOSTCOMMENTS, Comment.class );
        query.setParameter( "postId", id);

        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Comment> commentList = query.getResultList();

        List<Comment> returnList = commentList.subList( 0, comNum );

        return returnList.toArray(new Comment[ returnList.size()]);


    }



    public Comment[] getNextComments( int id, int userId, int comNum, int lastCommentId ) {

        TypedQuery<Comment> query = entityManager.createNamedQuery( Comment.GETPOSTCOMMENTS, Comment.class );
        query.setParameter( "postId", id);

        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Comment> commentList = query.getResultList();

        int i = 0;
        for( ; i < commentList.size(); i++){
            if (commentList.get( i ).getId() == lastCommentId){
                break;
            }
        }
        if (i == commentList.size()){
            return new Comment[0];
        }

        List<Comment> returnList = commentList.subList( i, i + comNum );
        return returnList.toArray(new Comment[ returnList.size()]);


    }



    public void deletePost( int id, int userId ) {

        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GET, Event.class);
        query.setParameter( "id", id );
        Event event = query.getSingleResult();

        TypedQuery<AppUser> queryUser = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        query.setParameter( "id", id );
        AppUser user = queryUser.getSingleResult();

        if (user.equals( event.getAppuserid() )){
            entityManager.remove(event);
        }


    }



    public void likePost( int id, int userId ) {

        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GET, Event.class);
        query.setParameter( "id", id );
        Event event = query.getSingleResult();

        TypedQuery<AppUser> queryUser = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        query.setParameter( "id", userId );
        AppUser user = queryUser.getSingleResult();


        int likes = event.getLikes() + 1;
        event.setLikes( likes );

        entityManager.persist(event);


    }
}
