package com.server.controller;

import com.server.Utils;
import com.server.entities.AppUser;
import com.server.entities.Event;
import com.server.entities.Location;

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


    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public com.server.datatype.Location[] getAllLocation() {
        TypedQuery<Location> query = entityManager.createNamedQuery( Location.GETALL, Location.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException(  );
        }
        List<Location> locationList = query.getResultList();



        return Utils.convertToDataLocationArray(locationList);

    }



    public void createPost(int id, int userId, String text ) {
        
        Event event = new Event();
        event.setContent( text );
        event.setDate( Calendar.getInstance() );
        event.setAppuserid( userId );
        event.setLocationid( id );

        entityManager.persist(event);


    }



    public com.server.datatype.Event[] getFirstPosts( int id, int userId, int postNum ) {
        return getNextPosts(id, userId, postNum, Integer.MAX_VALUE);
    }



    public com.server.datatype.Event[] getNextPosts( int id, int userId, int postNum, int lastPostId ) {

        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GETLOCATION, Event.class );
        query.setParameter("lastPostId", lastPostId);
        query.setParameter( "locationId", id );
        query.setMaxResults(postNum);
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Event> eventList = query.getResultList();

        AppUser user = userController.getUser(userId);

        return Utils.convertToDataEventArray(eventList, user);
    }


    public void createLocation(com.server.datatype.Location location) {

        Location entity = new Location();
        entity.setCityId(location.getCityId());
        entity.setDescription(location.getDescription());
        entity.setGPS(location.getGps());
        entity.setName(location.getName());
        entity.setHouseNumber(location.getHouseNumber());
        entity.setStreet(location.getStreet());
        entity.setLikes(0);
        entity.setPhoneNumber(location.getPhoneNumber());

        entityManager.persist( entity );

    }
}
