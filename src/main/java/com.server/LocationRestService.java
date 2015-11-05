package com.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.server.controller.LocationController;
import com.server.entities.Location;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jp on 02.11.15.
 */
@Path( "/location" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class LocationRestService
        extends ApplicationRestService {

    @EJB
    private LocationController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );


    // Get all locations?
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response getAll() {



        try {
            Location[] locationBeans = controller.getLocationOfCity();
            return  Response.ok(mapper.writeValueAsString( locationBeans )).build();
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }


        return Response.ok("42").build();
    }



    //Create Post
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/" )
    public Response createPost( @PathParam( "id" ) long timestamp,
                                @PathParam( "userId" ) long userId) {
        return Response.ok().build();
    }


    //Get first Posts
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}" )
    public Response getPost( @PathParam( "id" ) long id,
                                @PathParam( "userId" ) long userId,
                                @PathParam( "postNum" ) int postNum
    ) {
        return Response.ok().build();
    }


    //Get following post from last PostId on
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}/{lastPostId}" )
    public Response getPost( @PathParam( "id" ) long id,
                                @PathParam( "userId" ) long userId,
                                @PathParam( "postNum" ) int postNum,
                                @PathParam( "lastPostId" ) long lastPostId
    ) {
        return Response.ok().build();
    }

    //Get locationInformation
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) long id ) {
        return Response.ok().build();
    }




}