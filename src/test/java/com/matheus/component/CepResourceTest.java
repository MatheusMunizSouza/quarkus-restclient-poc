package com.matheus.component;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import com.matheus.component.resource.ViaCepWireMockResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(ViaCepWireMockResource.class)
class CepResourceTest {

  @Test
  @DisplayName("Should get address from cep successfully")
  void shouldGetAddressFromCepSuccessfully() {

    stubFor(get(urlEqualTo("/ws/01001000/json/"))
        .willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(
                """
                {
                    "cep": "01001-000",
                    "logradouro": "Praça da Sé",
                    "complemento": "RETURN FROM WIREMOCK",
                    "bairro": "Sé",
                    "localidade": "São Paulo",
                    "uf": "SP",
                    "ibge": "3550308",
                    "gia": "1004",
                    "siafi": "7107"
                }
                """
            )));

    when()
        .get("/ceps/01001000")
        .then()
        .statusCode(200)
        .body(is("{\"cep\":\"01001-000\",\"logradouro\":\"Praça da Sé\",\"complemento\":\"RETURN FROM WIREMOCK\",\"bairro\":\"Sé\",\"localidade\":\"São Paulo\",\"uf\":\"SP\",\"ibge\":\"3550308\",\"gia\":\"1004\",\"siafi\":\"7107\"}"));
  }
}