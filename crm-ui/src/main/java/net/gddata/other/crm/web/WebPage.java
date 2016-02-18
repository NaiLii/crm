package net.gddata.other.crm.web;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Produces(MediaType.APPLICATION_JSON)
public class WebPage {

    @Context
    protected UriInfo uriInfo;

    public String getBasePath() {
        return uriInfo.getBaseUri().getPath();
    }

    public String getRequestPath() {
        return uriInfo.getRequestUri().getPath();
    }

    /**
     * Return page title
     */
    public String getTitle() {
        return getClass().getSimpleName();
    }

}
