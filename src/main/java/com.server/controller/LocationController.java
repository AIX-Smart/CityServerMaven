package com.server.controller;

import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.entities.EventEntity;
import com.server.entities.LocationEntity;
import com.server.entities.TagEntity;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class LocationController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;

    @EJB
    private CityController cityController;

    @EJB
    private EventController eventController;

    @EJB
    private TagController tagController;

    private Logger logger = Logger.getLogger(this.getClass().getName());


    public LocationEntity getLocationEntityById(int id) {
        TypedQuery<LocationEntity> query = entityManager.createNamedQuery(LocationEntity.GET, LocationEntity.class);
        query.setParameter("id", id);

        LocationEntity locationEntity;

        try {
            locationEntity = query.getSingleResult();
        } catch (Exception e) {
            locationEntity = null;
        }

        return locationEntity;

    }

    // noch kaputt
    public Location getLocationById(int id) {
        LocationEntity locationEntity = getLocationEntityById(id);
        return new Location(locationEntity);
    }


    public Event[] getFirstPosts(int id, int userId, int postNum) {
        return eventController.getFirstPostsOfLocation(id, userId, postNum);
    }


    public com.server.datatype.Event[] getNextPosts(int id, int userId, int postNum, int lastPostId) {
        return eventController.getNextPostsOfLocation(id, userId, postNum, lastPostId);
    }


    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public com.server.datatype.Location[] getAllLocation() {
        TypedQuery<LocationEntity> query = entityManager
                .createNamedQuery(LocationEntity.GETALL, LocationEntity.class);
        if (query.getResultList() == null) {
            throw new NullPointerException();
        }
        List<LocationEntity> locationEntityList = query.getResultList();


        return Utils.convertToDataLocationArray(locationEntityList);

    }


    public Location[] getLocations(int cityId) {
        TypedQuery<LocationEntity> query = entityManager
                .createNamedQuery(LocationEntity.GETCITYLOCATIONS, LocationEntity.class);
        query.setParameter("cityId", cityId);
        List<LocationEntity> locationEntityList = query.getResultList();

        return Utils.convertToDataLocationArray(locationEntityList);
    }


    public Location createLocation(Location location) {


        LocationEntity locationEntity = createLocation(location.getName(),
                location.getCityId(),
                location.getStreet(),
                location.getHouseNumber(),
                location.getPhoneNumber(),
                location.getDescription(),
                location.getGps());

        return new Location(locationEntity);

    }


    public LocationEntity createLocation(String name, int cityId, String street, String houseNumber, String phoneNumber, String description, String gPS) {

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCityEntity(cityController.getCityEntity(cityId));
        locationEntity.setDescription(description);
        locationEntity.setGPS(gPS);
        locationEntity.setName(name);
        locationEntity.setHouseNumber(houseNumber);
        locationEntity.setStreet(street);
        locationEntity.setLikes(0);
        locationEntity.setPhoneNumber(phoneNumber);


        entityManager.persist(locationEntity);
        return locationEntity;
    }

    public Event createEvent(int id, int userId, String text) {

        return new Event(eventController.createEvent(id, userId, text));

    }


    public Location[] getLocationBySearchText(String searchText) {
        TypedQuery<LocationEntity> locationEntityTypedQuery = entityManager
                .createQuery(LocationEntity.GETBYNAME, LocationEntity.class);
        locationEntityTypedQuery.setParameter("name", searchText);
        List<LocationEntity> locationEntityList = locationEntityTypedQuery.getResultList();

        return Utils.convertToDataLocationArray(locationEntityList);
    }

    public boolean getLiked(int id, int userId) {
        List<LocationEntity> likedLocation = userController.getUser(userId).getLikedLocationEntities();
        LocationEntity locationEntity = getLocationEntityById(id);

        boolean liked = false;
        for (LocationEntity l : likedLocation) {
            if (locationEntity.getId() == l.getId()) {
                liked = true;
            }
        }

        return liked;
    }

    public int getLikeCount(int id) {
        return getLocationEntityById(id).getLikes();
    }

    public Location[] getNextLocationsWithTagId(int tagId, int cityId, int postNum, int lastLocationId) {

        TypedQuery<LocationEntity> locationEntityTypedQuery = entityManager
                .createQuery(LocationEntity.GETCITYLOCATIONSWITHTAG, LocationEntity.class);
        locationEntityTypedQuery.setParameter("tagId", tagId );
        locationEntityTypedQuery.setParameter("lastLocationId", lastLocationId);
        locationEntityTypedQuery.setParameter("cityId", cityId);
        locationEntityTypedQuery.setMaxResults(postNum);

        List<LocationEntity> locationEntityList = locationEntityTypedQuery.getResultList();


        return Utils.convertToDataLocationArray(locationEntityList);
    }

    public Location addTag(int id, int tagId) {
        LocationEntity locationEntity = getLocationEntityById(id);
        TagEntity tagEntity = tagController.getTagEntity(id);

        locationEntity.addTag(tagEntity);

        entityManager.merge(locationEntity);

        return new Location(locationEntity);

    }

    public Location removeTag(int id, int i) {
        LocationEntity locationEntity = getLocationEntityById(id);
        TagEntity tagEntity = tagController.getTagEntity(id);

        locationEntity.removeTag(tagEntity);

        entityManager.merge(locationEntity);

        return new Location(locationEntity);

    }

    public boolean isUpToDate(int id, int eventId) {
        boolean isUpToDate = true;

        EventEntity eventEntity = eventController.getFirstPostsOfLocation(id, 1).get(0);
        if (eventId < eventEntity.getId() ){
            isUpToDate = false;
        }

        return isUpToDate;
    }
}
