package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@NamedQueries( {
        @NamedQuery( name = AppUserEntity.GET, query = "SELECT u FROM AppUserEntity u WHERE u.id = :id and u.deleted = false" ),
        @NamedQuery( name = AppUserEntity.GETBYDEVICEID, query = "SELECT u FROM AppUserEntity u WHERE u.deviceId = :deviceId and u.deleted = false" ),
        @NamedQuery( name = AppUserEntity.GETALL, query = "SELECT u FROM AppUserEntity u where u.deleted = false" )
} )
@Entity
public class AppUserEntity {

    public static final String GET           = "AppUser.get";
    public static final String GETBYDEVICEID = "AppUser.getByDeviceId";
    public static final String GETALL        = "AppUser.getAll";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String deviceId;

    private int businessId;

    private boolean deleted;

    @OneToMany
    private List<LocationOwnerEntity> ownerEntityList;

    @OneToMany
    private List<EventEntity> likedEventEntities;

    @OneToMany
    private List<CommentEntity> likedCommentEntities;

    @OneToMany
    private List<LocationEntity> likedLocationEntities;



    public int getId() {
        return id;
    }



    public void setId( int userId ) {
        this.id = userId;
    }



    public String getDeviceId() {
        return deviceId;
    }



    public void setDeviceId( String deviceId ) {
        this.deviceId = deviceId;
    }



    public long getBusinessId() {
        return businessId;
    }



    public void setBusinessId( int businessId ) {
        this.businessId = businessId;
    }



    public List<EventEntity> getLikedEventEntities() {
        return likedEventEntities;
    }



    public void setLikedEventEntities( List<EventEntity> likedEventEntities ) {
        this.likedEventEntities = likedEventEntities;
    }



    public List<CommentEntity> getLikedCommentEntities() {
        return likedCommentEntities;
    }



    public void setLikedCommentEntities( List<CommentEntity> likedCommentEntities ) {
        this.likedCommentEntities = likedCommentEntities;
    }



    public List<LocationEntity> getLikedLocationEntities() {
        return likedLocationEntities;
    }



    public void setLikedLocationEntities( List<LocationEntity> likedLocationEntities ) {
        this.likedLocationEntities = likedLocationEntities;
    }



    public boolean isDeleted() {
        return deleted;
    }



    public void setDeleted( boolean deleted ) {
        this.deleted = deleted;
    }


    public List<LocationOwnerEntity> getOwnerEntityList() {
        return ownerEntityList;
    }

    public void setOwnerEntityList(List<LocationOwnerEntity> ownerEntityList) {
        this.ownerEntityList = ownerEntityList;
    }
}
