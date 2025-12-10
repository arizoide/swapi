# Star Wars Films API

Este é um projeto de demonstração em Spring Boot que expõe uma API REST para consultar e gerenciar informações sobre os filmes da saga Star Wars.

## Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3.2.5**
*   **Spring Web:** Para a criação dos endpoints REST.
*   **Spring Data JPA:** Para a persistência de dados.
*   **H2 Database:** Banco de dados em memória para desenvolvimento e testes.
*   **Lombok:** Para reduzir código boilerplate (getters, setters, construtores, etc.).
*   **Gradle:** Ferramenta de automação de compilação.
*   **JUnit 5 & Mockito:** Para testes unitários e de integração.

## Arquitetura

O projeto segue uma estrutura baseada em Arquitetura Hexagonal (Portas e Adaptadores), separando a lógica de negócio das tecnologias de entrada e saída.

-   **`adapter/in`**: Adaptadores de entrada (ex: `FilmController` para a API REST).
-   **`adapter/out`**: Adaptadores de saída (ex: `FilmJpaAdapter` para acesso ao banco de dados via JPA).
-   **`core`**: Contém a lógica de negócio (`FilmService`) e as portas (interfaces) que definem os contratos de comunicação.

## Endpoints da API

A API está disponível no prefixo `/api/v1`.

### Filmes

*   **`GET /api/v1/films`**
    *   Retorna uma lista com o resumo de todos os filmes.

*   **`GET /api/v1/films/{episodeId}`**
    *   Retorna o resumo de um filme específico.
    *   **Parâmetro:** `episodeId` (int) - O ID do episódio do filme (ex: 4 para "A New Hope").

*   **`GET /api/v1/films/{episodeId}/details`**
    *   Retorna todos os detalhes de um filme específico.
    *   **Parâmetro:** `episodeId` (int) - O ID do episódio do filme.

*   **`PUT /api/v1/films/{episodeId}/description`**
    *   Atualiza a sinopse de abertura (`openingCrawl`) de um filme.
    *   **Parâmetro:** `episodeId` (int) - O ID do episódio do filme.
    *   **Corpo da Requisição (JSON):**
        ```json
        {
          "description": "Uma nova sinopse de abertura para o filme..."
        }
        ```

## Como Executar

1.  Clone este repositório.
2.  Certifique-se de ter o JDK 21 instalado.
3.  Na raiz do projeto, execute o seguinte comando para iniciar a aplicação:

    ```bash
    ./gradlew bootRun
    ```

4.  A aplicação estará disponível em `http://localhost:8080`.
5.  Você pode acessar o console do banco de dados H2 em `http://localhost:8080/h2-console` para inspecionar os dados.
    *   **JDBC URL:** `jdbc:h2:mem:swapi`
    *   **User Name:** `sa`
    *   **Password:** (deixe em branco)

## Exemplos de Uso (cURL)

Aqui estão alguns exemplos de como interagir com a API usando `curl`.

### 1. Listar o resumo de todos os filmes

```bash
curl -X GET http://localhost:8080/api/v1/films
```

### 2. Obter o resumo de um filme específico (Episódio 4)

```bash
curl -X GET http://localhost:8080/api/v1/films/4
```

### 3. Obter os detalhes completos de um filme (Episódio 4)

```bash
curl -X GET http://localhost:8080/api/v1/films/4/details
```

### 4. Atualizar a sinopse de abertura de um filme (Episódio 4)

```bash
curl -X PUT http://localhost:8080/api/v1/films/4/description \
-H "Content-Type: application/json" \
-d '{
  "description": "É um período de guerra civil. Naves rebeldes, atacando de uma base secreta, conquistaram sua primeira vitória contra o maléfico Império Galáctico..."
}'
```

## Melhorias Futuras (Roadmap)

Os próximos passos para tornar a aplicação mais robusta e preparada para produção incluem:

*   **Resiliência e Retry:** Implementar um mecanismo de *retry* inteligente (ex: Resilience4j) para a carga inicial de dados. Caso a API externa (SWAPI) esteja indisponível na inicialização, o sistema deve tentar novamente de forma transparente.
*   **Mensageria (RabbitMQ/Kafka):** Evoluir a arquitetura para usar filas de mensagens. Se a carga de dados falhar, uma mensagem pode ser enviada para uma *Dead Letter Queue* (DLQ) para ser reprocessada posteriormente, garantindo consistência eventual.
*   **Containerização:** Criar um `Dockerfile` e um `docker-compose.yml` para facilitar o deploy e a execução da aplicação em qualquer ambiente.
*   **Observabilidade:** Adicionar o Spring Boot Actuator e integrar com Prometheus e Grafana para monitoramento de métricas em tempo real.
*   **Migração de Banco de Dados:** Substituir o H2 por um banco persistente (PostgreSQL) e usar Flyway para versionamento de schemas.
