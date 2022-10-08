package br.com.bb.atc.biblioteca.aplicacao.funcionario;

public class ComandoDevolveLivro {
    public String nomeLivro;
    public String matriculaFuncionario;

    public ComandoDevolveLivro(String umNomeLivro, String umaMatriculaFuncionario) {
        this.nomeLivro = umNomeLivro;
        this.matriculaFuncionario = umaMatriculaFuncionario;
    }
}
