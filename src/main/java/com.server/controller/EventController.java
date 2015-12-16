package com.server.controller;

import com.server.Utils;
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



    public com.server.datatype.Event[] allOwnPosts( int userId ) {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GETUSER, EventEntity.class );
        query.setParameter( "appuserId", userId );

        List<EventEntity> eventEntityList = query.getResultList();
        return Utils.convertToDataEventArray( eventEntityList );
    }



    public void createComment( int eventId, int userId, String text ) {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent( text );
        commentEntity.setDate( Calendar.getInstance() );
        commentEntity.setAppUserEntity( userController.getUser( userId ) );
        commentEntity.setEventEntity( getEventById( eventId ) );

        entityManager.persist( commentEntity );
    }



    public com.server.datatype.Comment[] getNextComments( int eventId, int userId, int comNum, int lastCommentId ) {

        TypedQuery<CommentEntity> query = entityManager
                .createNamedQuery( CommentEntity.GETPOSTCOMMENTS, CommentEntity.class );
        query.setParameter( "eventId", eventId );
        query.setParameter( "lastId", lastCommentId );
        query.setMaxResults( comNum );

        if ( query.getResultList() == null ) {
            throw new NullPointerException();
        }
        List<CommentEntity> commentEntityList = query.getResultList();

        AppUserEntity user = userController.getUser( userId );

        return Utils.convertToDataCommentArray( commentEntityList, user );
    }



    public EventEntity getEventById( int id ) {
        TypedQuery<EventEntity> query = entityManager.createQuery( EventEntity.GET, EventEntity.class );
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


    public void likePost( int id, int userId, boolean isLiked ) {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GET, EventEntity.class );
        query.setParameter( "id", id );
        EventEntity eventEntity = query.getSingleResult();

        TypedQuery<AppUserEntity> queryUser = entityManager.createNamedQuery( AppUserEntity.GET, AppUserEntity.class );
        query.setParameter( "id", userId );
        AppUserEntity user = queryUser.getSingleResult();
        List<EventEntity> likeEventList = user.getLikedEventEntities();
        int likeCount = eventEntity.getLikes();

        if (isLiked) {
            if (!likeEventList.contains(eventEntity)) {
                likeEventList.add(eventEntity);
                likeCount++;
            }
        }
        else {
            if (likeEventList.contains(eventEntity)) {
                likeEventList.remove(eventEntity);
                likeCount--;
            }
        }
        eventEntity.setLikes( likeCount );
        entityManager.persist( eventEntity );
        entityManager.persist( user );

    }



    public com.server.datatype.Comment[] getFirstComments( int id, int userId, int comNum ) {
        return getNextComments( id, userId, comNum, Integer.MAX_VALUE );
    }



    public com.server.datatype.Event[] getAllPost() {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GETALL, EventEntity.class );

        List<EventEntity> eventEntityList = query.getResultList();
        return Utils.convertToDataEventArray( eventEntityList );

    }
}
