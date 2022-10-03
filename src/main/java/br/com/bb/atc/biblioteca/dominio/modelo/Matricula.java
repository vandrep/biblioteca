package br.com.bb.atc.biblioteca.dominio.modelo;

public class Matricula extends ObjetoDeValor {
    private String matricula;

    public Matricula(){}

    public Matricula(String matricula) {
        this.setMatricula(matricula);
    }

    public String matricula() {
        return matricula;
    }

    private void setMatricula(String umaMatricula) {
        String PADRAO_MATRICULA_FUNCI = "^F(\\d{7})$";
        assertArgumentNotNull(umaMatricula, "Matrícula deve ser informada.");
        assertArgumentNotEmpty(umaMatricula, "Matrícula não pode estar vazia.");
        assertPatternEquals(umaMatricula, PADRAO_MATRICULA_FUNCI, "Matrícula fora do padrão");
        this.matricula = umaMatricula.toUpperCase();
    }
}
