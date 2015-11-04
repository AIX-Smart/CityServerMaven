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
        @NamedQuery( name = UserBean.GET, query = "SELECT d FROM User d " ),
      } )
@Entity
public class UserBean {

    public static final String GET = "User.get";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long userId;

    private long handyId;

    private int permission;

    private long businessId;



    public long getUserId() {
        return userId;
    }



    public void setUserId( long userId ) {
        this.userId = userId;
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
