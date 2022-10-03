package br.com.bb.atc.biblioteca.aplicacao.funcionario;

public class ComandoPegarLivroEmprestado {
    public final String nomeLivro;
    public final String matriculaFuncionario;

    public ComandoPegarLivroEmprestado(String umNomeLivro, String umaMatriculaFuncionario){
        this.nomeLivro = umNomeLivro;
        this.matriculaFuncionario = umaMatriculaFuncionario;
    }
}
