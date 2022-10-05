package br.com.bb.atc.biblioteca.porta.adaptador.rest;

import br.com.bb.atc.biblioteca.Fixtures;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoDevolverLivroEmprestado;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoPegarLivroEmprestado;
import br.com.bb.atc.biblioteca.dominio.modelo.Funcionario;
import br.com.bb.atc.biblioteca.dominio.modelo.Matricula;
import br.com.bb.atc.biblioteca.porta.adaptador.persistencia.OracleDBFuncionarioRepository;
import br.com.bb.atc.biblioteca.porta.adaptador.persistencia.RepositorioLivroOracle;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.net.URL;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(FuncionarioResource.class)
class FuncionarioResourceTest {
    @Inject
    Fixtures fixtures;

    @InjectMock
    RepositorioLivroOracle repositorioLivro;

    @InjectMock
    OracleDBFuncionarioRepository funcionarioRepository;

    ComandoPegarLivroEmprestado comandoPegarLivroEmprestado;
    ComandoDevolverLivroEmprestado comandoDevolverLivroEmprestado;

    @BeforeEach
    void criaFixtures() {
        var livro = fixtures.criaLivroRefatoracao();
        var uniLivro = Uni.createFrom().item(livro);
        Mockito.when(repositorioLivro.pegaLivroPeloNome(livro.nome)).thenReturn(uniLivro);

        var funcionario = new Funcionario(new Matricula("F0741372"));
        var uniFuncionario = Uni.createFrom().item(funcionario);
        Mockito.when(funcionarioRepository.pegaFuncionarioPorMatricula(funcionario.matricula())).thenReturn(uniFuncionario);

        comandoPegarLivroEmprestado = new ComandoPegarLivroEmprestado("Refatoração", "F0741372");
        comandoDevolverLivroEmprestado = new ComandoDevolverLivroEmprestado("Refatoração", "F0741372");
    }

    @TestHTTPResource("/funcionario/pegalivro")
    URL pegaLivroEndpoint;
    @Test
    void pegaLivro() {
        given().contentType(ContentType.JSON).body(comandoPegarLivroEmprestado)
                .when().post(pegaLivroEndpoint)
                .then().statusCode(204);
    }

    @TestHTTPResource("/funcionario/devolvelivro")
    URL devolveLivroEndpoint;
    @Test
    void devolveLivro() {
        given().contentType(ContentType.JSON).body(comandoDevolverLivroEmprestado)
                .when().post(devolveLivroEndpoint)
                .then().statusCode(204);
    }
}