# Catalog Service

Responsável por gerenciar os produtos disponíveis no sistema.

## Porta: `8081`
## Banco: `db_catalog`

---

## Endpoints

| Método | Rota            | Descrição              |
|--------|-----------------|------------------------|
| GET    | `/product`      | Lista todos os produtos |
| GET    | `/product/{id}` | Busca produto por ID   |
| POST   | `/product`      | Cadastra novo produto  |

### GET /product
**Response 200:**
```json
[
  { "id": 1, "description": "Notebook Dell", "value": 3500.00 },
  { "id": 2, "description": "Mouse Logitech", "value": 150.00 }
]
```

### GET /product/{id}
**Response 200:**
```json
{ "id": 1, "description": "Notebook Dell", "value": 3500.00 }
```
**Response 404:**
```json
{ "error": "Produto não encontrado" }
```

### POST /product
**Request:**
```json
{ "description": "Notebook Dell", "value": 3500.00 }
```
**Response 201:**
```json
{ "id": 1, "message": "Produto criado com sucesso" }
```

---

## Swagger UI
```
http://localhost:8081/swagger-ui.html
```

---

## Como executar

```bash
# 1. Crie o banco
mysql -u root -p -e "CREATE DATABASE db_catalog;"

# 2. Execute
mvn spring-boot:run
```

## Configuração (application.properties)
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/db_catalog
spring.datasource.username=ecommerce
spring.datasource.password=ecommerce123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
springdoc.swagger-ui.path=/swagger-ui.html
```