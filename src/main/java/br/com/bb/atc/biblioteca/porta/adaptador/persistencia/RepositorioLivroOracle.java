package br.com.bb.atc.biblioteca.porta.adaptador.persistencia;

import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import br.com.bb.atc.biblioteca.dominio.modelo.RepositorioLivro;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class RepositorioLivroOracle implements RepositorioLivro {

    @Override
    public Uni<Livro> pegaLivroPeloNome(String umNome) {
        return find("nome", umNome).firstResult();
    }

    @Override
    public Uni<Livro> buscaLivroDisponivelPeloNome(String umNome){
        return find("nome = ?1 and estadisponivel = true", umNome).firstResult();
    }

    @Override
    public Uni<Void> salva(Livro livro) {
        return this.persist(livro).replaceWithVoid();
    }

    @Override
    public Multi<Livro> pegaTodosLivros(){
        return findAll().stream();
    }
}
