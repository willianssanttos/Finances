## Sistema Controle Financeiro Pessoal

## Descrição

O Sistema de Controle de Finanças Pessoais é uma aplicação de console desenvolvida em Java que permite aos usuários gerenciar suas finanças pessoais. O sistema oferece funcionalidades como cadastro de receitas, despesas, transações, controle de categorias, e visualização de extratos por período. A aplicação utiliza JDBC para manipulação de dados e PostgreSQL como banco de dados.

## Funcionalidades

## Gestão de Usuários
- Cadastro do Usuário: Permite o cadastro de novos usuários no sistema.
- Autenticação de Usuários: Login seguro com verificação de senha criptografada usando BCrypt.

## Gestão de Contas Financeiras
- Cadastro de Contas Financeiras: Permite o usuário cadastrar múltiplas contas, como "Banco", "Carteira", etc., com nome, tipo e saldo inicial. A seleção do tipo de conta agora é feita a partir de uma lista de tipos disponíveis armazenados no banco de dados.
- Edição e Exclusão de Contas: O usuário pode editar ou excluir contas. Ao excluir uma conta, todas as transações associadas a essa conta são também excluídas.

## Gestão de Transações
- Cadastro de Receitas e Despesas: Permite especificar a conta de origem/destino, descrição, categoria, valor e data da transação.
- Transações por Categoria: O sistema permite que o usuário categorize suas transações, podendo ser gastos (despesas) ou ganhos (receitas).
- Cálculo de Ganhos e Gastos por Categoria: Implementação de relatórios que calculam os valores totais de receitas e despesas por categoria, com visualização da porcentagem de participação de cada categoria no total do mês.
- Relatório Detalhado de Transações: O usuário pode visualizar o extrato de todas as suas contas, com filtros por mês e ano, exibindo todas as transações detalhadas (data, descrição, valor e categoria).

## Extratos e Relatórios
- Visualização de Extrato Financeiro: O sistema permite a geração de um extrato financeiro completo para o mês e ano corrente, ou para um período específico informado pelo usuário.
- Relatórios de Ganhos e Gastos: O sistema exibe o total de ganhos e gastos do mês e permite visualizar a distribuição percentual das categorias que mais contribuíram para cada tipo de transação (receitas ou despesas).

## Tecnologias Utilizadas

- Java 17
- JDBC para a comunicação com o banco de dados.
- PostgreSQL para persistência de dados.
- Maven para gerenciamento de dependências.
- BCrypt: Para criptografia de senhas e autenticação segura.

## Configurações de Banco de Dados
O sistema está configurado para usar um banco de dados PostgreSQL. As configurações de conexão estão na classe `DataSourceConfig`, e os scripts SQL necessários para a criação das tabelas e functions estão disponíveis na pasta `Scripts`.

## Funções no Banco de Dados
O sistema utiliza triggers e functions no banco de dados PostgreSQL para:

- Atualização automática de saldos: Uma trigger é acionada ao criar ou atualizar uma transação, recalculando automaticamente o saldo da conta afetada.

## Como Executar

1. Clone o repositório do projeto.
2. Configure um banco de dados PostgreSQL ou de sua preferencia, e execute os scripts SQL na pasta Scripts na raiz do projeto.
  - Observação: Execute primeiro o script de criação do banco de dados, seguido pelos scripts das functions.
3. Atualize as credenciais do banco de dados na classe DataSourceConfig conforme sua configuração local.
4. Compile o projeto usando o Maven:
  - mvn clean install
5. Execute o projeto. O sistema será executado no console e você poderá interagir com ele para gerenciar suas finanças pessoais.
