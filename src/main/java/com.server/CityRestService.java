package com.server;

/**
 * Created by jp on 02.11.15.
 */

import com.server.controller.CityController;
import com.server.entities.PostBean;
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
        return Response.ok().build();
    }



    //Create Post
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/" )
    public Response CreatePost( @PathParam( "id" ) long timestamp,
                                @PathParam( "userId" ) long userId ) {
        return Response.ok().build();
    }



    //Get first Posts
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}" )
    public Response getPost( @PathParam( "id" ) long id,
                             @PathParam( "userId" ) long userId,
                                             @PathParam( "postNum" ) int postNum ) {
       PostBean[] postBeans = controller.getFirstPostOfCity();
        try{
            return  Response.ok(mapper.writeValueAsString( postBeans )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();

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

    //Get cityInformation
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) long id ) {
        return Response.ok().build();
    }




}