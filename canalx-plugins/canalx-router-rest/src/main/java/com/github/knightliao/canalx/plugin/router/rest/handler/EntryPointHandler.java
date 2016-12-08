package com.github.knightliao.canalx.plugin.router.rest.handler;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.plugin.router.rest.support.server.CanalxDataGetter;

/**
 * @author knightliao
 * @date 2016/12/2 18:34
 */
@Path("/canalx/rest")
public class EntryPointHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(EntryPointHandler.class);

    @Context
    UriInfo url;

    @Context
    Request request;

    @Context
    Application application;

    @Context
    ServletContext context;

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("tableId") String tableId, @QueryParam("key") String key) {

        return CanalxDataGetter.get(tableId, key);

    }

}
