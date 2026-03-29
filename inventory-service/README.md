# Inventory Service

Controla a quantidade disponível de cada produto em estoque. Bloqueia pedidos com quantidade superior ao disponível.

Porta: `8084`
Banco: `db_inventory`

## Estrutura inventory
 
```
ecommerce-microsservicos/
│
├── inventory-service/
│   ├── src/
│   │   ├── java/com/ecommerce/inventory
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

| Método | Rota                        | Descrição                   |
|--------|-----------------------------|-----------------------------|
| GET    | `/inventory/{productId}`    | Consulta estoque do produto |
| PUT    | `/inventory/{productId}`    | Atualiza quantidade         |

### GET /inventory/{productId}
**Response 200:**
```json
{ "productid": 1, "quantity": 50 }
```
**Response 404:**
```json
{ "error": "Produto não encontrado no estoque" }
```

### PUT /inventory/{productId}
**Request:**
```json
{ "quantity": 30 }
```
**Response 200:**
```json
{ "productid": 1, "status": "ATUALIZADO" }
```

## Swagger UI
```
http://localhost:8084/swagger-ui.html
```

## Como executar

#### 1. Crie o banco
[Configuração do Banco](https://github.com/DeboraVicente/p1_e-commerce/blob/main/README.md#configura%C3%A7%C3%A3o-dos-bancos-de-dados)

#### 2. Esteja no projeto
`cd .\inventory-service\`

#### 3. Execute
`mvn spring-boot:run`