package com.server.controller;

import com.server.datatype.Comment;
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
public class CommentController {


    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;

    @EJB
    private EventController eventController;


    public Comment[] getFirstComments(int id, int userId, int comNum) {
        return getNextComments(id, userId, comNum, Integer.MAX_VALUE);
    }

    public Comment[] getNextComments(int eventId, int userId, int comNum, int lastCommentId) {

        List<CommentEntity> commentEntityList = getNextComments(eventId, comNum, lastCommentId);

        AppUserEntity user = userController.getUser(userId);

        return Utils.convertToDataCommentArray(commentEntityList, user);
    }

    private List<CommentEntity> getNextComments(int eventId, int comNum, int lastCommentId) {
        TypedQuery<CommentEntity> query = entityManager
                .createNamedQuery(CommentEntity.GETPOSTCOMMENTS, CommentEntity.class);
        query.setParameter("eventId", eventId);
        query.setParameter("lastId", lastCommentId);
        query.setMaxResults(comNum);

        if (query.getResultList() == null) {
            throw new NullPointerException();
        }
        return query.getResultList();
    }


    public Comment[] allOwnComments(int userId) {

        TypedQuery<CommentEntity> query = entityManager.createNamedQuery(CommentEntity.GETOWN, CommentEntity.class);
        query.setParameter("appuserId", userId);

        List<CommentEntity> commentEntityList = query.getResultList();

        AppUserEntity user = userController.getUser(userId);
        return Utils.convertToDataCommentArray(commentEntityList, user);
    }

    public Comment[] allComments() {

        TypedQuery<CommentEntity> query = entityManager.createNamedQuery(CommentEntity.GETALL, CommentEntity.class);
        List<CommentEntity> commentList = query.getResultList();
        return Utils.convertToDataCommentArray(commentList);
    }


    public Comment deleteComment(int id, int userId) {

        CommentEntity commentEntity = getCommentById(id);

        if (userId == (commentEntity.getAppUserEntity().getId())) {
            commentEntity.setDeleted(true);
            eventController.deleteComment(commentEntity);
        }

        entityManager.merge(commentEntity);

        return new Comment(getCommentById(id));

    }


    public boolean likeComment(int id, int userId, boolean isLiked) {

        CommentEntity commentEntity = getCommentById(id);
        AppUserEntity user = userController.getUser(userId);

        List<CommentEntity> likeCommentList = user.getLikedCommentEntities();
        int likeCount = commentEntity.getLikes();

        if (isLiked && !likeCommentList.contains(commentEntity)) {
            likeCommentList.add(commentEntity);
            likeCount++;
        } else if (!isLiked && likeCommentList.contains(commentEntity)) {
            likeCommentList.remove(commentEntity);
            likeCount--;
        }
        commentEntity.setLikes(likeCount);
        entityManager.merge(commentEntity);
        entityManager.merge(user);

        return isLiked;
    }

    private CommentEntity getCommentById(int id) {
        TypedQuery<CommentEntity> query = entityManager.createNamedQuery(CommentEntity.GET, CommentEntity.class);
        query.setParameter("id", id);

        CommentEntity commentEntity = query.getSingleResult();

        return commentEntity;
    }


    public Comment createComment(int eventId, int userId, String text) {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(text);
        commentEntity.setDate(Calendar.getInstance());
        commentEntity.setAppUserEntity(userController.getUser(userId));

        EventEntity eventEntity = eventController.getEventEntityById(eventId);
        commentEntity.setEventEntity(eventEntity);

        eventController.eventEntityAddComment(eventId, commentEntity);

        entityManager.persist(commentEntity);
        return new Comment(commentEntity);
    }


    public int getLikeCount(int id) {

        return getCommentById(id).getLikes();

    }

    public boolean isLiked(int id, int userId) {
        CommentEntity comment = getCommentById(id);
        int commentId = comment.getId();

        AppUserEntity user = userController.getUser(userId);

        boolean liked = false;

        for (CommentEntity commentEntity : user.getLikedCommentEntities()){
            if (commentEntity.getId() == commentId){
                liked = true;
            }
        }

        return liked;
    }


    public List<CommentEntity> getFirstComments(int id, int i) {
        return getNextComments(id, i, Integer.MAX_VALUE);

    }
}
