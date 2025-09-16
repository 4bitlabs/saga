# 💻 SIGHAS – Sistema Integrado de Gestão de Horários e Alocação de Salas

## 🔎 Descrição

Projeto desenvolvido para a disciplina de Projeto Integrador do curso Bacharelado em Sistemas de Informação do Instituto Federal de Alagoas (IFAL).

Propõe o desenvolvimento de uma aplicação WEB, que pemitirá o fácil gerencimento das salas de aulas, disciplinas, calendário e horários da instituição.

## ⚙️ Principais Features

Uma aplicação WEB, que pemitirá um fácil gerencimento de salas de aula de uma instituição de ensino, disciplinas e seus respectivos horários.

- CRUD de salas de aulas
- CRUD de disciplinas
- CRD de cursos
- CRUD de usuários (administradores do sistema, professores, alunos, etc)
- Produção de representação de calendário acadêmico para cursos e suas disciplinas

## 🪆 Stakeholders

### 🧑🏻‍🎓 Colaboradores/Desenvolvedores

| Nome                                  | Foto                                                                                           | E-mail                          |
|---------------------------------------|------------------------------------------------------------------------------------------------|---------------------------------|
| **[Emesson Horário dos Santos](https://github.com/emessonhoracio)**        | <img src="https://avatars.githubusercontent.com/u/61273469?v=4" width="100" alt="Emesson">     | ehs4@aluno.ifal.edu.br         |
| **[Lucas Matheus Vieira Lúcio](https://github.com/Casterrr)**        | <img src="https://avatars.githubusercontent.com/u/44622004?v=4" width="100" alt="Lucas">     | lmvl2@aluno.ifal.edu.br         |
| **[Filipe Zaidan Ferreira da Silva](https://github.com/filipezaidan)**   | <img src="https://avatars.githubusercontent.com/u/41112779?v=4" width="100" alt="Filipe">    | fzfs2@aluno.ifal.edu.br         |
| **[Maelton Lima dos Santos](https://github.com/Maelton)**         | <img src="https://avatars.githubusercontent.com/u/61250761?v=4" width="100" alt="Luís">     | mls54@aluno.ifal.edu.br         |

### 👨🏻‍💻 Beneficiários
- Instituto Federal de Alagoas
    - Departamento de Ensino (DE)
    - Coordenações dos cursos técnicos e superiores
    - Docentes

## 🚀 Como Executar o Projeto

### 📋 Pré-requisitos

- **Java 21** ou superior
- **Maven 3.6+** ou superior
- **Docker** e **Docker Compose** (para execução com containers)
- **PostgreSQL 17+** (se executando localmente)

### 🛠️ Instalação e Execução

#### Execução com Docker

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/cacadoresti/sighas-be.git
   cd sighas-be
   ```

2. **Execute com Docker Compose:**
   ```bash
   docker-compose up --build
   ```

3. **Acesse a aplicação:**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui/index.html`
   - Banco de dados: `localhost:5432`

### 🔧 Configurações

#### Variáveis de Ambiente

O projeto utiliza as seguintes variáveis de ambiente (com valores padrão):

- `DB_HOST`: localhost
- `DB_PORT`: 5432
- `DB_NAME`: sighas
- `DB_USERNAME`: admin
- `DB_PASSWORD`: admin
- `ACTIVE_PROFILE`: prod
- `FRONTEND_URL`: http://localhost:3000

#### Perfis de Execução

- **Desenvolvimento**: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`
- **Produção**: `./mvnw spring-boot:run -Dspring-boot.run.profiles=prod`

### 📚 Documentação da API

Após executar a aplicação, acesse:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### 🐛 Solução de Problemas

#### Problema: Erro de conexão com banco de dados
- Verifique se o PostgreSQL está rodando
- Confirme as credenciais no arquivo de configuração
- Verifique se a porta 5432 está disponível

#### Problema: Erro de permissão no Maven Wrapper (Windows)
```bash
# Execute no PowerShell como administrador
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

#### Problema: Porta 8080 já em uso
- Altere a porta no arquivo `application.properties`:
  ```properties
  server.port=8081
  ```

## 📅 Prazo

- 01/01/2025 (Entrega Final)
