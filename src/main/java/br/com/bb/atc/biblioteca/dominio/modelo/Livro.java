package br.com.bb.atc.biblioteca.dominio.modelo;

import javax.persistence.Entity;

@Entity
public class Livro extends Entidade {

    private String nome;
    private boolean disponibilidade;

    public Livro() {
        this.disponibilidade = true;
    }

    public Livro(String umNome) {
        this();
        this.setNome(umNome);
    }

    private void setNome(String umNome) {
        assertArgumentNotNull(umNome, "Nome do Livro deve ser informado.");
        assertArgumentNotEmpty(umNome, "Nome do Livro não pode estar vazio.");
        this.nome = umNome;
    }

    public void ficaIndisponivel() {
        if (this.estaDisponivel()) {
            this.disponibilidade = false;
        }
    }

    public void ficaDisponivel() {
        if (!this.estaDisponivel()) {
            this.disponibilidade = true;
        }
    }

    public boolean estaDisponivel() {
        return this.disponibilidade;
    }

    public String nome() {
        return this.nome;
    }
}
