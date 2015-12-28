package com.server.controller;

import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.entities.EventEntity;
import com.server.entities.LocationEntity;
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

    private Logger logger = Logger.getLogger( this.getClass().getName() );


    private LocationEntity getLocationEntityById( int id ) {
        TypedQuery<LocationEntity> query = entityManager.createNamedQuery( LocationEntity.GET, LocationEntity.class );
        query.setParameter( "id", id );

        LocationEntity locationEntity;

        try {
            locationEntity = query.getSingleResult();
        } catch ( Exception e ) {
            locationEntity = null;
        }

        return locationEntity;

    }
    // noch kaputt
    public Location getLocationById( int id ) {
        LocationEntity locationEntity = getLocationEntityById( id );
        return new Location( locationEntity );
    }



    public Event[] getFirstPosts( int id, int userId, int postNum ) {
        return eventController.getFirstPosts( id, userId, postNum );
    }



    public com.server.datatype.Event[] getNextPosts( int id, int userId, int postNum, int lastPostId ) {
        return eventController.getNextPosts( id, userId, postNum, lastPostId );
    }



    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public com.server.datatype.Location[] getAllLocation() {
        TypedQuery<LocationEntity> query = entityManager
                .createNamedQuery( LocationEntity.GETALL, LocationEntity.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException();
        }
        List<LocationEntity> locationEntityList = query.getResultList();


        return Utils.convertToDataLocationArray( locationEntityList );

    }



    public Location[] getLocations( int cityId ) {
        TypedQuery<LocationEntity> query = entityManager
                .createNamedQuery( LocationEntity.GETCITYLOCATIONS, LocationEntity.class );
        query.setParameter( "cityId", cityId );
        List<LocationEntity> locationEntityList = query.getResultList();

        return Utils.convertToDataLocationArray( locationEntityList );
    }


    public Location createLocation( Location location ) {


        LocationEntity locationEntity = createLocation( location.getName(),
                                                        location.getCityId(),
                                                        location.getStreet(),
                                                        location.getHouseNumber(),
                                                        location.getPhoneNumber(),
                                                        location.getDescription(),
                                                        location.getGps() );

        return new Location( locationEntity );

    }



    public LocationEntity createLocation( String name, int cityId, String street, String houseNumber, String phoneNumber, String description, String gPS ) {

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCityEntity( cityController.getCityEntity( cityId ) );
        locationEntity.setDescription( description );
        locationEntity.setGPS( gPS );
        locationEntity.setName( name );
        locationEntity.setHouseNumber( houseNumber );
        locationEntity.setStreet( street );
        locationEntity.setLikes( 0 );
        locationEntity.setPhoneNumber( phoneNumber );


        entityManager.persist( locationEntity );
        return locationEntity;
    }

    public void createEvent(int id, int userId, String text ) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setContent( text );
        eventEntity.setDate( Calendar.getInstance() );
        eventEntity.setAppUserEntity( userController.getUser( userId ) );
        eventEntity.setLocationEntity( getLocationEntityById( id ) );

        entityManager.persist( eventEntity );

    }


    public Location[] getLocationBySearchText( String searchText ) {
        TypedQuery<LocationEntity> locationEntityTypedQuery = entityManager
                .createQuery( LocationEntity.GETBYNAME, LocationEntity.class );
        locationEntityTypedQuery.setParameter( "name", searchText );
        List<LocationEntity> locationEntityList = locationEntityTypedQuery.getResultList();

        return Utils.convertToDataLocationArray( locationEntityList );
    }
}
