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
        @NamedQuery( name = AppUser.GET, query = "SELECT u FROM AppUser u WHERE u.id = :id" ),
        @NamedQuery( name = AppUser.GETALL, query = "SELECT u FROM AppUser u" )
} )
@Entity
public class AppUser {

    public static final String GET    = "AppUser.get";
    public static final String GETALL = "AppUser.getAll";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private int smartPhoneId;

    private int permission;

    private int businessId;



    public long getId() {
        return id;
    }



    public void setId( int userId ) {
        this.id = userId;
    }



    public long getHandyId() {
        return smartPhoneId;
    }



    public void setHandyId( int smartPhoneId ) {
        this.smartPhoneId = smartPhoneId;
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



    public void setBusinessId( int businessId ) {
        this.businessId = businessId;
    }
}
