package com.server.controller;

import com.server.entities.AppUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by jp on 05.11.15.
 */
@Stateless
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;



    public AppUser getUser() {

        TypedQuery<AppUser> query = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        AppUser appUser = query.getSingleResult();
        return appUser;

    }
}
