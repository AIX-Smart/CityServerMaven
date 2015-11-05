package com.server.controller;

import com.server.entities.User;

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



    public User getUser() {

        TypedQuery<User> query = entityManager.createNamedQuery( User.GET, User.class );
        User user = query.getSingleResult();
        return user;

    }
}
