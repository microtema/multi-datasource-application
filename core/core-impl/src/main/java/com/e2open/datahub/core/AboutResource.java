package com.e2open.datahub.core;

import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Controller
@Path("/about")
public class AboutResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response about() {
        return Response.ok("{}").build();
    }

    @GET
    @Path("/foo")
    @Produces(MediaType.TEXT_PLAIN)
    public Response foo() {
        return Response.ok("REST Call TIME: " + new Date()).build();
    }
}
