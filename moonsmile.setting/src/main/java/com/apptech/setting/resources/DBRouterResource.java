package com.apptech.setting.resources;

import com.apptech.base.model.DBEndPoint;
import com.apptech.setting.services.DBRouterService;
import com.google.inject.Inject;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("dbrouter")
public class DBRouterResource {

    private DBRouterService dbRouterService;

    @Inject
    public DBRouterResource(DBRouterService dbRouterService) {
        this.dbRouterService = dbRouterService;
    }

    @Path("config")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response fetchDataBaseConfig(@FormParam("tenantcode") String tenantCode, @FormParam("readwrite") Integer readWrite) {
        DBEndPoint endPoint = dbRouterService.fetchEndPoint(tenantCode, readWrite);
        return Response.ok(endPoint).build();
    }
}
