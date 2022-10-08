package br.com.bb.atc.biblioteca;

import br.com.bb.atc.biblioteca.dominio.modelo.Livro;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.stream.IntStream;

@ApplicationScoped
public class Fixtures {

    public Livro criaLivroRefatoracao() {
        return new Livro("Refatoração",
                "Português",
                "Martin Fowler",
                "Segunda Edição",
                "Novatec",
                "978-85-7522-724-4");
    }

    public Multi<Livro> criaLivrosAleatorios(int quantidade){
        return Multi.createFrom().items(IntStream.range(0, quantidade)
                .mapToObj(i -> criaLivroAleatorio()));
    }

    public Livro criaLivroAleatorio(){
        return new Livro(criaStringAleatoria(10),
                criaStringAleatoria(10),
                criaStringAleatoria(10),
                criaStringAleatoria(10),
                criaStringAleatoria(10),
                criaStringAleatoria(10));
    }

    public String criaStringAleatoria(int tamanhoString){
        int limiteEsquerda = 97; // letter 'a'
        int limiteDireira = 122; // letter 'z'
        Random random = new Random();

        return random.ints(limiteEsquerda, limiteDireira + 1)
                .limit(tamanhoString)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String criaFuncionarioAleatorio() {
        Random rand = new Random();
        var caracterMaiusculoOuMinusculo = rand.nextBoolean() ? "F" : "f";
        return String.format(caracterMaiusculoOuMinusculo + "%07d", rand.nextInt(10000000));
    }
}
