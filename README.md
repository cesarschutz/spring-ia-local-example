# Spring AI - Local LLM with Tools Demo

Este é um projeto educacional que demonstra como usar o **Spring AI** com um modelo de linguagem grande (LLM) executado localmente via **Ollama**. O foco principal é mostrar o poder das **Tools (`@Tool`)**, que permitem à IA interagir com sistemas externos para buscar informações e responder a perguntas de forma mais inteligente e contextual.

O projeto utiliza uma **arquitetura hexagonal** para separar as preocupações de negócio, aplicação e infraestrutura, resultando em um código mais limpo, testável e manutenível.

## Arquitetura do Projeto

A aplicação segue os princípios da arquitetura hexagonal (Portas e Adaptadores). A imagem abaixo ilustra o fluxo de uma requisição, desde a interface do usuário até a interação com os serviços externos.

![Arquitetura do Projeto](https://mermaid.ink/svg/eyJjb2RlIjoiZ3JhcGggVEQ7XG4gICAgc3ViZ3JhcGggXCJCcm93c2VyXCJcbiAgICAgICAgVUkoXCJGcm9udGVuZCA8YnIvPihpbmRleC5odG1sKVwiKVxuICAgIGVuZFxuXG4gICAgc3ViZ3JhcGggXCJBcHBsaWNhdGlvbiBMYXllciAoU3ByaW5nIEJvb3QpXCJcbiAgICAgICAgQVtCXCIvYXBpL2NoYXQ8YnIvPkNoYXRDb250cm9sbGVyXCIvXVxuICAgICAgICBCW1wiQ2hhdFNlcnZpY2VcIl1cbiAgICAgICAgQ1tcIlNwcmluZyBBSSA8YnIvPkNoYXRDbGllbnRcIl1cbiAgICAgICAgRFtcIkNhcmRBY2NvdW50QXBpVG9vbHMgPGJyLz4oQFRvb2wpXCJdXG4gICAgZW5kXG5cbiAgICBzdWJncmFwaCBcIkRvbWFpbiBMYXllclwiXG4gICAgICAgIEVbXCJDYXJkQWNjb3VudEFwaSA8YnIvPihQb3J0KVwiXVxuICAgIGVuZFxuICAgIFxuICAgIHN1YmdyYXBoIFwiSW5mcmFzdHJ1Y3R1cmUgTGF5ZXJcIlxuICAgICAgICBGW1wiQ2FyZEFjY291bnRBcGJJbXBsIDxici8-KEFkYXB0ZXIpXCJdXG4gICAgZW5kXG5cbiAgICBzdWJncmFwaCBcIkV4dGVybmFsIFNlcnZpY2VzXCJcbiAgICAgICAgR1tcIk9sbGFtYSA8YnIvPihMb2NhbCBMTE0pXCJdXG4gICAgICAgIEhbXCJDYXJkIEFjY291bnQgQVBJIDxici8-KE1vY2sgU2VydmljZSlcIl1cbiAgICBlbmRcblxuICAgIFVJIC0tIFwiSFRUUCBSZXF1ZXN0XCIgLS0-IEE7XG4gICAgQSAtLSBcIkNhbGxzXCIgLS0-IEI7XG4gICAgQiAtLSBcIlVzZXNcIiAtLT4gQztcbiAgICBDIC0tIFwiRGV0ZWN0cyBuZWVkIGZvciBUb29sXCIgLS0-IEQ7XG4gICAgRCAtLSBcIlVzZXMgSW50ZXJmYWNlXCIgLS0-IEU7XG4gICAgRSAtLSBcIkRJXCIgLS0-IEY7XG4gICAgQyAtLSBcIkNvbW11bmljYXRlcyB3aXRoXCIgLS0-IEc7XG4gICAgRiAtLSBcIkhUVFAgQ2FsbFwiIC0tPiBIO1xuXG4gICAgc3R5bGUgVUkgaWQ6YnJvd3Nlci1iYWNrZ3JvdW5kLCBmaWxsOiNmOWYsc3Ryb2tlOiMzMzMsc3Ryb2tlLXdpZHRoOjJweFxuICAgIHN0eWxlIEcgaWQ6ZXh0ZXJuYWwtYmFja2dyb3VuZCwgZmlsbDojYmJmLHN0cm9zZTojMzMzLHN0cm9zZS12aWR0aDoycHhcbiAgICBzdHlsZSBIIGlkOmV4dGVybmFsLWJhY2tncm91bmQsIGZpbGw6I2JiZixzdHJva2U6IzMzMyxzdHJva2Utd2lkdGg6MnB4IiwibWVybWFpZCI6e30sInVwZGF0ZUVkaXRvciI6ZmFsc2UsImF1dG9TeW5jIjp0cnVlLCJ1cGRhdGVEaWFncmFtIjpmYWxzZX0)

- **Fluxo de Interação:**
  1.  O usuário envia uma mensagem pela interface web (`index.html`).
  2.  O `ChatController` recebe a requisição.
  3.  O `ChatService` orquestra a chamada ao `ChatClient` do Spring AI.
  4.  O `ChatClient` envia o prompt do usuário para o **Ollama LLM**.
  5.  Se o LLM identifica que a pergunta pode ser respondida por uma ferramenta, ele invoca a função correspondente em `CardAccountApiTools`.
  6.  A ferramenta (`@Tool`) utiliza a porta `CardAccountApi` do domínio para buscar os dados.
  7.  A implementação da porta (`CardAccountApiImpl`), na camada de infraestrutura, faz uma chamada HTTP para a API externa (neste caso, um mock de API de cartões).
  8.  A resposta da ferramenta é enviada de volta ao LLM, que a utiliza para formular a resposta final ao usuário.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring AI**: Para integração com modelos de IA.
- **Ollama**: Para executar LLMs localmente.
- **Docker & Docker Compose**: Para orquestrar os serviços (Ollama e a API de mock).
- **Maven**: Para gerenciamento de dependências.
- **Arquitetura Hexagonal**: Para organização do código.

## Pré-requisitos

Antes de começar, garanta que você tenha os seguintes softwares instalados:
- **Java 21+**
- **Docker** e **Docker Compose**
- **Maven**

## Como Executar o Projeto

1.  **Clone o Repositório**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd spring-ia-local-example
    ```

2.  **Inicie os Serviços com Docker Compose**
    Este comando irá subir dois contêineres:
    - `ollama`: O servidor do Ollama.
    - `card-account-api`: Um mock de API de contas de cartão feito com `json-server`.

    ```bash
    docker-compose up -d
    ```

3.  **Baixe o Modelo do Ollama**
    Após o contêiner do Ollama iniciar, você precisa baixar o modelo que será usado. Este projeto está configurado para o `qwen2.5:0.5b`, um modelo leve e eficiente.

    ```bash
    docker exec -it ollama ollama pull qwen2.5:0.5b
    ```

4.  **Execute a Aplicação Spring Boot**
    Use o Maven para compilar e iniciar a aplicação.

    ```bash
    ./mvnw spring-boot:run
    ```

## Como Usar

1.  **Acesse a Interface Web**
    Abra seu navegador e acesse: `http://localhost:8080`

    Você verá uma interface de chat simples.

2.  **Interaja com a IA**
    Faça perguntas ao chatbot. Para acionar a `@Tool`, faça uma pergunta que precise de dados de um cartão, usando um UUID válido.

    **UUIDs de exemplo (disponíveis no `db.json`):**
    - `1d53a1a3-5526-4252-959c-72a7a45217a4`
    - `93a7a452-17a4-4252-959c-1d53a1a35526`

    **Exemplo de prompt:**
    > Qual o limite do cartão com id `1d53a1a3-5526-4252-959c-72a7a45217a4`?

    A IA irá identificar a necessidade de usar a ferramenta, chamar a API de cartões, receber os dados e formatar uma resposta amigável para você.