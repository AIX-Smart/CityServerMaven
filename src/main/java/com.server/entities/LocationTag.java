package com.server.entities;

import javax.persistence.*;

/**
 * Created by Thomas on 15.11.2015.
 */
@NamedQueries( {
        @NamedQuery( name = LocationTag.GET_LOCATION_TAG, query = "SELECT lt FROM LocationTag lt WHERE lt.id = :id" )

} )

@Entity
public class LocationTag {

    public static final String GET_LOCATION_TAG = "getLocationTag";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private int locationId;
    private int tagId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
