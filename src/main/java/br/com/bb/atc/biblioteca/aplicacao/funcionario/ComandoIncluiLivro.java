package br.com.bb.atc.biblioteca.aplicacao.funcionario;

public class ComandoIncluiLivro {
    public String nomeLivro;
    public String idioma;
    public String autor;
    public String edicao;
    public String editora;
    public String isbn;

    public ComandoIncluiLivro(String nomeLivro,
                              String idioma,
                              String autor,
                              String edicao,
                              String editora,
                              String isbn) {
        this.nomeLivro = nomeLivro;
        this.idioma = idioma;
        this.autor = autor;
        this.edicao = edicao;
        this.editora = editora;
        this.isbn = isbn;
    }
}
