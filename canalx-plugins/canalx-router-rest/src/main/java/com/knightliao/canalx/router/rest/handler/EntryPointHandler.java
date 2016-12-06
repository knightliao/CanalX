package com.knightliao.canalx.router.rest.handler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author knightliao
 * @date 2016/12/2 18:34
 */
@Path("/canalx")
public class EntryPointHandler {

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        return "Test";
    }
}
