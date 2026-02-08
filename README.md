# apiTaskManagerGlobalParts

# Global Parts – API de Gerenciamento de Tarefas (Kanban)

API RESTful desenvolvida com **Spring Boot** para gerenciamento de tarefas em formato **Kanban**, permitindo criação, edição, organização por status e exclusão de tarefas.

---

## 📌 Visão Geral

Esta API fornece endpoints para **criar, listar, atualizar e excluir tarefas**, organizando-as em um quadro Kanban agrupado por status:

- `TODO`
- `DOING`
- `DONE`

O objetivo é facilitar o controle de atividades e o acompanhamento do fluxo de trabalho.

---

## 🛠 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL**
- **Maven**

---

## Variáveis de Ambiente

Para configurar a conexão com o banco de dados e a aplicação, podem ser definidas as variáveis abaixo.  
Caso não sejam informadas, **valores padrão serão utilizados**.

| Variável        | Descrição                                           | Valor Padrão |
|-----------------|-----------------------------------------------------|--------------|
| `DB_URL`        | URL de conexão JDBC com o MySQL                      | `jdbc:mysql://localhost:3306/kanban?useSSL=false&serverTimezone=America/Sao_Paulo` |
| `DB_USER`       | Usuário do banco de dados                            | `root` |
| `DB_PASS`       | Senha do banco de dados                              | `root` |
| `SERVER_PORT`   | Porta em que a aplicação será executada              | `4000` |

---

## Endpoints da API

Prefixo base de todos os endpoints:  
/api
---

### Obter o Quadro Kanban

Retorna todas as tarefas **agrupadas por status**.

- **Método:** `GET`
- **Endpoint:** `/kanbanBoard`
- **Resposta de Sucesso (200 OK):**

```json
{
  "response": {
    "TODO": [
      {
        "id": 1,
        "title": "Configurar ambiente de desenvolvimento",
        "description": "Instalar todas as dependências necessárias.",
        "priority": "HIGH",
        "status": "TODO",
        "date": "2024-08-15"
      }
    ],
    "DOING": [],
    "DONE": [
      {
        "id": 2,
        "title": "Criar o repositório no GitHub",
        "description": null,
        "priority": "LOW",
        "status": "DONE",
        "date": "2024-08-10"
      }
    ]
  },
  "message": "Dados carregados com sucesso"
}
Adicionar uma Nova Tarefa
Cria uma nova tarefa no quadro Kanban.

Método: POST

Endpoint: /addTask

Payload da Requisição:

{
  "title": "Minha Nova Tarefa",
  "description": "Descrição detalhada da tarefa.",
  "priority": "MEDIUM",
  "status": "TODO",
  "date": "2024-09-01"
}
Resposta de Sucesso (201 Created):

{
  "message": "Tarefa adicionada com sucesso"
}
Editar uma Tarefa
Atualiza os dados de uma tarefa existente.

Método: PUT

Endpoint: /editTask/{id}

Parâmetro de URL:

id (Long) – ID da tarefa

Payload da Requisição:

{
  "title": "Título da Tarefa Atualizado",
  "description": "Descrição atualizada.",
  "priority": "HIGH",
  "status": "DOING",
  "date": "2024-09-05"
}
Resposta de Sucesso (200 OK):

{
  "message": "Tarefa editada com sucesso"
}
Mudar o Status de uma Tarefa
Avança automaticamente o status da tarefa no fluxo:

TODO → DOING → DONE
Método: PUT

Endpoint: /changeStatusTask/{id}

Parâmetro de URL:

id (Long) – ID da tarefa

Resposta de Sucesso (200 OK):

{
  "message": "Status atualizado com sucesso"
}
Excluir uma Tarefa
Remove definitivamente uma tarefa do banco de dados.

Método: DELETE

Endpoint: /deleteTask/{id}

Parâmetro de URL:

id (Long) – ID da tarefa

Resposta de Sucesso (200 OK):

{
  "message": "Tarefa excluída com sucesso"
}
Modelos de Dados e Enums
Payloads
CreateTaskRequest / UpdateTaskRequest
Campo	Tipo	Validação	Descrição
title	String	@NotBlank	Título da tarefa
description	String	Opcional	Descrição detalhada
priority	TaskPriority	@NotNull	Prioridade da tarefa
status	TaskStatus	@NotNull	Status atual
date	LocalDate	@NotNull	Data de vencimento (yyyy-MM-dd)

Enums
TaskStatus
TODO

DOING

DONE

TaskPriority
LOW

MEDIUM

HIGH

▶ Como Executar a Aplicação
Clone o repositório.

Configure as variáveis de ambiente (ou utilize os valores padrão).

Certifique-se de que o MySQL está em execução e que o banco kanban existe.

Execute o comando abaixo para iniciar a aplicação:

./mvnw spring-boot:run
A API estará disponível em:
 http://localhost:4000