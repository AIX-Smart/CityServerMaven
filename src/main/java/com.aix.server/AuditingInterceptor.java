package com.aix.server;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;

/**
 * Created by jp on 02.11.15.
 */
public class AuditingInterceptor {

    private static final Logger LOGGER = Logger.getLogger( "AuditingInterceptor" );
    @Resource
    private SessionContext context;



    @AroundInvoke
    protected Object audit( InvocationContext ctx ) {
        Object responseObject = null;


        try {
            responseObject = ctx.proceed();
            StringBuilder methodInformation = new StringBuilder();
            String currentUser = context.getCallerPrincipal().getName();

            methodInformation.append( "Class: " );
            methodInformation.append( ctx.getMethod().getDeclaringClass().getSimpleName() );
            methodInformation.append( " Method: " ).append( ctx.getMethod().getName() );

            Object[] parameters = ctx.getParameters();
            if ( null != parameters ) {
                methodInformation.append( "/" );
                for ( int i = 0; i < parameters.length; i++ ) {
                    Object currentParameter = parameters[ i ];
                    methodInformation.append( currentParameter );
                    if ( i < ( parameters.length - 1 ) ) {
                        methodInformation.append( "/" );
                    }
                }
            }

            methodInformation.append( " | " + currentUser );

            if ( responseObject instanceof Response ) {
                Response response = ( Response )responseObject;
                for ( Response.Status status : Response.Status.values() ) {
                    if ( response.getStatus() == status.getStatusCode() ) {
                        methodInformation.append( " | Status:" + status.name() + "(" + response.getStatus() + ")" );
                        break;
                    }
                }
            }

            LOGGER.info( methodInformation );
        } catch ( Exception e ) {
            LOGGER.error( e );
        }

        return responseObject;
    }
}

