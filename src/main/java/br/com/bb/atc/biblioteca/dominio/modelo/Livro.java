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
    public String funcionarioLendo;
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

    public void emprestaPara(String umFuncionario) {
        if (this.estaDisponivel) {
            this.estaDisponivel = false;
            this.setFuncionarioLendo(umFuncionario);
        } else {
            throw new IllegalStateException("Livro não está disponível.");
        }
    }

    public void devolvePor(String umFuncionario) {
        if (this.estaDisponivel) {
            throw new IllegalStateException("Livro não está emprestado.");
        }
        if (!this.funcionarioLendo.equalsIgnoreCase(umFuncionario)) {
            throw new IllegalArgumentException("Funcionário informado não está lendo este livro.");
        }
        this.estaDisponivel = true;
        this.setFuncionarioLendo(null);
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

    private void setFuncionarioLendo(String umFuncionario) {
        if (umFuncionario != null) {
            String PADRAO_MATRICULA_FUNCI = "^F(\\d{7})$";
            assertArgumentNotEmpty(umFuncionario, "Matrícula não pode estar vazia.");
            assertPatternEqualsIgnoreCase(umFuncionario, PADRAO_MATRICULA_FUNCI, "Matrícula fora do padrão");
            this.funcionarioLendo = umFuncionario.toUpperCase();
        } else {
            this.funcionarioLendo = null;
        }
    }
}
