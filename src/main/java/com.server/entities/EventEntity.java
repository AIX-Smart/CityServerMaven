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

@NamedQueries( {
        @NamedQuery( name = EventEntity.GETALL, query = "SELECT d FROM Event d ORDER BY d.id DESC" ),
        @NamedQuery( name = EventEntity.GETCITY, query =
                "SELECT d " +
                "FROM Event d JOIN d.location lc " +
                "WHERE d.id < :lastId AND lc.cityId = :cityId " +
                "ORDER BY d.id DESC" ),
        @NamedQuery( name = EventEntity.GET, query = "SELECT d FROM Event d WHERE d.id = :id" ),
        @NamedQuery( name = EventEntity.GETUSER, query = "SELECT d FROM Event d WHERE d.appuserid = :appuserid ORDER BY d.id DESC" ),
        @NamedQuery( name = EventEntity.GETLOCATION, query = "SELECT d  FROM Event d join d.location l WHERE l.id = :locatioid ORDER BY d.id DESC" )
} )
@Entity
public class EventEntity {

    public static final String GETALL       = "Event.getAll";
    public static final String GETCITY      = "Event.getCity";
    public static final String GET          = "Event.get";
    public static final String GETUSER      = "Event.getUser";
    public static final String GETLOCATION  = "Event.getLocation";
    public static final String GETEVENTLIKE = "Event.getEventLike";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @Temporal( TemporalType.DATE )
    private Calendar date;

    @ManyToOne( optional = false )
    private AppUserEntity appUserEntity;

    @OneToMany
    private List<CommentEntity> commentEntities;

    @ManyToOne( optional = false )
    private LocationEntity locationEntity;

    private String content;

    private int likes;



    public int getId() {
        return id;
    }



    public void setId( int postId ) {
        this.id = postId;
    }



    public String getContent() {
        return content;
    }



    public void setContent( String content ) {
        this.content = content;
    }



    public Calendar getDate() {
        return date;
    }



    public void setDate( Calendar date ) {
        this.date = date;
    }



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }



    public LocationEntity getLocationEntity() {
        return locationEntity;
    }



    public void setLocationEntity( LocationEntity locationEntity ) {
        this.locationEntity = locationEntity;
    }



    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }



    public void setCommentEntities( List<CommentEntity> commentEntities ) {
        this.commentEntities = commentEntities;
    }



    public AppUserEntity getAppUserEntity() {
        return appUserEntity;
    }



    public void setAppUserEntity( AppUserEntity appUserEntity ) {
        this.appUserEntity = appUserEntity;
    }

}