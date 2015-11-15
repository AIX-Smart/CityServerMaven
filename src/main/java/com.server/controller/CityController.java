package com.server.controller;

import com.server.entities.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public Event[] getAllPost(){
        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GETALL, Event.class );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Event> eventList = query.getResultList();
        return eventList.toArray(new Event[ eventList.size()]);

    }



    public void createPost(int cityId, int userId, String text ) {

        //zurzeit nur Aachen deswegen ist cityId egal

        Event event = new Event();
        event.setContent( text );
        event.setDate( Calendar.getInstance() );
        event.setAppuserid( userId );

        entityManager.persist(event);


    }



    public com.server.datatype.Event[] getFirstPosts(int cityId, int userId, int postNum ) {
        return getNextPosts(cityId, userId, postNum, Integer.MAX_VALUE);
    }

    public com.server.datatype.Event[] getNextPosts( int cityId, int userId, int postNum, int lastPostId ) {
        TypedQuery<Event> query = entityManager.createNamedQuery(Event.GETCITY, Event.class);
        query.setParameter("cityId", cityId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);

        List<Event> eventList = query.getResultList();
        if(eventList == null){
            throw new NullPointerException(  );
        }

        com.server.datatype.Event[] events = new com.server.datatype.Event[eventList.size()];
        for (int i = 0; i < eventList.size(); i++){
            events[i]= new com.server.datatype.Event(eventList.get(i));
        }


        return events;
    }
}
