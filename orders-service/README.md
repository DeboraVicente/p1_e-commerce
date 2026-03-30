# Order Service

Orquestra a criação e o ciclo de vida dos pedidos. É o serviço central, responsável por coordenar as chamadas a Catálogo, Estoque e Pagamento.

Porta: `8083`
Banco: `db_order`

## Dependências (devem estar no ar antes de subir este serviço)

| Serviço           | URL                      |
|-------------------|--------------------------|
| Catalog Service   | http://localhost:8081    |
| Inventory Service | http://localhost:8084    |
| Payment Service   | http://localhost:8085    |

## Estrutura order
 
```
ecommerce-microsservicos/
│
├── order-service/
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

| Método | Rota           | Descrição              |
|--------|----------------|------------------------|
| POST   | `/orders`      | Cria novo pedido       |
| GET    | `/orders/{id}` | Consulta pedido por ID |

### POST /orders
**Request:**
```json
{
  "userid": 1,
  "items": [
    { "productid": 1, "quantity": 2 },
    { "productid": 2, "quantity": 1 }
  ]
}
```
**Response 201 (sucesso):**
```json
{ "id": 1, "status": "PAGO", "paymentAmount": 7150.00 }
```
**Response 400 (estoque insuficiente):**
```json
{ "error": "Estoque insuficiente para o produto 1. Disponível: 1, solicitado: 2" }
```

### GET /orders/{id}
**Response 200:**
```json
{ "id": 1, "status": "PAGO", "paymentAmount": 7150.00 }
```

---

## Fluxo interno de criação de pedido

```
POST /orders
  ├── Para cada item:
  │     ├── GET http://localhost:8081/product/{id}   → busca preço
  │     └── GET http://localhost:8084/inventory/{id} → verifica estoque
  │           └── se insuficiente → retorna 400
  ├── Persiste pedido com status CRIADO
  ├── POST http://localhost:8085/payment {orderId}
  │     └── retorna APROVADO ou RECUSADO
  ├── Atualiza status → PAGO ou CANCELADO
  └── Se PAGO: PUT http://localhost:8084/inventory/{id}/decrement
```

---

## Swagger UI
```
http://localhost:8083/swagger-ui.html
```

---

## Como executar

```bash
mysql -u root -p -e "CREATE DATABASE db_order;"
mvn spring-boot:run
```

## Configuração (application.properties)
```properties
server.port=8083
spring.datasource.url=jdbc:mysql://localhost:3306/db_order
spring.datasource.username=ecommerce
spring.datasource.password=ecommerce123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
springdoc.swagger-ui.path=/swagger-ui.html

catalog.service.url=http://localhost:8081
inventory.service.url=http://localhost:8084
payment.service.url=http://localhost:8085
```