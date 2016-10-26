package com.apptech.setting.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("systemconfig")
public class SystemConfigResource {

    @Path("endpoint")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response encryptString(@Context HttpServletRequest request) {
        Map<String, String> sysConfig = new HashMap<>();
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        sysConfig.putIfAbsent("endPointUrl", baseUrl + "/dbrouter/config");
        return Response.ok(sysConfig).build();
    }
}
