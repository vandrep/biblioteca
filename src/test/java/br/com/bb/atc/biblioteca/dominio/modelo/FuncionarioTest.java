package br.com.bb.atc.biblioteca.dominio.modelo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class FuncionarioTest {
    Funcionario funcionario;
    Livro livro;

    @BeforeEach
    void criaFixtures() {
        funcionario = new Funcionario(new Matricula("F0741372"));
        livro = new Livro();
    }

    @Test
    void pegarLivroPodendoPegarLivro() {
        funcionario.pegaLivro(livro);

        assertFalse(funcionario.podePegarLivro());
        assertFalse(livro.estaDisponivel());
    }

    @Test
    void devolveLivroTendoLivroEmprestado() {
        funcionario.pegaLivro(livro);

        funcionario.devolveLivro(livro);

        assertTrue(funcionario.podePegarLivro());
        assertTrue(livro.estaDisponivel());
    }
}
