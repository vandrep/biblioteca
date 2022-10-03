package br.com.bb.atc.biblioteca.aplicacao.funcionario;

public class ComandoDevolverLivroEmprestado {
    public final String nomeLivro;
    public final String matriculaFuncionario;

    public ComandoDevolverLivroEmprestado(String umNomeLivro, String umaMatriculaFuncionario){
        this.nomeLivro = umNomeLivro;
        this.matriculaFuncionario = umaMatriculaFuncionario;
    }
}
