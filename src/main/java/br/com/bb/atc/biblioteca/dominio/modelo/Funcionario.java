package br.com.bb.atc.biblioteca.dominio.modelo;

import io.smallrye.mutiny.Uni;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Funcionario extends Entidade {

    @Embedded
    public Matricula matricula;
    public boolean temLivroEmprestado;

    public Funcionario() {

    }

    public Funcionario(Matricula umMatricula) {
        this();
        this.temLivroEmprestado = false;
        this.setMatriculaFuncionario(umMatricula);
    }

    public String matricula(){
        return this.matricula.matricula();
    }

    public Uni<Void> pegaLivro(Livro livro) {
        if (this.podePegarLivro()) {
            this.temLivroEmprestado = true;
            livro.ficaIndisponivel();
        }
        return Uni.createFrom().voidItem();
    }

    public Uni<Void> devolveLivro(Livro livro) {
        if (this.temLivroEmprestado) {
            this.temLivroEmprestado = false;
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
