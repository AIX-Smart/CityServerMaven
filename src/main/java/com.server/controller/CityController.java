package com.server.controller;

import com.server.datatype.City;
import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.entities.CityEntity;
import com.server.entities.EventEntity;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class CityController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;

    @EJB
    private LocationController locationController;

    @EJB
    private EventController eventController;

    private Logger logger = Logger.getLogger( this.getClass().getName() );


    public CityEntity getCityEntity( int cityId ) {
        TypedQuery<CityEntity> query = entityManager.createNamedQuery( CityEntity.GETCITYBYID, CityEntity.class );
        query.setParameter( "cityId", cityId );
        CityEntity city = query.getSingleResult();
        return city;
    }

    public City getCity( int cityId ) {

        return new City( getCityEntity( cityId ) );
    }



    public City[] getAll() {
        TypedQuery<CityEntity> query = entityManager.createNamedQuery( CityEntity.GETALL, CityEntity.class );
        List <CityEntity> cityEntities = query.getResultList();

        return Utils.convertToDataCityArray(cityEntities);
    }



    public Location[] getLocations( int cityId ) {
        return locationController.getLocations( cityId );
    }



    public Location[] getLocationBySearchText( String searchText ) {
        return locationController.getLocationBySearchText( searchText );
    }



    public com.server.datatype.Event[] getFirstPostsOfCity( int cityId, int userId, int postNum ) {
        return eventController.getFirstPostsOfCity( cityId, userId, postNum );
    }



    public com.server.datatype.Event[] getNextPostsOfCity( int cityId, int userId, int postNum, int lastPostId ) {
        return eventController.getNextPostsOfCity( cityId, userId, postNum, lastPostId );
    }



    public City createCity( String cityName ) {

        CityEntity cityEntity;

        try {
            TypedQuery<CityEntity> query = entityManager.createNamedQuery( CityEntity.GETCITYBYNAME, CityEntity.class );
            query.setParameter( "cityName", cityName  );

            cityEntity = query.getSingleResult();

        } catch ( NoResultException e ){

            cityEntity = new CityEntity();
            cityEntity.setName( cityName );
            entityManager.persist( cityEntity );

        }

            return new City (cityEntity);
    }



    public Event createEvent( int id, int userId, String text ) {
        return null;
    }


    public boolean isUpToDate(int id, int eventId) {

        boolean isUpToDate = true;

        EventEntity eventEntity = eventController.getFirstPostsOfCity(id, 1).get(0);
        if (eventId < eventEntity.getId() ){
            isUpToDate = false;
        }

        return isUpToDate;
    }
}