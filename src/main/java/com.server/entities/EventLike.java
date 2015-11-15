package com.server.entities;

import javax.persistence.*;

/**
 * Created by Thomas on 15.11.2015.
 */
@NamedQueries( {
        @NamedQuery( name = EventLike.GET_EVENT_LIKE, query = "SELECT el FROM EventLike el WHERE el.eventId = :eventId AND el.appUserId = :userId" )
} )

@Entity
public class EventLike {

    public static final String GET_EVENT_LIKE = "getEventLike";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private int eventId;
    private int appUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }
}
