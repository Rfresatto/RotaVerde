# 🌿 Rota Verde

Sistema de gerenciamento de coleta e monitoramento de resíduos, desenvolvido com Java 21 e Spring Boot.

---

## 👥 Time

| Nome             |
| ---------------- |
| Julio Cesar      |
| Renan Martins    |
| João Ricardo     |
| Miguel Siqueira  |
| Arthur Tassinari |

---

## 🛠️ Tecnologias

| Tecnologia            | Versão |
| --------------------- | ------ |
| Java - 21             |
| Spring Boot           |
| Spring Security + JWT |
| Spring Data JPA       |
| Flyway                |
| Oracle Database       |
| Docker                |

---

## 📋 Pré-requisitos

- Java 21+
- Maven 3.8+
- Docker

---

## ⚙️ Configuração

### application.properties

```properties
spring.application.name=rota-verde
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.flyway.baselineOnMigrate=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.error.include-stacktrace=never
server.error.include-message=always
```

---

## 🗄️ Banco de Dados

O projeto utiliza **Oracle Database** com migrações gerenciadas pelo **Flyway**.

### Migrações

| Arquivo                                 | Descrição                                              |
| --------------------------------------- | ------------------------------------------------------ |
| `V1__create_structural_tables.sql`      | Criação das tabelas estruturais do sistema             |
| `V2__create_sequences_alter_tables.sql` | Criação das sequences e alterações nas tabelas         |
| `V3__alter_user_adding_role.sql`        | Adição do campo de perfil (role) na tabela de usuários |
| `V4__modify_sizer_password.sql`         | Ajuste no tamanho do campo de senha                    |

### Diagrama de Entidades

```
RV_T_COLLECTION_POINT
    └── RV_T_CONTAINER         (FK_CONTAINER_POINT)
            ├── RV_T_ALERT     (FK_ALERT_CONTAINER)
            │       └── RV_T_USER_NOTIFICATION  (FK_NOTIFICATION_ALERT)
            └── RV_T_COLLECTION (FK_COLLECTION_CONTAINER)

RV_T_USERS (independente)
```

---

## 🔐 Segurança

O projeto utiliza **JWT (JSON Web Token)** com **Spring Security** para autenticação e autorização.

### Regras de Acesso

| Método   | Rota       | Acesso        |
| -------- | ---------- | ------------- |
| `POST`   | `/auth/**` | Público       |
| `GET`    | `/api/**`  | Autenticado   |
| `POST`   | `/api/**`  | Somente ADMIN |
| `PUT`    | `/api/**`  | Somente ADMIN |
| `DELETE` | `/api/**`  | Somente ADMIN |

### Autenticação

**Registro:**

```
POST /auth/register
```

```json
{
  "name": "Seu Nome",
  "email": "seu@email.com",
  "password": "suasenha",
  "role": "ADMIN"
}
```

**Login:**

```
POST /auth/login
```

```json
{
  "email": "seu@email.com",
  "password": "suasenha"
}
```

Retorna um token JWT que deve ser enviado no header das requisições:

```
Authorization: Bearer {token}
```

---

## 🚀 Endpoints

### Collection Points `/api`

| Método   | Rota                                              | Descrição                      | Acesso      |
| -------- | ------------------------------------------------- | ------------------------------ | ----------- |
| `POST`   | `/collection-point`                               | Criar ponto de coleta          | ADMIN       |
| `GET`    | `/collection-points`                              | Listar todos                   | Autenticado |
| `GET`    | `/collection-point/{id}`                          | Buscar por ID                  | Autenticado |
| `PUT`    | `/collection-point/{id}`                          | Atualizar                      | ADMIN       |
| `DELETE` | `/collection-point/{id}`                          | Deletar                        | ADMIN       |
| `GET`    | `/collection-points/active`                       | Listar ativos                  | Autenticado |
| `GET`    | `/collection-points/large`                        | Listar com capacidade > 300kg  | Autenticado |
| `GET`    | `/collection-points/city/{city}`                  | Buscar por cidade              | Autenticado |
| `GET`    | `/collection-points/capacity?min={min}&max={max}` | Buscar por faixa de capacidade | Autenticado |
| `GET`    | `/collection-points/search?name={name}`           | Buscar por nome                | Autenticado |

### Containers `/api`

| Método   | Rota                                       | Descrição                      | Acesso      |
| -------- | ------------------------------------------ | ------------------------------ | ----------- |
| `POST`   | `/container`                               | Criar container                | ADMIN       |
| `GET`    | `/containers`                              | Listar todos                   | Autenticado |
| `GET`    | `/container/{id}`                          | Buscar por ID                  | Autenticado |
| `PUT`    | `/container/{id}`                          | Atualizar                      | ADMIN       |
| `DELETE` | `/container/{id}`                          | Deletar                        | ADMIN       |
| `GET`    | `/containers/waste-type/{wasteType}`       | Buscar por tipo de resíduo     | Autenticado |
| `GET`    | `/containers/critical`                     | Listar com uso ≥ 80%           | Autenticado |
| `GET`    | `/containers/collection-point/{pointId}`   | Buscar por ponto de coleta     | Autenticado |
| `GET`    | `/containers/empty`                        | Listar vazios                  | Autenticado |
| `GET`    | `/containers/capacity?min={min}&max={max}` | Buscar por faixa de capacidade | Autenticado |

### Alerts `/api`

| Método   | Rota                                           | Descrição             | Acesso      |
| -------- | ---------------------------------------------- | --------------------- | ----------- |
| `POST`   | `/alert`                                       | Criar alerta          | ADMIN       |
| `GET`    | `/alerts`                                      | Listar todos          | Autenticado |
| `GET`    | `/alert/{id}`                                  | Buscar por ID         | Autenticado |
| `PUT`    | `/alert/{id}`                                  | Atualizar             | ADMIN       |
| `DELETE` | `/alert/{id}`                                  | Deletar               | ADMIN       |
| `GET`    | `/alerts/unresolved`                           | Listar não resolvidos | Autenticado |
| `GET`    | `/alerts/resolved`                             | Listar resolvidos     | Autenticado |
| `GET`    | `/alerts/type/{alertType}`                     | Buscar por tipo       | Autenticado |
| `GET`    | `/alerts/container/{containerId}`              | Buscar por container  | Autenticado |
| `GET`    | `/alerts/date?startDate={date}&endDate={date}` | Buscar por período    | Autenticado |

Valores válidos para `alertType`: `CRITICAL_CAPACITY`, `PENDING_COLLECTION`, `OVERFLOW`

### Collections `/api`

| Método   | Rota                                                | Descrição              | Acesso      |
| -------- | --------------------------------------------------- | ---------------------- | ----------- |
| `POST`   | `/collection`                                       | Registrar coleta       | ADMIN       |
| `GET`    | `/collections`                                      | Listar todas           | Autenticado |
| `GET`    | `/collection/{id}`                                  | Buscar por ID          | Autenticado |
| `PUT`    | `/collection/{id}`                                  | Atualizar              | ADMIN       |
| `DELETE` | `/collection/{id}`                                  | Deletar                | ADMIN       |
| `GET`    | `/collections/container/{containerId}`              | Buscar por container   | Autenticado |
| `GET`    | `/collections/responsible/{responsible}`            | Buscar por responsável | Autenticado |
| `GET`    | `/collections/destination/{destination}`            | Buscar por destino     | Autenticado |
| `GET`    | `/collections/weight?weight={weight}`               | Buscar por peso mínimo | Autenticado |
| `GET`    | `/collections/date?startDate={date}&endDate={date}` | Buscar por período     | Autenticado |

### Notifications `/api`

| Método   | Rota                                                  | Descrição               | Acesso      |
| -------- | ----------------------------------------------------- | ----------------------- | ----------- |
| `POST`   | `/notification`                                       | Criar notificação       | ADMIN       |
| `GET`    | `/notifications`                                      | Listar todas            | Autenticado |
| `GET`    | `/notification/{id}`                                  | Buscar por ID           | Autenticado |
| `PUT`    | `/notification/{id}`                                  | Atualizar               | ADMIN       |
| `DELETE` | `/notification/{id}`                                  | Deletar                 | ADMIN       |
| `GET`    | `/notifications/unsent`                               | Listar não enviadas     | Autenticado |
| `GET`    | `/notifications/channel/{channel}`                    | Buscar por canal        | Autenticado |
| `GET`    | `/notifications/alert/{alertId}`                      | Buscar por alerta       | Autenticado |
| `GET`    | `/notifications/recipient/{recipient}`                | Buscar por destinatário | Autenticado |
| `GET`    | `/notifications/date?startDate={date}&endDate={date}` | Buscar por período      | Autenticado |

### Users `/api`

| Método   | Rota         | Descrição     | Acesso      |
| -------- | ------------ | ------------- | ----------- |
| `POST`   | `/user`      | Criar usuário | ADMIN       |
| `GET`    | `/users`     | Listar todos  | Autenticado |
| `GET`    | `/user/{id}` | Buscar por ID | Autenticado |
| `PUT`    | `/user/{id}` | Atualizar     | ADMIN       |
| `DELETE` | `/user/{id}` | Deletar       | ADMIN       |

---

## 📦 Exemplos de JSON

### Criar Collection Point

```json
{
  "name": "Ponto de Coleta Central",
  "address": "Rua das Flores, 123",
  "city": "São Paulo",
  "capacityKg": 500.0,
  "active": "Y"
}
```

### Criar Container

```json
{
  "collectionPointId": 1,
  "wasteType": "PAPER",
  "capacityKg": 100.0,
  "currentVolumeKg": 0.0
}
```

### Criar Alert

```json
{
  "containerId": 1,
  "alertType": "CRITICAL_CAPACITY",
  "message": "Contêiner 1 atingiu 82% da capacidade. Agendar coleta."
}
```

### Criar Collection

```json
{
  "containerId": 1,
  "collectedWeightKg": 45.5,
  "responsible": "João Silva",
  "destination": "Reciclagem Central",
  "notes": "Coleta realizada sem intercorrências"
}
```

### Criar Notification

```json
{
  "alertId": 1,
  "recipient": "joao.silva@email.com",
  "channel": "EMAIL",
  "message": "Alerta: Contêiner 1 atingiu capacidade crítica."
}
```

---

## 🐳 Docker

### Dockerfile

```dockerfile
FROM eclipse-temurin:21-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/rota-verde-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Build e execução

**1. Gerar o JAR:**

```bash
mvn clean package -DskipTests
```

**2. Build da imagem:**

```bash
docker build -t rota-verde .
```

**3. Rodar o container:**

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL \
  -e SPRING_DATASOURCE_USERNAME=rm562801 \
  -e SPRING_DATASOURCE_PASSWORD=041198 \
  rota-verde
```

**4. Verificar se está rodando:**

```bash
docker ps
```

**5. Ver logs:**

```bash
docker logs {container_id}
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 🔄 Tratamento de Erros

A API retorna respostas padronizadas para erros:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Recurso não encontrado.",
  "timestamp": "2026-05-23T10:00:00"
}
```

| Status | Descrição                |
| ------ | ------------------------ |
| `200`  | Sucesso                  |
| `201`  | Criado com sucesso       |
| `204`  | Deletado com sucesso     |
| `400`  | Requisição inválida      |
| `401`  | Não autenticado          |
| `403`  | Sem permissão            |
| `404`  | Recurso não encontrado   |
| `500`  | Erro interno do servidor |

---

## ✅ Validações

Os campos obrigatórios são validados automaticamente. Exemplo de erro de validação:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "O nome é obrigatório",
  "timestamp": "2026-05-23T10:00:00"
}
```
