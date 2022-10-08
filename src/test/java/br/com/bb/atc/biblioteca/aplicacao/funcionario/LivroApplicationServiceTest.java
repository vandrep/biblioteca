package br.com.bb.atc.biblioteca.aplicacao.funcionario;

import br.com.bb.atc.biblioteca.Fixtures;
import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import br.com.bb.atc.biblioteca.porta.adaptador.persistencia.RepositorioLivroOracle;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class LivroApplicationServiceTest {
    int QUANTIDADE_LIVROS_PARA_LISTAR = 10;

    @Inject
    Fixtures fixtures;
    @InjectMock
    RepositorioLivroOracle repositorioLivroOracle;

    @Inject
    LivroApplicationService livroApplicationService;

    Livro livro;
    Uni<Livro> uniLivro;
    String funcionario;
    ComandoDevolveLivro comandoDevolveLivro;
    ComandoEmprestaLivro comandoEmprestaLivro;
    ComandoIncluiLivro comandoIncluiLivro;

    @BeforeEach
    void setUp() {
        livro = fixtures.criaLivroAleatorio();
        uniLivro = Uni.createFrom().item(livro);
        funcionario = fixtures.criaFuncionarioAleatorio();
        comandoDevolveLivro = new ComandoDevolveLivro(livro.nome, funcionario);
        comandoEmprestaLivro = new ComandoEmprestaLivro(livro.nome, funcionario);
        comandoIncluiLivro = new ComandoIncluiLivro(livro.nome, livro.idioma, livro.autor, livro.edicao, livro.editora, livro.isbn);
    }

    @Test
    void listaTodosLivrosComSucesso() {
        Mockito.when(repositorioLivroOracle.pegaTodosLivros()).thenReturn(fixtures.criaLivrosAleatorios(QUANTIDADE_LIVROS_PARA_LISTAR));

        var livros = livroApplicationService.listaTodosLivros().collect().asList().await().indefinitely();

        assertEquals(QUANTIDADE_LIVROS_PARA_LISTAR, livros.size());
        livros.forEach(livro -> {
            assertNotNull(livro.nome);
            assertNotNull(livro.idioma);
            assertNotNull(livro.autor);
            assertNotNull(livro.edicao);
            assertNotNull(livro.editora);
            assertNotNull(livro.isbn);
            assertNull(livro.funcionarioLendo);
            assertTrue(livro.estaDisponivel);
        });
    }

    @Test
    void incluiLivroComSucesso() {
        Mockito.when(repositorioLivroOracle.salva(livro)).thenReturn(Uni.createFrom().voidItem());

        assertDoesNotThrow(() -> livroApplicationService.incluiLivro(comandoIncluiLivro).await().indefinitely());
    }

    @Test
    void incluiLivroFaltandoCampoRetornaErro() {
        Mockito.when(repositorioLivroOracle.salva(livro)).thenReturn(Uni.createFrom().voidItem());
        comandoIncluiLivro.autor = null;

        var erro = assertThrows(IllegalArgumentException.class, () -> livroApplicationService.incluiLivro(comandoIncluiLivro).await().indefinitely());
        assertEquals("Autor do Livro deve ser informado.", erro.getMessage());
    }

    @Test
    void emprestaComSucesso() {
        Mockito.when(repositorioLivroOracle.persist(livro)).thenReturn(Uni.createFrom().item(livro));
        Mockito.when(repositorioLivroOracle.buscaLivroDisponivelPeloNome(livro.nome)).thenReturn(uniLivro);

        assertDoesNotThrow(() -> livroApplicationService.empresta(comandoEmprestaLivro).await().indefinitely());
    }

    @Test
    void emprestaLivroInexistenteRetornaErro() {
        Mockito.when(repositorioLivroOracle.buscaLivroDisponivelPeloNome(any())).thenReturn(Uni.createFrom().nullItem());

        var erro = assertThrows(NotFoundException.class, () -> livroApplicationService.empresta(comandoEmprestaLivro).await().indefinitely());
        assertEquals("Livro não encontrado.", erro.getMessage());
    }

    @Test
    void devolveComSucesso() {
        Mockito.when(repositorioLivroOracle.persist(livro)).thenReturn(Uni.createFrom().item(livro));
        Mockito.when(repositorioLivroOracle.buscaLivroDisponivelPeloNome(livro.nome)).thenReturn(uniLivro);
        livro.emprestaPara(funcionario);

        assertDoesNotThrow(() -> livroApplicationService.devolve(comandoDevolveLivro).await().indefinitely());
    }
}