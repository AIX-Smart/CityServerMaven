package com.server.datatype;

import java.util.List;

/**
 * Created by jp on 15.11.2015.
 */
public class LocationData {

    private Location location;
    private List<Tag> tags;
    private String description;
    private int cityId;
    private String street;
    private String houseNumber;
    private String phoneNumber;
    private String gps;
    private int likeCount;
    private boolean liked;


    public LocationData(com.server.entities.Location location, List<Tag> tagList, boolean liked){
        this.location = new Location(location.getId(), location.getName());
        this.tags = tagList;
        this.description = location.getDescription();
        this.cityId = location.getCityId();
        this.street = location.getStreet();
        this.houseNumber = location.getHouseNumber();
        this.phoneNumber = location.getPhoneNumber();
        this.likeCount = location.getLikes();
        this.liked = liked;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
