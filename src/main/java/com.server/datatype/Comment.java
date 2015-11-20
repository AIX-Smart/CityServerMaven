package com.server.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.TypedQuery;

/**
 * Created by jp on 15.11.2015.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Comment{


    private int id;
    private String content;
    private long creationTime;
    private int likeCount;
    private int authorId;
    private boolean liked;

    private int eventId;


    public Comment (com.server.entities.Comment comment, boolean liked){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.creationTime = comment.getDate().getTimeInMillis();
        this.likeCount =  comment.getLikes();
        this.authorId = comment.getAppuserid();
        this.liked = liked;
        this.eventId = comment.getEvent().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
