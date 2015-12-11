package com.server.controller;

import com.server.Utils;
import com.server.datatype.User;
import com.server.entities.AppUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jp on 05.11.15.
 */
@Stateless
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;


    public AppUser createUser( String deviceId){
        AppUser newUser = new AppUser();
        newUser.setDeviceId(deviceId);

        entityManager.persist( newUser );

        return newUser;
    }

    public AppUser getUser( int userId) {

        TypedQuery<AppUser> query = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        query.setParameter("id", userId);
        AppUser appUser = query.getSingleResult();
        return appUser;

    }

    public List<AppUser> getAllUser() {
        TypedQuery<AppUser> query = entityManager.createNamedQuery( AppUser.GETALL, AppUser.class);
        List<AppUser> userList = query.getResultList();

        return  userList;
    }

    public User getUserByDeviceId(String deviceId) {

        TypedQuery<AppUser> query = entityManager.createNamedQuery( AppUser.GETBYDEVICEID, AppUser.class );
        query.setParameter("deviceId", deviceId);
        query.setMaxResults(1);
        List<AppUser> appUserList = query.getResultList();
        if (appUserList.isEmpty()){
            appUserList.add(createUser(deviceId));
        }
        AppUser userEntity = appUserList.get(0);

        return Utils.convertToDataUser(userEntity) ;
    }
}
