package br.com.bb.atc.biblioteca.porta.adaptador.rest;

import br.com.bb.atc.biblioteca.Fixtures;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoDevolveLivro;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoEmprestaLivro;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NO_CONTENT;

@QuarkusTest
@TestHTTPEndpoint(LivroWeb.class)
class LivroWebTest {
    @Inject
    Fixtures fixtures;
    ComandoEmprestaLivro comandoEmprestaLivro;
    ComandoDevolveLivro comandoDevolveLivro;

    @BeforeEach
    void setUp() {
        var livro = fixtures.criaLivroRefatoracao();
        var uniLivro = Uni.createFrom().item(livro);

        comandoEmprestaLivro = new ComandoEmprestaLivro("Refatoração", "F0741372");
        comandoDevolveLivro = new ComandoDevolveLivro("Refatoração", "F0741372");
    }

    @Test
    void listaTodosLivros() {
    }

    @Test
    void incluiLivro() {
    }

    @TestHTTPResource("/livro/emprestar")
    URL emprestarEndpoint;
    @Test
    void emprestaLivroDisponivelParaFuncionarioSemLivroEmprestado() {
        given().contentType(JSON).body(comandoEmprestaLivro)
                .when().post(emprestarEndpoint)
                .then().statusCode(NO_CONTENT);
    }

//    @TestHTTPResource("/livro/devolver")
//    URL devolverEndpoint;
//    @Test
//    void devolvePor() {
//        given().contentType(JSON).body(comandoDevolveLivro)
//                .when().post(devolverEndpoint)
//                .then().statusCode(NO_CONTENT);
//    }
}