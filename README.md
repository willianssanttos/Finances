## Sistema Controle Financeiro Pessoal

## Descrição

O Sistema de Controle de Finanças Pessoais é uma aplicação de console desenvolvida em Java que permite aos usuários gerenciar suas finanças pessoais. O sistema oferece funcionalidades como cadastro de receitas, despesas. A aplicação utiliza JDBC para manipulação de dados e PostgreSQL como banco de dados.

## Funcionalidades

- Cadastro do Usuário
- Cadastro de Contas Financeiras
- Cadastro de Receitas financeiras: 
Especificando a conta de recebimento, descrição valor, data.
- Cadastro de Despesas financeiras:
Especificando a conta de saida, descrição, valor, data.

## Tecnologias Utilizadas

- Java 17
- JDBC para a comunicação com o banco de dados.
- PostgreSQL para persistência de dados.
- Maven para gerenciamento de dependências.

## Configurações de Banco de Dados
O sistema está configurado para usar um banco de dados PostgreSQL. As configurações de conexão estão na classe `DataSourceConfig`.

## Scrip da base de dados e as functions

- Estão na pasta Scripts na raiz do projeto

- Observação: Realizar a execução do script da base de dados primeiro, e logo apos realizar a execução dos scripts das functions

## Compile o projeto com Maven:

- mvn clean install
