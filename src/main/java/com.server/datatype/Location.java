package com.server.datatype;

import com.server.entities.LocationEntity;
import com.server.entities.TagEntity;

import java.util.List;

/**
 * Created by jp on 13.11.2015.
 */
public class Location {

    private int id;
    private String name;
    private int[] tagIds;
    private String description;
    private int cityId;
    private String street;
    private String houseNumber;
    private String phoneNumber;
    private String gps;
    private String openHours;
    private String imagePath;
    private String postalCode;


    public Location(LocationEntity locationEntity) {
        this.id = locationEntity.getId();
        this.name = locationEntity.getName();
        this.tagIds = new int[locationEntity.getTags().size()];
        this.description = locationEntity.getDescription();
        this.cityId = locationEntity.getCityEntity().getId();
        this.street = locationEntity.getStreet();
        this.houseNumber = locationEntity.getHouseNumber();
        this.phoneNumber = locationEntity.getPhoneNumber();
        this.openHours = locationEntity.getOpenHours();
        this.postalCode = locationEntity.getPostalCode();

        setTagArray(locationEntity.getTags());

    }

    private void setTagArray(List<TagEntity> tags) {

        for (int i = 0; i < tags.size(); i++) {

            this.tagIds[i] = tags.get(i).getId();

        }

    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
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
        this.cityId = cityId;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
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


    public String getGps() {
        return gps;
    }


    public void setGps(String gps) {
        this.gps = gps;
    }


    public int[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(int[] tagIds) {
        this.tagIds = tagIds;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}