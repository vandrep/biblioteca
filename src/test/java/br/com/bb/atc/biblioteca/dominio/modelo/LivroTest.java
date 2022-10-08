package br.com.bb.atc.biblioteca.dominio.modelo;

import br.com.bb.atc.biblioteca.Fixtures;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class LivroTest {
    @Inject
    Fixtures fixtures;
    Livro umLivro;
    String umFuncionario;
    String outroFuncionario;

    @BeforeEach
    void setUp() {
        umLivro = fixtures.criaLivroRefatoracao();
        umFuncionario = fixtures.criaFuncionarioAleatorio();
        outroFuncionario = fixtures.criaFuncionarioAleatorio();
        while(outroFuncionario.equals(umFuncionario)){
            outroFuncionario = fixtures.criaFuncionarioAleatorio();
        }
    }

    @Test
    void emprestaLivroDisponivel() {
        umLivro.emprestaPara(umFuncionario);

        assertFalse(umLivro.estaDisponivel);
        assertTrue(umLivro.funcionarioLendo.equalsIgnoreCase(umFuncionario));
    }
    @Test
    void emprestaLivroIndisponivelRetornaErro() {
        umLivro.emprestaPara(umFuncionario);
        assertFalse(umLivro.estaDisponivel);
        assertTrue(umLivro.funcionarioLendo.equalsIgnoreCase(umFuncionario));

        var erro = assertThrows(IllegalStateException.class, () -> umLivro.emprestaPara(umFuncionario));
        assertEquals("Livro não está disponível.", erro.getMessage());
    }
    @Test
    void emprestaLivroParaFuncionarioComMatriculaIncorreta() {
        var umFuncionarioRuim = "f074137";
        var erro = assertThrows(IllegalArgumentException.class, () -> umLivro.emprestaPara(umFuncionarioRuim));
    }
    @Test
    void devolveLivroEmprestado() {
        umLivro.emprestaPara(umFuncionario);
        assertFalse(umLivro.estaDisponivel);
        assertTrue(umLivro.funcionarioLendo.equalsIgnoreCase(umFuncionario));

        umLivro.devolvePor(umFuncionario);
        assertTrue(umLivro.estaDisponivel);
        assertNull(umLivro.funcionarioLendo);
    }
    @Test
    void devolveLivroNaoEmprestadoRetornaErro() {
        var erro = assertThrows(IllegalStateException.class, () -> umLivro.devolvePor(umFuncionario));
        assertEquals("Livro não está emprestado.", erro.getMessage());
    }

    @Test
    void devolveLivroEmprestadoParaOutroFuncionarioRetornaErro() {
        umLivro.emprestaPara(umFuncionario);
        assertFalse(umLivro.estaDisponivel);
        assertTrue(umLivro.funcionarioLendo.equalsIgnoreCase(umFuncionario));

        var outroFuncionario = "F1234567";
        var erro = assertThrows(IllegalArgumentException.class, () -> umLivro.devolvePor(outroFuncionario));
        assertEquals("Funcionário informado não está lendo este livro.", erro.getMessage());
    }
}