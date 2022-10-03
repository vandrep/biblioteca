package br.com.bb.atc.biblioteca.porta.adaptador.persistencia;

import br.com.bb.atc.biblioteca.dominio.modelo.Funcionario;
import br.com.bb.atc.biblioteca.dominio.modelo.FuncionarioRepository;
import br.com.bb.atc.biblioteca.dominio.modelo.Matricula;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.LockModeType;

@ApplicationScoped
public class OracleDBFuncionarioRepository implements FuncionarioRepository {

    public Uni<Funcionario> pegaFuncionarioPorMatricula(String umaMatricula) {
        return find("matricula", new Matricula(umaMatricula)).firstResult();
    }

    @Override
    public Uni<Void> salva(Funcionario funcionario) {
        return this.persist(funcionario).replaceWithVoid();
    }
}
