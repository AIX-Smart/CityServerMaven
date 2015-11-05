package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by jp on 02.11.15.
 */
@NamedQueries( {
        @NamedQuery( name = User.GET, query = "SELECT u FROM User u " )
      } )
@Entity
public class User {

    public static final String GET = "User.get";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private long handyId;

    private int permission;

    private long businessId;



    public long getId() {
        return id;
    }



    public void setId( long userId ) {
        this.id = userId;
    }



    public long getHandyId() {
        return handyId;
    }



    public void setHandyId( long handyId ) {
        this.handyId = handyId;
    }



    public int getPermission() {
        return permission;
    }



    public void setPermission( int permission ) {
        this.permission = permission;
    }



    public long getBusinessId() {
        return businessId;
    }



    public void setBusinessId( long businessId ) {
        this.businessId = businessId;
    }
}
