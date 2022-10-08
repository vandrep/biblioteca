package br.com.bb.atc.biblioteca.dominio.modelo;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface RepositorioLivro extends PanacheRepository<Livro> {

    Uni<Livro> pegaLivroPeloNome(String umNome);

    Uni<Livro> buscaLivroDisponivelPeloNome(String umNome);

    Uni<Void> salva(Livro livro);

    Multi<Livro> pegaTodosLivros();
}
