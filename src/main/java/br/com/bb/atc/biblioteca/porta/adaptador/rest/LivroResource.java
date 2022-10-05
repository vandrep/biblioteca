package br.com.bb.atc.biblioteca.porta.adaptador.rest;

import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoIncluirLivro;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.LivroApplicationService;
import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/livro")
public class LivroResource {

    @Inject
    LivroApplicationService livroApplicationService;

    @GET
    @Path("/listaLivros")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Livro>> listaTodosLivros() {
        return livroApplicationService.listaTodosLivros();
    }

    @POST
    @Path("/incluiLivro")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> incluiLivro(ComandoIncluirLivro umComandoIncluirLivro) {
        return livroApplicationService.incluiLivro(umComandoIncluirLivro);
    }
}
