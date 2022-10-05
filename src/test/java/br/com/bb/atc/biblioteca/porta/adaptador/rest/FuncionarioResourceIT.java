package br.com.bb.atc.biblioteca.porta.adaptador.rest;

import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoPegarLivroEmprestado;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;

@QuarkusTest
class FuncionarioResource2Test {

    @TestHTTPResource("/funcionario/pegalivro")
    URL pegaLivroEndpoint;

    ComandoPegarLivroEmprestado comandoPegarLivroEmprestado;

    @BeforeEach
    void criaFixtures() {
        comandoPegarLivroEmprestado =
                new ComandoPegarLivroEmprestado("Refatoração",
                        "F0741372");
    }

    @Test
    void pegaLivro() {
        given().contentType(ContentType.JSON).body(comandoPegarLivroEmprestado)
                .when().post(pegaLivroEndpoint)
                .then().statusCode(204);
    }
}