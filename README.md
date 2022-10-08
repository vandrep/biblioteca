# biblioteca Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

O intuito inicial deste projeto era de fornecer uma API simplificada para gerenciar Empréstimos de Livros dentro de uma 
Empresa.

Posteriormente, pensamos em usar como uma base de boas práticas para consultas.
Não queremos afirmar com isso que as nossas práticas são as melhores, apenas que foram feitas visando técnicas 
publicadas por autores consagrados.

O projeto, porém, não está completo. Logo abaixo apresentamos uma lista de itens que consideramos como fundamentais e 
que ainda não foram implementados.

* Implementar Casos de Testes no nível dos Adaptadores;
* Finalizar Casos de Testes no nível da Aplicação;
* Enriquecer o Modelo de Domínio incluindo Tipos Padrão;
* Lidar com a persistência para mapear corretamente os Tipos Padrão;
* Enriquecer as Validações de campos como o ISBN;
* Implementar os casos usos abaixo:
  * Remover Livro;
  * Duplicar Livro (facilitando a inclusão de um novo exemplar);
  * Consultas variadas para utilização da UI.


* Finalmente, deixamos aqui algumas implementações mais estruturantes que foram planejadas:
  * Implementar a Segurança da API;
  * Criar novo Agregado para representar as Opiniões dos Leitores;
  * Criar novo Agregado para representar Sugestões de Novos Livros;
  * Elaborar um novo Contexto Delimitado que aborde a Gamificação da Leitura.

Esperamos poder voltar em breve para retomar esse projeto.

Referências que serviram de Apoio para este Projeto:
* Implementando Domain-Driven Design - Vaughn Vernon
* Arquitetura Limpa - O Guia do Artesão para Estrutura e Design de Software - Uncle Bob
* Código Limpo - Habilidades Práticas do Agile Software - Uncle Bob
* Refatoração - Aperfeiçoando o Design de Códigos Existentes - Martin Fowler
* https://quarkus.io/guides/
  * https://quarkus.io/guides/hibernate-orm
  * https://quarkus.io/guides/hibernate-orm-panache
  * https://quarkus.io/guides/getting-started-reactive
  * https://quarkus.io/guides/mutiny-primer
  * https://quarkus.io/guides/resteasy-reactive
  * https://quarkus.io/guides/resteasy-reactive-migration
  * https://quarkus.io/guides/reactive-sql-clients
  * https://quarkus.io/guides/hibernate-reactive
  * https://quarkus.io/guides/hibernate-reactive-panache
  * https://quarkus.io/guides/quarkus-reactive-architecture
* https://stackoverflow.com/
* https://smallrye.io/smallrye-mutiny/1.7.0/

## Running the application in dev mode

Para rodar essa Aplicação é necessário ter um ambiente de container configurado. Durante o desenvolvimento foi utiilzado
o Podman:

https://podman.io/

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/biblioteca-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
