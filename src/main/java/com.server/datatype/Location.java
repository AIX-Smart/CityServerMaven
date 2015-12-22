package com.server.datatype;

import com.server.entities.LocationEntity;

import java.util.List;

/**
 * Created by jp on 13.11.2015.
 */
public class Location {

    private int       id;
    private String    name;
    private List<Tag> tags;
    private String    description;
    private int       cityId;
    private String    street;
    private String    houseNumber;
    private String    phoneNumber;
    private String    gps;
    private int       likeCount;
    private boolean   liked;







    public Location( LocationEntity locationEntity) {
        this.id = locationEntity.getId();
        this.name = locationEntity.getName();
        this.tags = null;
        this.description = locationEntity.getDescription();
        this.cityId = locationEntity.getCityEntity().getId();
        this.street = locationEntity.getStreet();
        this.houseNumber = locationEntity.getHouseNumber();
        this.phoneNumber = locationEntity.getPhoneNumber();
        this.likeCount = locationEntity.getLikes();
        this.liked = false;
    }



    public int getId() {
        return id;
    }



    public void setId( int id ) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName( String name ) {
        this.name = name;
    }



    public List<Tag> getTags() {
        return tags;
    }



    public void setTags( List<Tag> tags ) {
        this.tags = tags;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription( String description ) {
        this.description = description;
    }



    public int getCityId() {
        return cityId;
    }



    public void setCityId( int cityId ) {
        this.cityId = cityId;
    }



    public String getStreet() {
        return street;
    }



    public void setStreet( String street ) {
        this.street = street;
    }



    public String getHouseNumber() {
        return houseNumber;
    }



    public void setHouseNumber( String houseNumber ) {
        this.houseNumber = houseNumber;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }



    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }



    public String getGps() {
        return gps;
    }



    public void setGps( String gps ) {
        this.gps = gps;
    }



    public int getLikeCount() {
        return likeCount;
    }



    public void setLikeCount( int likeCount ) {
        this.likeCount = likeCount;
    }



    public boolean isLiked() {
        return liked;
    }



    public void setLiked( boolean liked ) {
        this.liked = liked;
    }
}