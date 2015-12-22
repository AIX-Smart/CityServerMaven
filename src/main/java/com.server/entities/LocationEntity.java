package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */


@NamedQueries( {
        @NamedQuery( name = LocationEntity.GET, query = "SELECT l FROM LocationEntity l WHERE l.id = :id  " ),
        @NamedQuery( name = LocationEntity.GETBYNAME, query = "SELECT l FROM LocationEntity l WHERE l.name = :name" ),
        @NamedQuery( name = LocationEntity.GETCITYLOCATIONS, query = "SELECT l FROM LocationEntity l WHERE l.cityEntity.id = :cityId" ),
        @NamedQuery( name = LocationEntity.GETCITYLOCATIONSWITHTAG, query = "SELECT l FROM LocationEntity l join l.TagEntity t WHERE l.cityEntity.id = :cityId AND t.id = :tagId" ),
        @NamedQuery( name = LocationEntity.GETALL, query = "SELECT l FROM LocationEntity l " )

} )
@Entity
public class LocationEntity {


    public static final String GET              = "Location.get";
    public static final String GETBYNAME        = "Locaiton.getByName";
    public static final String GETCITYLOCATIONS = "Location.getCityLocations";
    public static final String GETCITYLOCATIONSWITHTAG = "Location.getCityLocationsWithTag";
    public static final String GETALL           = "Location.getAll";



    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String name;

    @ManyToOne( optional = false )
    private CityEntity cityEntity;

    @OneToMany
    private List<TagEntity> tags;

    private String street;

    private String phoneNumber;

    private int likes;

    private String GPS;

    private String description;

    private String houseNumber;

    private boolean deleted;



    public static String getGETALL() {
        return GETALL;
    }



    public static String getGET() {
        return GET;
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



    public CityEntity getCityEntity() {
        return cityEntity;
    }



    public void setCityEntity( CityEntity cityEntity ) {
        this.cityEntity = cityEntity;
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



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }



    public String getGPS() {
        return GPS;
    }



    public void setGPS( String GPS ) {
        this.GPS = GPS;
    }



    public String getDescription() {
        return description;
    }



    public boolean isDeleted() {
        return deleted;
    }



    public void setDeleted( boolean deleted ) {
        this.deleted = deleted;
    }



    public void setDescription( String description ) {
        this.description = description;

    }



    public List<TagEntity> getTags() {
        return tags;
    }



    public void setTags( List<TagEntity> tags ) {
        this.tags = tags;
    }
}
