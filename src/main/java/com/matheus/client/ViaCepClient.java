package com.matheus.client;

import com.matheus.vo.response.ViaCepResponse;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@RequestScoped
public class ViaCepClient {

    public ViaCepResponse getCep(String cep) {
        Client client = ClientBuilder.newBuilder().build();

        return client.target("https://viacep.com.br/ws/{cep}/json/")
                .resolveTemplate("cep", cep)
                .request()
                .get(ViaCepResponse.class);
    }
}
