package com.server.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by jp on 13.11.2015.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Event {

        private int id;
        private String content;
        private long creationTime;
        private int likeCount;
        private int authorId;
        private boolean liked; //current user has already liked this post

        private Location location;
        private int commentCount;



    public Event(int id, String content, long creationTime, int likeCount, int authorId, boolean liked, Location location, int commentCount) {
        this.id = id;
        this.content = content;
        this.creationTime = creationTime;
        this.likeCount = likeCount;
        this.authorId = authorId;
        this.liked = liked;
        this.location = location;
        this.commentCount = commentCount;
    }

    public Event(com.server.entities.Event event){
        this.id = event.getId();
        this.content = event.getContent();
        this.creationTime = event.getDate().getTimeInMillis();
        this.likeCount = event.getLikes();
        this.authorId = event.getAppuserid();
        this.liked = false;
        this.location = new Location(event.getLocationid());
        this.commentCount = 10;
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

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }




}
