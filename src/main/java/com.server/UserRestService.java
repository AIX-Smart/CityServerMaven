package com.server;

/**
 * Created by jp on 05.11.15.
 */

import com.server.controller.UserController;
import com.server.datatype.User;
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

@Path("/user")
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class UserRestService
        extends ApplicationRestService {


    @EJB
    private UserController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response getAll() {

        User [] users  = controller.getAllUser();
        try {
            return Response.ok( mapper.writeValueAsString( users ) ).build();
        } catch ( Exception e ) {
            return Response.ok( "No User found" ).build();

        }

    }

    @GET
    @Path( "/{deviceId}" )
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response login(@PathParam( "deviceId" ) String deviceId) {

        User user  = controller.getUserByDeviceId( deviceId );
        try {
            return Response.ok( mapper.writeValueAsString( user )).build();
        } catch ( Exception e ) {
            return Response.ok( "No User found" ).build();

        }

    }







}
