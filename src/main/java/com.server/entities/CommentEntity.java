package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Created by jp on 02.11.15.
 */


@NamedQueries( {
        @NamedQuery( name = CommentEntity.GETALL, query = "SELECT c FROM Comment c ORDER BY c.id DESC" ),
        @NamedQuery( name = CommentEntity.GET, query = "SELECT c FROM Comment c WHERE c.id = :id ORDER BY c.id DESC" ),
        @NamedQuery( name = CommentEntity.GETOWN, query = "SELECT c FROM Comment c WHERE c.appuserid = :userId ORDER BY c.id DESC" ),
        @NamedQuery( name = CommentEntity.GETPOSTCOMMENTS, query =
                "SELECT c " +
                "FROM Comment c JOIN c.event ev " +
                "WHERE c.id < :lastId AND ev.id = :eventId " +
                "ORDER BY c.id DESC" ),

} )
@Entity
public class CommentEntity {

    public static final String GET             = "Comment.get";
    public static final String GETALL          = "Comment.getAll";
    public static final String GETOWN          = "Comment.getOwn";
    public static final String GETPOSTCOMMENTS = "Comment.getPostComments";
    public static final String GETCOMMENTLIKE  = "Comment.getCommentLike";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String content;

    @ManyToOne
    private EventEntity eventEntity;

    @Temporal( TemporalType.DATE )
    private Calendar date;

    @ManyToOne( optional = false )
    private AppUserEntity appUserEntity;


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
}
