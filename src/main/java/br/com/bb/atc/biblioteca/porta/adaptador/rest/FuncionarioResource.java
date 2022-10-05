package br.com.bb.atc.biblioteca.porta.adaptador.rest;

import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoDevolverLivroEmprestado;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.FuncionarioApplicationService;
import br.com.bb.atc.biblioteca.aplicacao.funcionario.ComandoPegarLivroEmprestado;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/funcionario")
public class FuncionarioResource {

    @Inject
    FuncionarioApplicationService funcionarioApplicationService;

    @POST
    @Path("/pegalivro")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> pegaLivro(ComandoPegarLivroEmprestado umComandoPegarLivroEmprestado) {
        return funcionarioApplicationService.pegarLivroEmprestado(umComandoPegarLivroEmprestado);
    }

    @POST
    @Path("/devolvelivro")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> devolveLivro(ComandoDevolverLivroEmprestado umComandoDevolverLivroEmprestado) {
        return funcionarioApplicationService.devolverLivroEmprestado(umComandoDevolverLivroEmprestado);
    }
}
