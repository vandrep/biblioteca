//package br.com.bb.atc.biblioteca.porta.adaptador.persistencia;
//
//import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
//import io.quarkus.arc.Priority;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Alternative;
//
//@Priority(1)
//@Alternative
//@ApplicationScoped
//public class LivroRepositoryTest extends OracleDBLivroRepository {
//
//    @PostConstruct
//    public void init() {
//        persist(new Livro("Implementando Domain-Driven-Design"),
//                new Livro("Refatoração")).subscribe().with(
//                item -> System.out.println("OK"),
//                fail -> System.out.println("FALHA"));
//    }
//}
