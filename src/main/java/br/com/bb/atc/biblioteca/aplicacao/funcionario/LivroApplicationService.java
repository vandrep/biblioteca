package br.com.bb.atc.biblioteca.aplicacao.funcionario;

import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import br.com.bb.atc.biblioteca.dominio.modelo.RepositorioLivro;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class LivroApplicationService {

    @Inject
    RepositorioLivro repositorioLivro;

    public Multi<Livro> listaTodosLivros() {
        return repositorioLivro.pegaTodosLivros();
    }

    public Uni<Void> incluiLivro(ComandoIncluiLivro umComando) {
        var livro = new Livro(umComando.nomeLivro,
                umComando.idioma,
                umComando.autor,
                umComando.edicao,
                umComando.editora,
                umComando.isbn);

        return livro.persist().replaceWithVoid();
    }

    public Uni<Void> empresta(ComandoEmprestaLivro umComando) {
        return repositorioLivro.buscaLivroDisponivelPeloNome(umComando.nomeLivro)
                .onItem().ifNull().failWith(new NotFoundException("Livro não encontrado."))
                .invoke(livro -> livro.emprestaPara(umComando.matriculaFuncionario))
                .chain(livro -> repositorioLivro.persist(livro))
                .replaceWithVoid();
    }

    public Uni<Void> devolve(ComandoDevolveLivro umComando) {
        return repositorioLivro.buscaLivroDisponivelPeloNome(umComando.nomeLivro)
                .onItem().ifNull().failWith(new NotFoundException("Livro não encontrado."))
                .invoke(livro -> livro.devolvePor(umComando.matriculaFuncionario))
                .chain(livro -> repositorioLivro.persist(livro))
                .replaceWithVoid();
    }
}
