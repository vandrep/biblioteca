package br.com.bb.atc.biblioteca.porta.adaptador.rest;

import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoDevolveLivro;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoEmprestaLivro;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoIncluiLivro;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.LivroApplicationService;
import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/livro")
public class LivroWeb {

    @Inject
    LivroApplicationService livroApplicationService;

    @GET
    @Path("/listaLivros")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Livro> listaTodosLivros() {
        return livroApplicationService.listaTodosLivros();
    }

    @POST
    @Path("/incluiLivro")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> incluiLivro(ComandoIncluiLivro umComandoIncluiLivro) {
        return livroApplicationService.incluiLivro(umComandoIncluiLivro);
    }

    @POST
    @Path("/emprestar")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> emprestaPara(ComandoEmprestaLivro umComando) {
        return livroApplicationService.empresta(umComando);
    }

    @POST
    @Path("/devolver")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> devolvePor(ComandoDevolveLivro umComandoIncluirLivro) {
        return livroApplicationService.devolve(umComandoIncluirLivro);
    }
}
