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


    public Event[] getFirstPostsOfCity(int cityId, int userId, int postNum) {
        return getNextPostsOfCity(cityId, userId, postNum, Integer.MAX_VALUE);
    }

    public List<EventEntity> getFirstPostsOfCity(int cityId, int postNum) {
        return getNextPostsOfCity(cityId, postNum, Integer.MAX_VALUE);
    }


    public Event[] getNextPostsOfCity(int cityId, int userId, int postNum, int lastPostId) {
        List<EventEntity> eventEntityList = getNextPostsOfCity(cityId, postNum, lastPostId);

        AppUserEntity user = userController.getUserEntity(userId);

        return Utils.convertToDataEventArray(eventEntityList, user);
    }

    public List<EventEntity> getNextPostsOfCity(int cityId, int postNum, int lastPostId) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETCITY, EventEntity.class);
        query.setParameter("cityId", cityId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);


        List<EventEntity> eventEntityList = query.getResultList();

        return eventEntityList;
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
        Boolean isOwner = eventEntity.getLocationEntity().getLocationOwnerEntity().getAppUserEntityList().contains( userController.getUserEntity( userId ) );

        if (userId == (eventEntity.getAppUserEntity().getId())  ||  isOwner) {
            eventEntity.setDeleted(true);
        }
        entityManager.merge(eventEntity);

        return getEventById(id);

    }


    public boolean likePost(int id, int userId, boolean isLiked) {

        EventEntity eventEntity = getEventEntityById(id);
        AppUserEntity user = userController.getUserEntity(userId);

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

    public EventEntity createEvent(int id, int userId, String text) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setContent(text);
        Calendar calendar = Calendar.getInstance();
        eventEntity.setDate(calendar);
        eventEntity.setAppUserEntity(userController.getUserEntity(userId));
        eventEntity.setLocationEntity(locationController.getLocationEntityById(id));
        eventEntity.setCommentEntities(new ArrayList<CommentEntity>());

        entityManager.persist(eventEntity);

        return eventEntity;

    }


    public Comment createComment(int id, int userId, String text) {
        return commentController.createComment(id, userId, text);
    }

    public Event[] getNextEventsWithTagId(int tagId, int cityId, int userId, int postNum, int lastPostId) {

        List<EventEntity> eventEntityList = getNextEventsWithTagId(tagId, cityId, postNum, lastPostId);

        AppUserEntity user = userController.getUserEntity(userId);

        return Utils.convertToDataEventArray(eventEntityList, user);


    }

    private List<EventEntity> getNextEventsWithTagId(int tagId, int cityId, int postNum, int lastPostId) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETWITHTAG, EventEntity.class);
        query.setParameter("tagId", tagId);
        query.setParameter("cityId", cityId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);

        return query.getResultList();
    }

    public void eventEntityAddComment(int id, CommentEntity commentEntity) {

        EventEntity eventEntity = getEventEntityById(id);
        eventEntity.addCommentEntities(commentEntity);

        entityManager.persist(eventEntity);

    }

    public int getLikeCount(int id) {

        EventEntity eventEntity = getEventEntityById(id);

        return eventEntity.getLikes();
    }

    public boolean isLiked(int id, int userId) {

        EventEntity event = getEventEntityById(id);
        int eventId = event.getId();
        AppUserEntity user = userController.getUserEntity(userId);

        boolean liked = false;

        for (EventEntity eventEntity : user.getLikedEventEntities()){
            if (eventEntity.getId() == eventId){
                liked = true;
            }
        }

        return liked;

    }

    public void deleteComment(CommentEntity commentEntity) {
        EventEntity event = commentEntity.getEventEntity();
        event.removeComment(commentEntity);

        entityManager.merge(event);
    }

    public Event[] getFirstPostsOfLocation(int id, int userId, int postNum) {
        return getNextPostsOfLocation(id, userId, postNum, Integer.MAX_VALUE);

    }

    public Event[] getNextPostsOfLocation(int id, int userId, int postNum, int lastPostId) {

        List<EventEntity> eventEntityList = getNextPostsOfLocation(id, postNum, lastPostId);

        AppUserEntity user = userController.getUserEntity(userId);

        return Utils.convertToDataEventArray(eventEntityList, user);

    }

    public List<EventEntity> getNextPostsOfLocation(int locationId, int postNum, int lastPostId) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETLOCATION, EventEntity.class);
        query.setParameter("locationId", locationId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);


        List<EventEntity> eventEntityList = query.getResultList();

        return eventEntityList;
    }

    public List<EventEntity> getFirstPostsOfLocation(int locationId, int postNum) {
        return getNextPostsOfLocation(locationId, postNum, Integer.MAX_VALUE);
    }

    public List<EventEntity> getFirstPostsOfTag(int tagId, int cityId, int i) {
        return getNextEventsWithTagId(tagId, cityId, i, Integer.MAX_VALUE);
    }

    public boolean isUpToDate(int id, int commentId) {

        boolean isUpToDate = true;

        CommentEntity commentEntity = commentController.getFirstComments(id, 1).get(0);
        if (commentId < commentEntity.getId() ){
            isUpToDate = false;
        }

        return isUpToDate;
    }



    public  List<EventEntity> getNextPostsOfCityByPopularity( int cityId, int postNum ) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETBYPOPULARITY, EventEntity.class);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);
        query.setParameter( "today", cal.getTimeInMillis() );
        query.setParameter("cityId", cityId);
        query.setMaxResults(postNum);


        List<EventEntity> eventEntityList = query.getResultList();

        return eventEntityList;
    }

    public Event[] getNextPostsOfCityByPopularity (int cityId, int userId, int postNum) {
        List<EventEntity> eventEntityList = getNextPostsOfCityByPopularity(cityId, postNum);

        AppUserEntity user = userController.getUserEntity(userId);

        return Utils.convertToDataEventArray(eventEntityList, user);
    }

    public boolean isAuthenticatedPost(int id) {
        EventEntity eventEntity = getEventEntityById(id);
        if (eventEntity.getLocationEntity().getLocationOwnerEntity().getAppUserEntityList().contains(eventEntity.getAppUserEntity())){
            return true;
        }else{
            return false;
        }
    }
}
