package com.server.controller;

import com.server.Utils;
import com.server.entities.AppUser;
import com.server.entities.Comment;
import com.server.entities.Event;

import javax.ejb.EJB;
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
public class EventController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;



    public com.server.datatype.Event[] allOwnPosts( int userId ) {

        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GETUSER, Event.class );
        query.setParameter( "appuserId", userId );

        List<Event> eventList = query.getResultList();
        return Utils.convertToDataEventArray(eventList);
    }



    public void createComment(int id, int userId, String text ) {
        Comment comment = new Comment();
        comment.setContent( text );
        comment.setDate( Calendar.getInstance() );
        comment.setAppuserid( userId );

        comment.setEvent(getEventById(id));

        entityManager.persist( comment );
    }



    public com.server.datatype.Comment[] getNextComments( int id, int userId, int comNum, int lastCommentId ) {

        TypedQuery<Comment> query = entityManager.createNamedQuery(Comment.GETPOSTCOMMENTS, Comment.class);
        query.setParameter("postId", id);
        query.setParameter("lastCommentId", lastCommentId);
        query.setMaxResults(comNum);

        if (query.getResultList() == null) {
            throw new NullPointerException();
        }
        List<Comment> commentList = query.getResultList();

        AppUser user = userController.getUser(userId);

        return Utils.convertToDataCommentArray(commentList, user);
    }

    public Event getEventById( int id ){
        TypedQuery<Event> query = entityManager.createQuery(Event.GET, Event.class);
        query.setParameter("id", id);

        Event event = query.getSingleResult();

        return event;
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

    public com.server.datatype.Comment[] getFirstComments(int id, int userId, int comNum) {
        return getNextComments(id, userId, comNum, Integer.MAX_VALUE);
    }
}
