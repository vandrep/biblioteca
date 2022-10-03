package br.com.bb.atc.biblioteca.dominio.modelo;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

public interface FuncionarioRepository extends PanacheRepository<Funcionario> {
    Uni<Funcionario> pegaFuncionarioPorMatricula(String matriculaFuncionario);
    Uni<Void> salva(Funcionario funcionario);
}
