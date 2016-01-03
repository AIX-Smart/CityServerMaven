package com.server.entities;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by jp on 02.11.15.
 */


@NamedQueries( {
        @NamedQuery( name = CommentEntity.GETALL, query = "SELECT c FROM CommentEntity c where c.deleted = false ORDER BY c.id DESC" ),
        @NamedQuery( name = CommentEntity.GET, query = "SELECT c FROM CommentEntity c WHERE c.id = :id" ),
        @NamedQuery( name = CommentEntity.GETOWN, query = "SELECT c FROM CommentEntity c WHERE c.appUserEntity.id = :userId and c.deleted = false ORDER BY c.id DESC" ),
        @NamedQuery( name = CommentEntity.GETPOSTCOMMENTS, query =
                "SELECT c " +
                "FROM CommentEntity c JOIN c.eventEntity ev " +
                "WHERE c.id < :lastId AND ev.id = :eventId and c.deleted = false " +
                "ORDER BY c.id DESC" ),

} )
@Entity
public class CommentEntity {

    public static final String GET             = "CommentEntity.get";
    public static final String GETALL          = "CommentEntity.getAll";
    public static final String GETOWN          = "CommentEntity.getOwn";
    public static final String GETPOSTCOMMENTS = "CommentEntity.getPostComments";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String content;

    @ManyToOne
    private EventEntity eventEntity;

    @Temporal( TemporalType.TIMESTAMP )
    private Calendar date;

    @ManyToOne( optional = false )
    private AppUserEntity appUserEntity;


    private boolean deleted;


    private int likes;



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }



    public String getContent() {
        return content;
    }



    public void setContent( String content ) {
        this.content = content;
    }



    public int getId() {
        return id;
    }



    public void setId( int commentId ) {
        this.id = commentId;
    }



    public Calendar getDate() {
        return date;
    }



    public void setDate( Calendar date ) {
        this.date = date;
    }



    public AppUserEntity getAppUserEntity() {
        return appUserEntity;
    }



    public void setAppUserEntity( AppUserEntity appUserEntity ) {
        this.appUserEntity = appUserEntity;
    }



    public EventEntity getEventEntity() {
        return eventEntity;
    }



    public void setEventEntity( EventEntity eventEntity ) {
        this.eventEntity = eventEntity;
    }

    public boolean isDeleted() {
        return deleted;
    }



    public void setDeleted( boolean deleted ) {
        this.deleted = deleted;
    }

}
