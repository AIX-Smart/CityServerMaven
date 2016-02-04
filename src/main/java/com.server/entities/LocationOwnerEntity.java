package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by jp on 13.01.16.
 */

@Entity
public class LocationOwnerEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @OneToMany
    private List<AppUserEntity> appUserEntityList;

    @OneToMany
    private List<LocationEntity> locationEntities;

    private String email;

    private String password;



    public String getEmail() {
        return email;
    }



    public void setEmail( String email ) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword( String password ) {
        this.password = password;
    }



    public List<AppUserEntity> getAppUserEntityList() {
        return appUserEntityList;
    }



    public void setAppUserEntityList( List<AppUserEntity> appUserEntityList ) {
        this.appUserEntityList = appUserEntityList;
    }



    public List<LocationEntity> getLocationEntities() {
        return locationEntities;
    }



    public void setLocationEntities( List<LocationEntity> locationEntities ) {
        this.locationEntities = locationEntities;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAppUser(AppUserEntity user ) {
        this.appUserEntityList.add( user );
    }



    public void appendLocation( LocationEntity locationEntity ) {

        this.locationEntities.add( locationEntity );
    }
}
