package com.server;

import com.server.controller.LocationController;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.DELETE;
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
@Path( "/post" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class PostRestService
        extends ApplicationRestService {

    @EJB
    private LocationController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    // Get all events in the database which the user created himself
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "{userId}/" )
    public Response getAll(@PathParam( "userId" ) long userId) {
        return Response.ok().build();
    }



    //Create Comment
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "{id}/{userId}/" )
    public Response createComment( @PathParam( "id" ) long id,
                                @PathParam( "userId" ) long userId ) {
        return Response.ok().build();
    }



    //Get first Comments
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}" )
    public Response getComment( @PathParam( "id" ) long id,
                                @PathParam( "userId" ) long userId,
                                @PathParam( "postNum" ) int postNum ) {
        return Response.ok().build();
    }


    //Get following comment from last commentId on
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{commentNum}/{lastCommentId}" )
    public Response getComment( @PathParam( "id" ) long id,
                                @PathParam( "userId" ) long userId,
                                @PathParam( "commentNum" ) int commentNum,
                                @PathParam( "lastCommentId" ) long lastCommentId
    ) {
        return Response.ok().build();
    }



    //Delete Post
    @DELETE
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/" )
    public Response deletePost( @PathParam( "id" ) long id,
                                 @PathParam( "userId" ) long userId
                                   ) {
        return Response.ok().build();
    }


}