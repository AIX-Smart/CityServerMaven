package com.aix.server;

import com.aix.server.controller.LocationController;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jp on 02.11.15.
 */
@Path( "/tag" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class TagRestService
        extends ApplicationRestService {

    @EJB
    private LocationController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    // Get all tags?
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response getAll() {
        return Response.ok().build();
    }


    //Get first Posts with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}" )
    public Response getPost( @PathParam( "id" ) long id,
                              @PathParam( "userId" ) long userId,
                              @PathParam( "postNum" ) int postNum
    ) {
        return Response.ok().build();
    }


    //Get following post from last PostId on with tag
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

    //Get first Posts with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{userId}/{locationNum}" )
    public Response getLocation( @PathParam( "id" ) long id,
                              @PathParam( "userId" ) long userId,
                              @PathParam( "locationNum" ) int postNum
    ) {
        return Response.ok().build();
    }


    //Get following post from last PostId on with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{userId}/{locationNum}/{lastLocationId}" )
    public Response getLocatioin( @PathParam( "id" ) long id,
                              @PathParam( "userId" ) long userId,
                              @PathParam( "locationNum" ) int postNum,
                              @PathParam( "lastLocationId" ) long lastPostId
    ) {
        return Response.ok().build();
    }

    //Get tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) long id ) {
        return Response.ok().build();
    }




}