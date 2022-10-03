package br.com.bb.atc.biblioteca.porta.adaptador.persistencia;

import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import br.com.bb.atc.biblioteca.dominio.modelo.LivroRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.LockModeType;

@ApplicationScoped
public class OracleDBLivroRepository implements LivroRepository {

    @Override
    public Uni<Livro> pegaLivroPeloNome(String umNome) {
        return find("nome", umNome).firstResult();
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
