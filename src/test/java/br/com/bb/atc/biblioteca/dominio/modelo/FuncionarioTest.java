package br.com.bb.atc.biblioteca.dominio.modelo;

import br.com.bb.atc.biblioteca.Fixtures;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class FuncionarioTest {
    @Inject
    Fixtures fixtures;
    Funcionario funcionario;
    Livro livro;

    @BeforeEach
    void criaFixtures() {
        funcionario = new Funcionario(new Matricula("F0741372"));
        livro = fixtures.criaLivroRefatoracao();
    }

    @Test
    void pegarLivroPodendoPegarLivro() {
        funcionario.pegaLivro(livro);

        assertFalse(funcionario.podePegarLivro());
        assertFalse(livro.estaDisponivel);
    }

    @Test
    void devolveLivroTendoLivroEmprestado() {
        funcionario.pegaLivro(livro);

        funcionario.devolveLivro(livro);

        assertTrue(funcionario.podePegarLivro());
        assertTrue(livro.estaDisponivel);
    }
}
