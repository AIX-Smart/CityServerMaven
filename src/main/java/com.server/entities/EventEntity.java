package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */

@NamedQueries({
        @NamedQuery(name = EventEntity.GETALL, query = "SELECT d FROM EventEntity d where d.deleted = false ORDER BY d.id DESC"),
        @NamedQuery(name = EventEntity.GETCITY, query =
                "SELECT d " +
                        "FROM EventEntity d JOIN d.locationEntity lc " +
                        "WHERE d.id < :lastId AND lc.cityEntity.id = :cityId and d.deleted = false " +
                        "ORDER BY d.id DESC"),
        @NamedQuery(name = EventEntity.GET, query = "SELECT d FROM EventEntity d WHERE d.id = :id"),
        @NamedQuery(name = EventEntity.GETUSER, query = "SELECT d FROM EventEntity d WHERE d.appUserEntity.id = :appuserid and d.deleted = false ORDER BY d.id DESC"),
        @NamedQuery(name = EventEntity.GETLOCATION, query =
                "SELECT d  " +
                        "FROM EventEntity d join d.locationEntity l " +
                        "WHERE d.id < :lastId AND l.id = :locationId and d.deleted = false " +
                        // "WHERE d.id < :lastId AND l.id = :locationid " +
                        "ORDER BY d.id DESC"),
        @NamedQuery(name = EventEntity.GETWITHTAG, query =
                "SELECT d  " +
                        "FROM EventEntity d join d.locationEntity l join l.tags lt " +
                        "WHERE d.id < :lastId AND d.deleted = false AND lt.id = :tagId AND l.cityEntity.id = :cityId " +
                        // "WHERE d.id < :lastId AND l.id = :locationid " +
                        "ORDER BY d.id DESC"),
        @NamedQuery(name = EventEntity.GETBYPOPULARITY, query =
                "SELECT d " +
                "FROM EventEntity d JOIN d.locationEntity lc " +
                "WHERE d.date > :today and lc.cityEntity.id = :cityId and d.deleted = false " +
                "ORDER BY d.likes DESC"),

})
@Entity
public class EventEntity {

    public static final String GETALL = "EventEntity.getAll";
    public static final String GETCITY = "EventEntity.getCity";
    public static final String GET = "EventEntity.get";
    public static final String GETUSER = "EventEntity.getUser";
    public static final String GETLOCATION = "EventEntity.getLocation";
    public static final String GETEVENTLIKE = "EventEntity.getEventLike";
    public static final String GETWITHTAG = "EventEntity.getWithTag";
    public static final String GETBYPOPULARITY = "EventEntity.getByPopularity";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @ManyToOne(optional = false)
    private AppUserEntity appUserEntity;

    @OneToMany
    private List<CommentEntity> commentEntities;

    @ManyToOne(optional = false)
    private LocationEntity locationEntity;

    private String content;

    private int likes;

    private boolean deleted;


    private boolean isAuthenticated;


    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }



    public int getId() {
        return id;
    }


    public void setId(int postId) {
        this.id = postId;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public Calendar getDate() {
        return date;
    }


    public void setDate(Calendar date) {
        this.date = date;
    }


    public int getLikes() {
        return likes;
    }


    public void setLikes(int likes) {
        this.likes = likes;
    }


    public LocationEntity getLocationEntity() {
        return locationEntity;
    }


    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }


    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }


    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }


    public void addCommentEntities(CommentEntity commentEntity) {commentEntities.add(commentEntity);}


    public AppUserEntity getAppUserEntity() {
        return appUserEntity;
    }


    public void setAppUserEntity(AppUserEntity appUserEntity) {
        this.appUserEntity = appUserEntity;
    }


    public boolean isDeleted() {
        return deleted;
    }


    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void removeComment(CommentEntity commentEntity) {
        this.commentEntities.remove(commentEntity);
    }
}
