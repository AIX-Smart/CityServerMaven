package com.server.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.server.entities.CommentEntity;

/**
 * Created by jp on 15.11.2015.
 */
@JsonTypeInfo( use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class" )
public class Comment {


    private int     id;
    private String  content;
    private long    creationTime;
    private int     likeCount;
    private int     authorId;
    private boolean liked;
    private boolean isAuthenticated;

    private int eventId;



    public Comment( CommentEntity commentEntity, boolean liked ) {
        this.id = commentEntity.getId();
        this.content = commentEntity.getContent();
        this.creationTime = commentEntity.getDate().getTimeInMillis();
        this.likeCount = commentEntity.getLikes();
        this.authorId = commentEntity.getAppUserEntity().getId();
        this.liked = liked;
        this.eventId = commentEntity.getEventEntity().getId();
        this.isAuthenticated = commentEntity.isAuthenticated();
    }

    public Comment( CommentEntity commentEntity ) {
        this.id = commentEntity.getId();
        this.content = commentEntity.getContent();
        this.creationTime = commentEntity.getDate().getTimeInMillis();
        this.likeCount = commentEntity.getLikes();
        this.authorId = commentEntity.getAppUserEntity().getId();
        this.liked = false;
        this.eventId = commentEntity.getEventEntity().getId();
        this.isAuthenticated = commentEntity.isAuthenticated();
    }


    public int getId() {
        return id;
    }



    public void setId( int id ) {
        this.id = id;
    }



    public String getContent() {
        return content;
    }



    public void setContent( String content ) {
        this.content = content;
    }



    public long getCreationTime() {
        return creationTime;
    }



    public void setCreationTime( long creationTime ) {
        this.creationTime = creationTime;
    }



    public int getLikeCount() {
        return likeCount;
    }



    public void setLikeCount( int likeCount ) {
        this.likeCount = likeCount;
    }



    public int getAuthorId() {
        return authorId;
    }



    public void setAuthorId( int authorId ) {
        this.authorId = authorId;
    }



    public boolean isLiked() {
        return liked;
    }



    public void setLiked( boolean liked ) {
        this.liked = liked;
    }



    public int getEventId() {
        return eventId;
    }



    public void setEventId( int eventId ) {
        this.eventId = eventId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
