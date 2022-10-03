package br.com.bb.atc.biblioteca.dominio.modelo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class LivroTest {
    Livro livro;

    @BeforeEach
    void criaFixtures() {
        livro = new Livro();
    }

    @Test
    void tentaFicarIndisponivel() {
        livro.ficaIndisponivel();

        assertFalse(livro.estaDisponivel());
    }

    @Test
    void tentaFicarDisponivel() {
        livro.ficaIndisponivel();
        assertFalse(livro.estaDisponivel());

        livro.ficaDisponivel();
        assertTrue(livro.estaDisponivel());
    }
}