package com.server.controller;

import com.server.datatype.Comment;
import com.server.datatype.Event;
import com.server.entities.AppUserEntity;
import com.server.entities.CommentEntity;
import com.server.entities.EventEntity;

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

    @EJB
    private CommentController commentController;



    public Event[] allOwnPosts( int userId ) {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GETUSER, EventEntity.class );
        query.setParameter( "appuserId", userId );

        List<EventEntity> eventEntityList = query.getResultList();
        return Utils.convertToDataEventArray( eventEntityList );
    }



    public Comment[] getFirstComments( int eventId, int userId, int comNum ) {

        return commentController.getFirstComments( eventId, userId, comNum );

    }



    public Comment[] getNextComments( int eventId, int userId, int comNum, int lastCommentId ) {

        return commentController.getNextComments( eventId, userId, comNum, lastCommentId );

    }



    public Event[] getFirstPosts( int cityId, int userId, int postNum ) {
        return getNextPosts( cityId, userId, postNum, Integer.MAX_VALUE );
    }



    public Event[] getNextPosts( int cityId, int userId, int postNum, int lastPostId ) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GETCITY, EventEntity.class );
        query.setParameter( "cityId", cityId );
        query.setParameter( "lastId", lastPostId );
        query.setMaxResults( postNum );


        AppUserEntity user = userController.getUser( userId );
        List<EventEntity> eventEntityList = query.getResultList();

        return Utils.convertToDataEventArray( eventEntityList, user );
    }



    public Event[] getAllPost() {
        return Utils.convertToDataEventArray( getAllPostEntity() );
    }



    private List<EventEntity> getAllPostEntity() {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GETALL, EventEntity.class );
        List <EventEntity> eventList = query.getResultList();

        return eventList;

    }






    public EventEntity getEventById( int id ) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GET, EventEntity.class );
        query.setParameter( "id", id );

        EventEntity eventEntity = query.getSingleResult();

        return eventEntity;
    }



    public void deletePost( int id, int userId ) {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GET, EventEntity.class );
        query.setParameter( "id", id );
        EventEntity eventEntity = query.getSingleResult();

        TypedQuery<AppUserEntity> queryUser = entityManager.createNamedQuery( AppUserEntity.GET, AppUserEntity.class );
        query.setParameter( "id", id );
        AppUserEntity user = queryUser.getSingleResult();

        if ( user.equals( eventEntity.getAppUserEntity().getId() ) ) {
            entityManager.remove( eventEntity );
        }


    }



    public boolean likePost( int id, int userId, boolean isLiked ) {

        EventEntity eventEntity = getEventById( id );
        AppUserEntity user = userController.getUser( userId );

        List<EventEntity> likeEventList = user.getLikedEventEntities();
        int likeCount = eventEntity.getLikes();

        if ( isLiked && !likeEventList.contains( eventEntity ) ) {
            likeEventList.add( eventEntity );
            likeCount++;
        } else if ( !isLiked && likeEventList.contains( eventEntity ) ) {
            likeEventList.remove( eventEntity );
            likeCount--;
        }
        eventEntity.setLikes( likeCount );
        entityManager.merge( eventEntity );
        entityManager.merge( user );

        return isLiked;
    }


}
