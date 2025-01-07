# Carteira Financeira - Simulador RESTful

## Descrição

Este projeto é uma aplicação RESTful desenvolvida em Java utilizando Spring Boot, com o objetivo de simular uma carteira
financeira. A aplicação permite aos usuários realizar operações como cadastro, depósito, saque e consulta de saldo.

## Rotas da aplicação

### Cadastro de usuário

```http request
    POST /api/usuarios
```
- Corpo da requisição: JSON com os campos `nome`, `email`, `senha` e `confirmaSenha`
### Autenticação de usuário

```http request
    POST /api/auth/login
```
- Corpo da requisição: JSON com os campos `email` e `senha`
### Ação de depositar

```http request
    POST /api/carteira/deposito
```
- Corpo da requisição: JSON com o campo `valor` e `idUsuario`.
- Campo `valor` do tipo `String` e casa decimal delimitada por `.`.
- Autenticação: Incluir o token de autenticação no cabeçalho da requisição.
### Ação de sacar

```http request
    PUT /api/carteira/saque
```
- Corpo da requisição: JSON com o campo `valor` e `idUsuario`.
- Campo `valor` do tipo `String` e casa decimal delimitada por `.`.
- Autenticação: Incluir o token de autenticação no cabeçalho da requisição.
### Ação de consultar saldo

```http request
    GET /api/carteira/saldo/{idUsuario}
```
- Autenticação: Incluir o token de autenticação no cabeçalho da requisição.

### Ação de consultar histórico de transações

```http request
    GET /api/historico/{idUsuario}
```
- Autenticação: Incluir o token de autenticação no cabeçalho da requisição.


## Configurando e Executando

### Pre-requisitos

* **Java:** 17 ou superior
* **Banco Postgres: 16.4 ou superior ;**

### 1. Clone o repositório:

```bash
    git clone git@github.com:GamaGustavo/CarteiraFinanceira.git
    cd CarteiraFinanceira
```
### 2. Configure o banco de dados:
A aplicação está configurada para utilizar um banco de dados PostgreSQL. 

Edite o arquivo `src/main/resources/application.properties` para configurar as credenciais do seu banco de dados.

### 3. Executando a aplicação
Na raiz do projeto execute o comando:
```bash
    ./gradlew bootRun
```

