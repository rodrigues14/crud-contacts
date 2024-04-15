# CRUD de Contatos usando Spring Boot

Este projeto implementa um CRUD (Create, Read, Update, Delete) básico para gerenciar contatos usando Spring Boot.

## Funcionalidades

- Cadastro de novos contatos.
- Listagem de todos os contatos cadastrados.
- Atualização de informações de um contato existente.
- Exclusão de um contato.

## Como Executar

1. Certifique-se de ter o JDK 8 ou superior instalado em sua máquina.
2. Clone este repositório para o seu ambiente local.
3. Configure as propriedades do banco de dados no arquivo `application.properties`.
4. Execute a aplicação.
5. Utilize o Postman ou o Insomnia para interagir com a api.

## Endpoints da API

- **POST /contacts**: Cadastra um novo contato.
- **GET /contacts**: Retorna a lista de todos os contatos cadastrados.
- **PUT /contacts**: Atualiza as informações de um contato existente.
- **DELETE /contacts/{id}**: Exclui um contato pelo seu ID.

## Dependências

Este projeto utiliza as seguintes dependências:

- Spring Web
- Spring Data JPA
- Lombok
- Hibernate Validation
- PostgreSQL
- Flyway
- Spring Boot DevTools
