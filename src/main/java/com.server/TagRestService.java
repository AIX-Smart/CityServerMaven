package com.server;

import com.server.controller.TagController;
import com.server.entities.EventEntity;
import com.server.entities.LocationEntity;
import com.server.entities.TagEntity;
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
import java.util.List;

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
    @Path( "/getAllTags/" )
    public Response getAll() {
        List<TagEntity> tags = controller.allTags(  );
        try {
            return Response.ok( mapper.writeValueAsString( tags ) ).build();
        } catch ( Exception e ) {

        }
        return Response.status( Response.Status.NOT_FOUND ).build();
//        return Response.ok().build();
    }



    //Get first Location with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{locationNum}/{userId}" )
    public Response getLocation( @PathParam( "id" ) int id,
                                 @PathParam( "locationNum" ) int locationNum,
                                 @PathParam( "userId" ) int userId
    ) {
        LocationEntity[] locationEntities = controller.getNextLocations( id, userId, locationNum );
        try{
            return  Response.ok(mapper.writeValueAsString( locationEntities )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();    }



    //Get following Location from lastLocationId on with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{locationNum}/{userId}/{lastLocationId}" )
    public Response getLocation( @PathParam( "id" ) int id,
                                 @PathParam( "locationNum" ) int locationNum,
                                 @PathParam( "userId" ) int userId,
                                 @PathParam( "lastLocationId" ) int lastPostId
    ) {
        LocationEntity[] locationEntities = controller.getNextLocations( id, userId, locationNum, lastPostId );
        try{
            return  Response.ok(mapper.writeValueAsString( locationEntities )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();    }

    //Get first Posts with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{postNum}/{userId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "postNum" ) int postNum,
                             @PathParam( "userId" ) int userId

    ) {
        EventEntity[] eventEntities = controller.getNextPosts( id, userId, postNum );
        try{
            return  Response.ok(mapper.writeValueAsString( eventEntities )).build();
        }catch ( Exception e ){

        }

        return Response.status( Response.Status.NOT_FOUND ).build();    }


    //Get following post from last PostId on with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{postNum}/{userId}/{lastPostId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "postNum" ) int postNum,
                             @PathParam( "userId" ) int userId,
                             @PathParam( "lastPostId" ) int lastPostId
    ) {
        EventEntity[] eventEntities = controller.getNextPosts( id, userId, postNum, lastPostId );
        try{
            return  Response.ok(mapper.writeValueAsString( eventEntities )).build();
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