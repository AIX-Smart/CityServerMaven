package com.server;

import com.server.controller.TagController;
import com.server.entities.Location;
import com.server.entities.Post;
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
    private TagController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    // Get all tags?
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response getAll() {
        return Response.ok().build();
    }



    //Get first Location with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{locationNum}" )
    public Response getLocation( @PathParam( "id" ) int id,
                             @PathParam( "userId" ) int userId,
                             @PathParam( "locationNum" ) int locationNum
    ) {
        Location[] locations = controller.getNextLocations( id, userId, locationNum );
        try{
            return  Response.ok(mapper.writeValueAsString( locations )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();    }



    //Get following Location from lastLocationId on with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}/{lastPostId}" )
    public Response getLocation( @PathParam( "id" ) int id,
                              @PathParam( "userId" ) int userId,
                              @PathParam( "postNum" ) int postNum,
                              @PathParam( "lastPostId" ) int lastPostId
    ) {
        Location[] locations = controller.getNextLocations( id, userId, postNum, lastPostId );
        try{
            return  Response.ok(mapper.writeValueAsString( locations )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();    }

    //Get first Posts with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{userId}/{locationNum}" )
    public Response getPost( @PathParam( "id" ) int id,
                              @PathParam( "userId" ) int userId,
                              @PathParam( "locationNum" ) int postNum
    ) {
        Post[] posts = controller.getNextPosts( id, userId, postNum );
        try{
            return  Response.ok(mapper.writeValueAsString( posts )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();    }


    //Get following post from last PostId on with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{userId}/{locationNum}/{lastLocationId}" )
    public Response getPost( @PathParam( "id" ) int id,
                              @PathParam( "userId" ) int userId,
                              @PathParam( "locationNum" ) int postNum,
                              @PathParam( "lastLocationId" ) int lastPostId
    ) {
        Post[] posts = controller.getNextPosts( id, userId, postNum, lastPostId );
        try{
            return  Response.ok(mapper.writeValueAsString( posts )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();    }

    //Get tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) long id ) {
        return Response.ok().build();
    }




}