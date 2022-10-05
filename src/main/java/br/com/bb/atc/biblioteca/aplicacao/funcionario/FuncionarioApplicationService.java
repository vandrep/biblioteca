package br.com.bb.atc.biblioteca.aplicacao.funcionario;

import br.com.bb.atc.biblioteca.dominio.modelo.FuncionarioRepository;
import br.com.bb.atc.biblioteca.dominio.modelo.LivroRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FuncionarioApplicationService {
    @Inject
    LivroRepository livroRepository;
    @Inject
    FuncionarioRepository funcionarioRepository;

    public Uni<Void> pegarLivroEmprestado(ComandoPegarLivroEmprestado umComando) {
        return livroRepository.pegaLivroPeloNome(umComando.nomeLivro)
                .chain(livro -> funcionarioRepository.pegaFuncionarioPorMatricula(umComando.matriculaFuncionario)
                        .map(funcionario -> funcionario.pegaLivro(livro)
                                .invoke(() -> livroRepository.salva(livro))
                                .invoke(() -> funcionarioRepository.salva(funcionario))))
                .replaceWithVoid();
    }

    public Uni<Void> devolverLivroEmprestado(ComandoDevolverLivroEmprestado umComando) {
        return livroRepository.pegaLivroPeloNome(umComando.nomeLivro)
                .chain(livro -> funcionarioRepository.pegaFuncionarioPorMatricula(umComando.matriculaFuncionario)
                        .map(funcionario -> funcionario.devolveLivro(livro)
                                .invoke(() -> livroRepository.salva(livro))
                                .invoke(() -> funcionarioRepository.salva(funcionario))))
                .replaceWithVoid();
    }
}
