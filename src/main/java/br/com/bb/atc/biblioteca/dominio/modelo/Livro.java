package br.com.bb.atc.biblioteca.dominio.modelo;

import javax.persistence.Entity;

@Entity
public class Livro extends Entidade {

    public String nome;
    public String idioma;
    public String autor;
    public String edicao;
    public String editora;
    public String isbn;
    public boolean estaDisponivel;

    public Livro() {
    }

    public Livro(String umNome,
                 String umIdioma,
                 String umAutor,
                 String umaEdicao,
                 String umaEditora,
                 String umIsbn) {
        this();
        this.setNome(umNome);
        this.setIdioma(umIdioma);
        this.setAutor(umAutor);
        this.setEdicao(umaEdicao);
        this.setEditora(umaEditora);
        this.setIsbn(umIsbn);
        this.estaDisponivel = true;
    }

    public void ficaIndisponivel() {
        if (this.estaDisponivel) {
            this.estaDisponivel = false;
        }
    }

    public void ficaDisponivel() {
        if (!this.estaDisponivel) {
            this.estaDisponivel = true;
        }
    }

    private void setNome(String umNome) {
        assertArgumentNotNull(umNome, "Nome do Livro deve ser informado.");
        assertArgumentNotEmpty(umNome, "Nome do Livro não pode estar vazio.");
        this.nome = umNome;
    }

    private void setIdioma(String umIdioma) {
        assertArgumentNotNull(umIdioma, "Idioma do Livro deve ser informado.");
        assertArgumentNotEmpty(umIdioma, "Idioma do Livro não pode estar vazio.");
        this.idioma = umIdioma;
    }

    private void setAutor(String umAutor) {
        assertArgumentNotNull(umAutor, "Autor do Livro deve ser informado.");
        assertArgumentNotEmpty(umAutor, "Autor do Livro não pode estar vazio.");
        this.autor = umAutor;
    }

    private void setEdicao(String umaEdicao) {
        assertArgumentNotNull(umaEdicao, "Edição do Livro deve ser informado.");
        assertArgumentNotEmpty(umaEdicao, "Edição do Livro não pode estar vazio.");
        this.edicao = umaEdicao;
    }

    private void setEditora(String umaEditora) {
        assertArgumentNotNull(umaEditora, "Editora do Livro deve ser informado.");
        assertArgumentNotEmpty(umaEditora, "Editora do Livro não pode estar vazio.");
        this.editora = umaEditora;
    }

    private void setIsbn(String umIsbn) {
        assertArgumentNotNull(umIsbn, "ISBN do Livro deve ser informado.");
        assertArgumentNotEmpty(umIsbn, "ISBN do Livro não pode estar vazio.");
        this.isbn = umIsbn;
    }
}
