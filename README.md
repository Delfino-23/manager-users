# Student Manager API

Um projeto Spring Boot desenvolvido para gerenciar usuários, administradores, instrutores e cursos, com autenticação JWT e banco de dados MySQL.

## 📋 Informações do Projeto

- **Nome do Projeto**: Manager Users
- **Versão**: 0.0.1-SNAPSHOT
- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.4.3
- **Build Tool**: Maven
- **Banco de Dados**: MySQL 8.0+
- **Autenticação**: JWT (JSON Web Token)

## 🚀 Tecnologias Utilizadas

### Backend
- **Spring Boot 3.4.3** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Segurança e autenticação
- **Spring Web** - APIs REST

### Autenticação
- **JWT (JJWT)** v0.11.5 - Geração e validação de tokens
  - jjwt-api
  - jjwt-impl
  - jjwt-jackson

### Banco de Dados
- **MySQL Connector/J** - Driver para MySQL
- **Hibernate** - ORM com dialect MySQL8

### Utilitários
- **Lombok** - Redução de boilerplate (getters, setters, construtores)

## 📁 Estrutura do Projeto

```
manager-users/
├── src/
│   ├── main/
│   │   ├── java/com/manager/clients/
│   │   │   ├── ClientsApplication.java          # Classe principal
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java          # Configurações de segurança
│   │   │   ├── controller/
│   │   │   │   ├── AdministratorsController.java
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── CourseController.java
│   │   │   │   ├── InstructorsController.java
│   │   │   │   └── UsersController.java
│   │   │   ├── models/
│   │   │   │   ├── Administrators.java
│   │   │   │   ├── Course.java
│   │   │   │   ├── Instructors.java
│   │   │   │   └── Users.java
│   │   │   ├── payload/
│   │   │   │   ├── JwtResponse.java
│   │   │   │   └── LoginRequest.java
│   │   │   ├── repository/
│   │   │   │   ├── AdministratorsRepository.java
│   │   │   │   ├── CourseRepository.java
│   │   │   │   ├── InstructorsRepository.java
│   │   │   │   └── UsersRepository.java
│   │   │   └── security/
│   │   │       ├── JpaUserDetailsService.java
│   │   │       ├── JwtAuthenticationEntryPoint.java
│   │   │       ├── JwtAuthenticationFilter.java
│   │   │       └── JwtUtils.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/manager/clients/
│           └── ClientsApplicationTests.java
├── docker-compose.yaml
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

## 🏗️ Componentes Principais

### Controllers (APIs REST)

#### **AuthController** (`/auth`)
- `POST /auth/login` - Autenticação de usuário via email e senha
  - Retorna token JWT e informações do usuário

#### **UsersController** (`/users`)
- `GET /users/` - Listar todos os usuários
- `GET /users/{id}` - Buscar usuário por ID
- `POST /users` - Criar novo usuário (com criptografia de senha)
- `PUT /users/{id}` - Atualizar usuário
- `DELETE /users/{id}` - Deletar usuário

#### **AdministratorsController** (`/administrators`)
- CRUD completo para administradores

#### **InstructorsController** (`/instructors`)
- CRUD completo para instrutores

#### **CourseController** (`/courses`)
- CRUD completo para cursos

### Models (Entidades)
- **Users** - Usuários do sistema
- **Administrators** - Usuários com perfil de administrador
- **Instructors** - Instrutores/Professores
- **Course** - Cursos disponíveis

### Segurança
- **SecurityConfig** - Configuração de segurança Spring
- **JwtUtils** - Utilitários para geração e validação de JWT
- **JwtAuthenticationFilter** - Filtro para validar tokens JWT
- **JwtAuthenticationEntryPoint** - Tratamento de erros de autenticação
- **JpaUserDetailsService** - Carregamento de detalhes do usuário

## 🔧 Configuração

### Variáveis de Ambiente (application.properties)

```properties
spring.application.name=clients

# Banco de Dados MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/spring_security
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate/JPA
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=change_this_secret_to_a_strong_one
jwt.expirationMs=3600000  # 1 hora em milissegundos
```

## ⚙️ Pré-requisitos

- Java 21+
- Maven 3.6.0+
- MySQL 8.0+ (se não usar Docker)

## 🚀 Como Executar

### Autenticar Usuário
```bash
POST /auth/login
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "usuario@example.com",
  "roles": ["ROLE_USER"]
}
```

### Criar Usuário
```bash
POST /users
Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@example.com",
  "password": "senha123",
  "phone": "11999999999",
  "cpf": "12345678900",
  "course": "1"
}
```

### Listar Usuários
```bash
GET /users/
Authorization: Bearer {token}
```

### Atualizar Usuário
```bash
PUT /users/1
Content-Type: application/json
Authorization: Bearer {token}

{
  "name": "João Silva Atualizado",
  "email": "joao.novo@example.com",
  "phone": "11988888888",
  "cpf": "12345678900",
  "course": "2"
}
```

### Deletar Usuário
```bash
DELETE /users/1
Authorization: Bearer {token}
```

## 🔐 Segurança

- **Autenticação JWT** - Baseada em tokens JWT com expiração configurável
- **Criptografia de Senha** - Senhas são criptografadas usando Spring Security
- **Filtro JWT** - Validação automática de tokens em todas as requisições autenticadas
- **CORS** - Configurável em `SecurityConfig`

## 📦 Dependências Principais

| Dependência | Versão | Descrição |
|-------------|--------|-----------|
| spring-boot-starter-parent | 3.4.3 | Spring Boot Base |
| spring-boot-starter-data-jpa | Latest | JPA/Hibernate |
| spring-boot-starter-web | Latest | Spring Web MVC |
| spring-boot-starter-security | Latest | Spring Security |
| mysql-connector-j | Latest | Driver MySQL |
| jjwt-api | 0.11.5 | JWT |
| jjwt-impl | 0.11.5 | JWT Implementation |
| jjwt-jackson | 0.11.5 | JWT Jackson |

## ✅ Checklist de Configuração
- Java 21 instalado
- Maven instalado
- Docker/Docker Compose instalado
- Alterar `jwt.secret` em `application.properties` para uma chave forte
- Configurar credenciais de banco de dados
- Testar endpoints com Postman/Insomnia

## 📄 Licença

Este projeto foi desenvolvido como parte do curso de Programação Orientada a Objetos - 4º Semestre - ADS.

## 👤 Autor

Desenvolvido por: Carlos Eduardo, Fabiane Manuelly, Eduardo Toledo, Júlio Cesar e Vinícius Covolam
