package com.server.controller;

import com.server.entities.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class LocationController {

    @PersistenceContext
    private EntityManager entityManager;



    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Post aus der Tabelle wieder
    public Location[] getLocationOfCity() {
        TypedQuery<Location> query = entityManager.createNamedQuery( Location.GET, Location.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException(  );
        }
        List<Location> locationList = query.getResultList();
        return locationList.toArray(new Location[ locationList.size()]);

    }

}
