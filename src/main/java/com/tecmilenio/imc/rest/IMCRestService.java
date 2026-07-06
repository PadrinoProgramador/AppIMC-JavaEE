package com.tecmilenio.imc.rest;

import com.tecmilenio.imc.dao.RegistroIMCDAO;
import com.tecmilenio.imc.model.RegistroIMC;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/historico")
public class IMCRestService {

    private RegistroIMCDAO registroDAO = new RegistroIMCDAO();

    /**
     * Servicio REST para obtener el histórico en formato JSON
     */
    @GET
    @Path("/usuario/{id}/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerHistoricoJSON(@PathParam("id") int usuarioId) {
        try {
            List<RegistroIMC> historico = registroDAO.obtenerHistorico(usuarioId);
            if (historico.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"No se encontraron registros para el usuario\"}")
                        .build();
            }
            return Response.ok(historico).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno del servidor\"}")
                    .build();
        }
    }

    /**
     * Servicio REST para obtener el histórico en formato XML
     */
    @GET
    @Path("/usuario/{id}/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response obtenerHistoricoXML(@PathParam("id") int usuarioId) {
        try {
            List<RegistroIMC> historico = registroDAO.obtenerHistorico(usuarioId);
            if (historico.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            // Para devolver una lista en XML usando JAX-RS (Jersey)
            return Response.ok(historico.toArray(new RegistroIMC[0])).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
