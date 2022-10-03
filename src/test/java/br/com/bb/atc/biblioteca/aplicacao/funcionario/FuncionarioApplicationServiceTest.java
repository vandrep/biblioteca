package br.com.bb.atc.biblioteca.aplicacao.funcionario;

import br.com.bb.atc.biblioteca.dominio.modelo.Funcionario;
import br.com.bb.atc.biblioteca.dominio.modelo.Matricula;
import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import br.com.bb.atc.biblioteca.porta.adaptador.persistencia.OracleDBFuncionarioRepository;
import br.com.bb.atc.biblioteca.porta.adaptador.persistencia.OracleDBLivroRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class FuncionarioApplicationServiceTest {

    @InjectMock
    OracleDBLivroRepository livroRepository;

    @InjectMock
    OracleDBFuncionarioRepository funcionarioRepository;

    @Inject
    FuncionarioApplicationService funcionarioApplicationService;

    Funcionario funcionario;
    Livro livro;

    @BeforeEach
    void criaFixtures() {
        livro = new Livro("Refatoração");
        var uniLivro = Uni.createFrom().item(livro);
        Mockito.when(livroRepository.pegaLivroPeloNome(livro.nome())).thenReturn(uniLivro);

        funcionario = new Funcionario(new Matricula("F0741372"));
        var uniFuncionario = Uni.createFrom().item(funcionario);
        Mockito.when(funcionarioRepository.pegaFuncionarioPorMatricula(funcionario.matricula())).thenReturn(uniFuncionario);
    }

    @Test
    void pegarLivroEmprestado() {
        var comando = new ComandoPegarLivroEmprestado(livro.nome(), funcionario.matricula());
        funcionarioApplicationService.pegarLivroEmprestado(comando).await().indefinitely();

        assertFalse(livroRepository.pegaLivroPeloNome(livro.nome()).await().indefinitely().estaDisponivel());
        assertFalse(funcionarioRepository.pegaFuncionarioPorMatricula(funcionario.matricula()).await().indefinitely().podePegarLivro());
    }

    @Test
    void devolverLivroEmprestado() {
        var comando = new ComandoDevolverLivroEmprestado(livro.nome(), funcionario.matricula());
        funcionarioApplicationService.devolverLivroEmprestado(comando).await().indefinitely();

        assertTrue(livroRepository.pegaLivroPeloNome(livro.nome()).await().indefinitely().estaDisponivel());
        assertTrue(funcionarioRepository.pegaFuncionarioPorMatricula(funcionario.matricula()).await().indefinitely().podePegarLivro());
    }
}
