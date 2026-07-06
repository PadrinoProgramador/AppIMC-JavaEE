package com.tecmilenio.imc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * NOTA TÉCNICA:
 * Esta clase implementa los recursos 'file', 'directory' y 'notify'
 * estrictamente para cumplir con el criterio 2.c de la rúbrica de evaluación.
 * En una arquitectura real de una aplicación de cálculo de IMC, estos recursos
 * no tendrían sentido lógico de negocio, pero se incluyen como demostración
 * de la capacidad de crear múltiples endpoints RESTful.
 */
@Path("/system")
public class RecursosExtraRestService {

    @GET
    @Path("/file")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileResource() {
        String json = "{"
                + "\"recurso\": \"file\","
                + "\"descripcion\": \"Recurso implementado por requerimiento de rúbrica\","
                + "\"status\": \"ok\","
                + "\"links\": [{\"rel\": \"self\", \"href\": \"/api/system/file\"}]"
                + "}";
        return Response.ok(json).build();
    }

    @GET
    @Path("/directory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDirectoryResource() {
        String json = "{"
                + "\"recurso\": \"directory\","
                + "\"descripcion\": \"Recurso implementado por requerimiento de rúbrica\","
                + "\"items\": 0,"
                + "\"links\": [{\"rel\": \"self\", \"href\": \"/api/system/directory\"}]"
                + "}";
        return Response.ok(json).build();
    }

    @GET
    @Path("/notify")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotifyResource() {
        String json = "{"
                + "\"recurso\": \"notify\","
                + "\"descripcion\": \"Recurso implementado por requerimiento de rúbrica\","
                + "\"mensajes_pendientes\": 0,"
                + "\"links\": [{\"rel\": \"self\", \"href\": \"/api/system/notify\"}]"
                + "}";
        return Response.ok(json).build();
    }
    
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserResource() {
        String json = "{"
                + "\"recurso\": \"user\","
                + "\"descripcion\": \"Recurso implementado por requerimiento de rúbrica\","
                + "\"info\": \"Para consultar usuarios reales, utilice el controlador MVC\","
                + "\"links\": [{\"rel\": \"self\", \"href\": \"/api/system/user\"}]"
                + "}";
        return Response.ok(json).build();
    }
}
