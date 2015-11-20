package com.server.entities;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by jp on 02.11.15.
 */


@NamedQueries( {
        @NamedQuery( name = Comment.GETALL, query = "SELECT c FROM Comment c ORDER BY c.id DESC" ),
        @NamedQuery( name = Comment.GET, query = "SELECT c FROM Comment c WHERE c.id = :id ORDER BY c.id DESC" ),
        @NamedQuery( name = Comment.GETOWN, query = "SELECT c FROM Comment c WHERE c.appuserid = :userId ORDER BY c.id DESC" ),
        @NamedQuery( name = Comment.GETPOSTCOMMENTS, query =
                "SELECT c " +
                        "FROM Comment c JOIN c.event ev " +
                        "WHERE c.id < :lastId AND ev.id = :eventId " +
                        "ORDER BY c.id DESC"),

} )
@Entity
public class Comment {

    public static final String GET             = "Comment.get";
    public static final String GETALL          = "Comment.getAll";
    public static final String GETOWN          = "Comment.getOwn";
    public static final String GETPOSTCOMMENTS = "Comment.getPostComments";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String content;

    @ManyToOne
    private Event event;

    @Temporal( TemporalType.DATE )
    private Calendar date;


    public int getAppuserid() {
        return appuserid;
    }

    public void setAppuserid(int appuserid) {
        this.appuserid = appuserid;
    }

    private int appuserid;

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


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
