# Catalog Service

Responsável por gerenciar os produtos disponíveis no sistema.

Porta: `8081`
Banco: `db_catalog`

## Estrutura catalog
 
```
ecommerce-microsservicos/
│
├── catalog-service/
│   ├── src/
│   │   ├── java/com/ecommerce/catalog
│   │   │   ├── controllers
│   │   │   ├── dto
│   │   │   ├── models
│   │   │   ├── repositories
│   │   │   └── services
│   │   ├── resources/
│   │   │   └── application.properties
│   ├── pom.xml
│   └── README.md

```


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

## Swagger UI
```
http://localhost:8081/swagger-ui.html
```

## Como executar

#### 1. Crie o banco
[Configuração do Banco](https://github.com/DeboraVicente/p1_e-commerce/blob/main/README.md#configura%C3%A7%C3%A3o-dos-bancos-de-dados)

#### 2. Esteja no projeto
`cd .\catalog-service\`

#### 3. Execute
`mvn spring-boot:run`

## Regras