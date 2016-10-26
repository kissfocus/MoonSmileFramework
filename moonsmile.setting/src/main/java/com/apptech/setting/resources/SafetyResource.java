package com.apptech.setting.resources;

import com.apptech.base.tools.EncryptUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("safety")
public class SafetyResource {

    @Path("encrypt")
    @POST
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public Response encryptString(@FormParam("msg") String msg) {
        String result = EncryptUtils.encryptString(msg);
        return Response.ok(result).build();
    }
}
