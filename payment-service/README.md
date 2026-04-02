# Payment Service

Processa pagamentos de pedidos. O próprio serviço define o resultado do pagamento (APROVADO ou RECUSADO).

Porta: `8085`  
Banco: `db_payment`

# Dependências (devem estar no ar antes de subir este serviço)

| Serviço        | URL                   |
|----------------|------------------------|
| Order Service  | http://localhost:8083  |

# Estrutura payment

```
ecommerce-microsservicos/
│
├── payment-service/
│ ├── src/
│ │ ├── java/com/ecommerce/payment
│ │ │ ├── controller
│ │ │ ├── dto
│ │ │ ├── model
│ │ │ ├── repository
│ │ │ └── service
│ │ ├── resources/
│ │ │ └── application.properties
│ ├── pom.xml
│ └── README.md
```

# Fluxo interno de processamento de pagamento

```
POST /payment
  ├── recebe orderId
  ├── define status do pagamento (APROVADO ou RECUSADO)
  ├── persiste no banco
  └── retorna resultado
```

# Swagger
http://localhost:8085/swagger-ui.html

# Como executar
mysql -u root -p -e "CREATE DATABASE db_payment;"
mvn spring-boot:run


