# Smart Budget Speech API

API REST para automação de lançamentos e gestão orçamentária por meio de comandos de voz, desenvolvida em Java com a infraestrutura do Spring AI.

## Objetivos do Projeto

- Automatizar o registro de transações financeiras via processamento de linguagem natural e áudio.
- Validar a disponibilidade de orçamento por categoria antes da gravação de dados no banco.
- Fornecer respostas em áudio e texto com base na avaliação das regras de negócio.

## Atendimento aos Requisitos do Desafio

- Integração com Spring AI para mediação entre modelos de linguagem e a aplicação Java.
- Conversão de entrada de áudio em texto estruturado por meio da Transcription API.
- Execução de validações de negócio com o uso de Tool Calling.
- Persistência das transações e atualização de saldos em banco de dados relacional.

## Evolução e Resultados Apresentados

1. Processamento Bidirecional de Áudio (Audio In / Audio Out)
   Recebimento de requisições de áudio via API REST, conversão para texto via Transcription API e síntese da resposta final em áudio MP3 utilizando Speech API.

2. Validação Encadeada via Tool Calling
   Execução obrigatória da consulta de limites (`checkBudgetLimit`) antes da chamada de gravação. Caso o teto financeiro seja atingido, a operação é interrompida com justificativa detalhada.

3. Persistência e Categorização Dinâmica
   A ferramenta `registerTransaction` infere o valor e a categoria a partir do texto interpretado, executa a escrita na tabela de transações e atualiza a soma acumulada da categoria.

## Tecnologias

- Java 25 e Spring Boot 3.3
- Spring AI (ChatClient, TranscriptionModel, SpeechModel)
- H2 Database e Apache Maven

## Contribuições Inovadoras

- Encadeamento sequencial de funções nativas Java orientadas pelo modelo de linguagem.
- Resposta audível gerada dinamicamente para integração com assistentes de voz.
- Inferência de categorias a partir de termos informais presentes na fala do usuário.

## Estrutura do Código-Fonte

### Estrutura de Diretórios

```text
smart-budget-speech-api/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── dio/
        │           └── budgetai/
        │               ├── BudgetAiApplication.java
        │               ├── controller/
        │               │   └── TransactionController.java
        │               ├── model/
        │               │   ├── BudgetLimit.java
        │               │   └── Transaction.java
        │               ├── repository/
        │               │   ├── BudgetLimitRepository.java
        │               │   └── TransactionRepository.java
        │               ├── service/
        │               │   └── SpeechService.java
        │               └── tools/
        │                   └── TransactionTools.java
        └── resources/
            └── application.yml
