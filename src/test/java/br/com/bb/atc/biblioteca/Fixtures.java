package br.com.bb.atc.biblioteca;

import br.com.bb.atc.biblioteca.dominio.modelo.Livro;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;

@ApplicationScoped
public class Fixtures {

    @Produces
    public Livro criaLivroRefatoracao(){
        return new Livro("Refatoração",
                "Português",
                "Martin Fowler",
                "Segunda Edição",
                "Novatec",
                "978-85-7522-724-4");
    }
}
