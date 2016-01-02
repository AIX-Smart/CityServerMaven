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
import java.util.ArrayList;
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

    @EJB
    private LocationController locationController;


    public Event[] allOwnPosts(int userId) {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETUSER, EventEntity.class);
        query.setParameter("appuserId", userId);

        List<EventEntity> eventEntityList = query.getResultList();
        return Utils.convertToDataEventArray(eventEntityList);
    }


    public Comment[] getFirstComments(int eventId, int userId, int comNum) {

        return commentController.getFirstComments(eventId, userId, comNum);

    }


    public Comment[] getNextComments(int eventId, int userId, int comNum, int lastCommentId) {

        return commentController.getNextComments(eventId, userId, comNum, lastCommentId);

    }


    public Event[] getFirstPosts(int cityId, int userId, int postNum) {
        return getNextPosts(cityId, userId, postNum, Integer.MAX_VALUE);
    }


    public Event[] getNextPosts(int cityId, int userId, int postNum, int lastPostId) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETCITY, EventEntity.class);
        query.setParameter("cityId", cityId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);


        AppUserEntity user = userController.getUser(userId);
        List<EventEntity> eventEntityList = query.getResultList();

        return Utils.convertToDataEventArray(eventEntityList, user);
    }


    public Event[] getAllPost() {
        return Utils.convertToDataEventArray(getAllPostEntity());
    }


    public List<EventEntity> getAllPostEntity() {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETALL, EventEntity.class);
        List<EventEntity> eventList = query.getResultList();

        return eventList;

    }


    public EventEntity getEventEntityById(int id) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GET, EventEntity.class);
        query.setParameter("id", id);

        EventEntity eventEntity = query.getSingleResult();

        return eventEntity;
    }


    private Event getEventById(int id) {
        EventEntity eventEntity = getEventEntityById(id);
        return new Event(eventEntity);
    }


    public Event deletePost(int id, int userId) {

        EventEntity eventEntity = getEventEntityById(id);

        if (userId == (eventEntity.getAppUserEntity().getId())) {
            eventEntity.setDeleted(true);
        }
        entityManager.merge(eventEntity);

        return getEventById(id);

    }


    public boolean likePost(int id, int userId, boolean isLiked) {

        EventEntity eventEntity = getEventEntityById(id);
        AppUserEntity user = userController.getUser(userId);

        List<EventEntity> likeEventList = user.getLikedEventEntities();
        int likeCount = eventEntity.getLikes();

        if (isLiked && !likeEventList.contains(eventEntity)) {
            likeEventList.add(eventEntity);
            likeCount++;
        } else if (!isLiked && likeEventList.contains(eventEntity)) {
            likeEventList.remove(eventEntity);
            likeCount--;
        }
        eventEntity.setLikes(likeCount);
        entityManager.merge(eventEntity);
        entityManager.merge(user);

        return isLiked;
    }

    public EventEntity createEvent(int id, int userId, String text ) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setContent( text );
        eventEntity.setDate( Calendar.getInstance() );
        eventEntity.setAppUserEntity( userController.getUser( userId ) );
        eventEntity.setLocationEntity( locationController.getLocationEntityById(id)) ;
        eventEntity.setCommentEntities( new ArrayList<CommentEntity>());

        entityManager.persist( eventEntity );

        return eventEntity;

    }


    public Comment createComment(int id, int userId, String text) {
        return commentController.createComment(id, userId, text);
    }

    public Event[] getNextEventsWithTagId(int tagId, int cityId, int userId, int postNum, int lastPostId) {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETWITHTAG, EventEntity.class);
        query.setParameter("tagId", tagId);
        query.setParameter("cityId", cityId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);


        AppUserEntity user = userController.getUser(userId);
        List<EventEntity> eventEntityList = query.getResultList();

        return Utils.convertToDataEventArray(eventEntityList, user);


    }
}
