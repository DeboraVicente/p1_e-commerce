# Payment Service

Responsável por processar pagamentos de pedidos. Recebe um `orderId` e um `status`, persiste no banco e retorna o resultado do pagamento.

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
├── Recebe orderId e status
├── Persiste pagamento no banco
└── Retorna dados do pagamento (id, orderId, status)
```

# Swagger
http://localhost:8085/swagger-ui.html

# Como executar
mysql -u root -p -e "CREATE DATABASE db_payment;"
mvn spring-boot:run


