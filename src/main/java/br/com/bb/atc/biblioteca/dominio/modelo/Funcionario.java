package br.com.bb.atc.biblioteca.dominio.modelo;

import io.smallrye.mutiny.Uni;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Funcionario extends Entidade {

    @Embedded
    private Matricula matricula;
    private boolean temLivroEmprestado;

    public Funcionario() {
        this.temLivroEmprestado = false;
    }

    public Funcionario(Matricula umMatricula) {
        this();
        this.setMatriculaFuncionario(umMatricula);
    }

    public String matricula(){
        return this.matricula.matricula();
    }

    public Uni<Void> pegaLivro(Livro livro) {
        if (this.podePegarLivro()) {
            temLivroEmprestado = true;
            livro.ficaIndisponivel();
        }
        return Uni.createFrom().voidItem();
    }

    public Uni<Void> devolveLivro(Livro livro) {
        if (this.temLivroEmprestado) {
            temLivroEmprestado = false;
            livro.ficaDisponivel();
        }
        return Uni.createFrom().voidItem();
    }

    public boolean podePegarLivro() {
        return !temLivroEmprestado;
    }

    private void setMatriculaFuncionario(Matricula umMatricula) {
        assertArgumentNotNull(umMatricula, "Matricula do Funcionário deve ser informada.");
        this.matricula = umMatricula;
    }
}
