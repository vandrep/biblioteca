package br.com.bb.atc.biblioteca.dominio.modelo;

import br.com.bb.atc.biblioteca.Fixtures;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class LivroTest {
    @Inject
    Fixtures fixtures;
    Livro livro;

    @BeforeEach
    void criaFixtures() {
        livro = fixtures.criaLivroRefatoracao();
    }

    @Test
    void tentaFicarIndisponivel() {
        livro.ficaIndisponivel();

        assertFalse(livro.estaDisponivel);
    }

    @Test
    void tentaFicarDisponivel() {
        livro.ficaIndisponivel();
        assertFalse(livro.estaDisponivel);

        livro.ficaDisponivel();
        assertTrue(livro.estaDisponivel);
    }
}