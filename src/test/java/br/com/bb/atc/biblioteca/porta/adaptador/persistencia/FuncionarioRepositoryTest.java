//package br.com.bb.atc.biblioteca.porta.adaptador.persistencia;
//
//import br.com.bb.atc.biblioteca.dominio.modelo.Funcionario;
//import br.com.bb.atc.biblioteca.dominio.modelo.Matricula;
//import io.quarkus.arc.Priority;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Alternative;
//
//@Priority(1)
//@Alternative
//@ApplicationScoped
//public class FuncionarioRepositoryTest extends OracleDBFuncionarioRepository {
//
//    @PostConstruct
//    public void init() {
//        persist(new Funcionario(new Matricula("F0741372")),
//                new Funcionario(new Matricula("F1234567")))
//                        .subscribe().with(
//                        item -> System.out.println("OK"),
//                        fail -> System.out.println("FALHA"));
//    }
//}
