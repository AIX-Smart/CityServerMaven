package com.server;

/**
 * Created by jp on 02.11.15.
 */

import com.server.controller.CityController;
import com.server.entities.Post;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path( "/city" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class CityRestService
        extends ApplicationRestService {

    @EJB
    private CityController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    // Get all cities in the database
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response getAll() {
        Post[] posts = controller.getAllPost();
        try{
            return  Response.ok(mapper.writeValueAsString( posts )).build();
        }catch ( Exception e ){

        }
        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Create Post
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Consumes("text/plain")
    @Path( "{id}/{userId}" )
    public Response CreatePost( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                String text ) {
        controller.createPost( id, userId, text );
        return Response.ok().build();
    }



    //Get first Posts
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "userId" ) int userId,
                             @PathParam( "postNum" ) int postNum ) {
       Post[] posts = controller.getFirstPosts( id, userId, postNum );
        try{
            return  Response.ok(mapper.writeValueAsString( posts )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();

    }





    //Get following post from last PostId on
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}/{lastPostId}" )
    public Response getPost( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                @PathParam( "postNum" ) int postNum,
                                @PathParam( "lastPostId" ) int lastPostId
    ) {
        Post[] posts = controller.getNextPosts( id, userId, postNum, lastPostId );
        try{
            return  Response.ok(mapper.writeValueAsString( posts )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();

    }

    //Get cityInformation
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) long id ) {
        return Response.ok().build();
    }



}