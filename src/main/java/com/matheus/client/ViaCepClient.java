package com.matheus.client;

import com.matheus.vo.response.ViaCepResponse;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
public class ViaCepClient {

    @ConfigProperty(name = "viacep.url")
    String viacepUrl;

    public ViaCepResponse getCep(String cep) {
        Client client = ClientBuilder.newBuilder().build();

        return client.target(viacepUrl + "/ws/{cep}/json/")
                .resolveTemplate("cep", cep)
                .request()
                .get(ViaCepResponse.class);
    }
}
