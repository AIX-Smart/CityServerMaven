package com.server.controller;

import com.server.entities.Event;
import com.server.entities.Location;

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



    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public Location[] getAllLocation() {
        TypedQuery<Location> query = entityManager.createNamedQuery( Location.GETALL, Location.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException(  );
        }
        List<Location> locationList = query.getResultList();
        return locationList.toArray(new Location[ locationList.size()]);

    }



    public void createPost(int id, int userId, String text ) {
        
        Event event = new Event();
        event.setContent( text );
        event.setDate( Calendar.getInstance() );
        event.setAppuserid( userId );
        event.setLocationid( id );

        entityManager.persist(event);


    }



    public Event[] getFirstPosts( int id, int userId, int postNum ) {
        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GETLOCATION, Event.class );
        query.setParameter( "locationId", id );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Event> eventList = query.getResultList();

        List<Event> returnList = eventList.subList( 0, postNum );

        return returnList.toArray(new Event[ returnList.size()]);
    }



    public Event[] getNextPosts( int id, int userId, int postNum, int lastPostId ) {

        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GETLOCATION, Event.class );
        query.setParameter( "locationId", id );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Event> eventList = query.getResultList();

        int i = 0;
        for( ; i < eventList.size(); i++){
            if (eventList.get( i ).getId() == lastPostId){
                break;
            }
        }
        if (i == eventList.size()){
            return new Event[0];
        }

        List<Event> returnList = eventList.subList( i, i + postNum );

        return returnList.toArray(new Event[ returnList.size()]);

    }



}
