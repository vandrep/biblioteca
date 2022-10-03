package br.com.bb.atc.biblioteca.porta.adaptador.rest;

import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoPegarLivroEmprestado;
import br.com.bb.atc.biblioteca.dominio.modelo.Funcionario;
import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import br.com.bb.atc.biblioteca.dominio.modelo.Matricula;
import br.com.bb.atc.biblioteca.porta.adaptador.persistencia.OracleDBFuncionarioRepository;
import br.com.bb.atc.biblioteca.porta.adaptador.persistencia.OracleDBLivroRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URL;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(FuncionarioResource.class)
class FuncionarioResourceTest {

    @TestHTTPResource("/funcionario/pegalivro")
    URL pegaLivroEndpoint;
    @InjectMock
    OracleDBLivroRepository livroRepository;

    @InjectMock
    OracleDBFuncionarioRepository funcionarioRepository;

    ComandoPegarLivroEmprestado comandoPegarLivroEmprestado;

    @BeforeEach
    void criaFixtures() {
        var livro = new Livro("Refatoração");
        var uniLivro = Uni.createFrom().item(livro);
        Mockito.when(livroRepository.pegaLivroPeloNome(livro.nome())).thenReturn(uniLivro);

        var funcionario = new Funcionario(new Matricula("F0741372"));
        var uniFuncionario = Uni.createFrom().item(funcionario);
        Mockito.when(funcionarioRepository.pegaFuncionarioPorMatricula(funcionario.matricula())).thenReturn(uniFuncionario);

        comandoPegarLivroEmprestado = new ComandoPegarLivroEmprestado("Refatoração", "F0741372");
    }

    @Test
    void pegaLivro() {
        given().contentType(ContentType.JSON).body(comandoPegarLivroEmprestado)
                .when().post(pegaLivroEndpoint)
                .then().statusCode(204);
    }
}