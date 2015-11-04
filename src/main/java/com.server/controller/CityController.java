package com.server.controller;

import com.server.entities.PostBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class CityController {

    @PersistenceContext
    private EntityManager entityManager;

    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Post aus der Tabelle wieder
    public PostBean[] getFirstPostOfCity(){
        TypedQuery<PostBean> query = entityManager.createNamedQuery( PostBean.GET, PostBean.class );
        List<PostBean> postBeanList = query.getResultList();
        return postBeanList.toArray(new PostBean[postBeanList.size()]);

    }

}
