package com.github.knightliao.canalx.plugin.router.rest.support.server.impl;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.plugin.router.rest.CanalxRouterRest;
import com.github.knightliao.canalx.plugin.router.rest.filter.RequestFilter;
import com.github.knightliao.canalx.plugin.router.rest.handler.EntryPointHandler;
import com.github.knightliao.canalx.plugin.router.rest.support.server.ICanalxRouterServer;

/**
 * @author knightliao
 * @date 2016/12/6 20:29
 */
public class ICanalxRouterSeverImpl implements ICanalxRouterServer {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxRouterRest.class);

    private Server jettyServer = null;

    @Override
    public void start(int port) throws CanalxRouterException {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addFilter(RequestFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        jettyServer = new Server(port);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                EntryPointHandler.class.getCanonicalName());

        try {

            jettyServer.start();

            LOGGER.info("{} {} start ok", "http://0.0.0.0:" + port, CanalxRouterRest.class.getName());

            jettyServer.join();

        } catch (InterruptedException e) {

            LOGGER.warn(e.toString());

        } catch (Exception e) {

            throw new CanalxRouterException(e);

        } finally {

            jettyServer.destroy();
        }
    }

    @Override
    public void stop() throws CanalxRouterException {

        if (jettyServer != null) {

            try {

                jettyServer.stop();

            } catch (Exception e) {

                LOGGER.warn(e.toString());
            }
        }
    }

}
