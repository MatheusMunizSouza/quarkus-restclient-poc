package com.matheus.resource;

import com.matheus.client.ViaCepClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ceps")
public class CepResource {

    private final ViaCepClient viaCepClient;

    public CepResource(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @GET
    @Path("/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCep(@PathParam("cep") String cep) {
        return Response.ok(this.viaCepClient.getCep(cep)).build();
    }
}
