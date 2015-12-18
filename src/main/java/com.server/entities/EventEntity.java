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
        @NamedQuery( name = EventEntity.GETALL, query = "SELECT d FROM EventEntity d ORDER BY d.id DESC" ),
        @NamedQuery( name = EventEntity.GETCITY, query =
                "SELECT d " +
                "FROM EventEntity d JOIN d.locationEntity lc " +
                "WHERE d.id < :lastId AND lc.cityEntity.id = :cityId " +
                "ORDER BY d.id DESC" ),
        @NamedQuery( name = EventEntity.GET, query = "SELECT d FROM EventEntity d WHERE d.id = :id ORDER BY d.id DESC" ),
        @NamedQuery( name = EventEntity.GETUSER, query = "SELECT d FROM EventEntity d WHERE d.appUserEntity.id = :appuserid ORDER BY d.id DESC" ),
        @NamedQuery( name = EventEntity.GETLOCATION, query =
                "SELECT d  " +
                "FROM EventEntity d join d.locationEntity l " +
                "WHERE d.id < :lastId AND l.id = :locationId " +
                       // "WHERE d.id < :lastId AND l.id = :locationid " +
                "ORDER BY d.id DESC" )
} )
@Entity
public class EventEntity {

    public static final String GETALL       = "EventEntity.getAll";
    public static final String GETCITY      = "EventEntity.getCity";
    public static final String GET          = "EventEntity.get";
    public static final String GETUSER      = "EventEntity.getUser";
    public static final String GETLOCATION  = "EventEntity.getLocation";
    public static final String GETEVENTLIKE = "EventEntity.getEventLike";


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
