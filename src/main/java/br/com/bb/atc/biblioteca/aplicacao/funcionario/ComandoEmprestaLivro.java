package br.com.bb.atc.biblioteca.aplicacao.funcionario;

public class ComandoEmprestaLivro {
    public String nomeLivro;
    public String matriculaFuncionario;

    public ComandoEmprestaLivro(String umNomeLivro, String umaMatriculaFuncionario){
        this.nomeLivro = umNomeLivro;
        this.matriculaFuncionario = umaMatriculaFuncionario;
    }
}
