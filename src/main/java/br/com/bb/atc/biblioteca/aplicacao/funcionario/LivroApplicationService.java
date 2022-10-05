package br.com.bb.atc.biblioteca.aplicacao.funcionario;

import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class LivroApplicationService {

    public Uni<List<Livro>> listaTodosLivros() {
        return Livro.listAll();
    }

    public Uni<Void> incluiLivro(ComandoIncluirLivro umComando) {
        var livro = new Livro(umComando.nomeLivro,
                umComando.idioma,
                umComando.autor,
                umComando.edicao,
                umComando.editora,
                umComando.isbn);

        return livro.persist().replaceWithVoid();
//        return livroRepository.persist(
//                        new Livro(umComando.nomeLivro,
//                                  umComando.idioma,
//                                  umComando.autor,
//                                  umComando.edicao,
//                                  umComando.editora,
//                                  umComando.isbn))
//                .replaceWithVoid();
    }
}
