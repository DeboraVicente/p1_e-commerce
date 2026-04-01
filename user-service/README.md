# User Service

Responsável por gerenciar usuários do sistema. Permite criar usuários e consultar por ID.

Porta: `8082`  
Banco: `db_user`

# Dependências (devem estar no ar antes de subir este serviço)


| Serviço           | URL                   |
|-------------------|------------------------|
| Order Service     | http://localhost:8083  |
| Catalog Service   | http://localhost:8081  |
| Inventory Service | http://localhost:8084  |
| Payment Service   | http://localhost:8085  |

# Estrutura user

```
ecommerce-microsservicos/
│
├── user-service/
│ ├── src/
│ │ ├── java/com/ecommerce/user
│ │ │ ├── controller
│ │ │ ├── model
│ │ │ ├── repository
│ │ │ └── service
│ │ ├── resources/
│ │ │ └── application.properties
│ ├── pom.xml
│ └── README.md
```
# Endpoints

| Método | Rota            | Descrição                   |
|--------|------------------|------------------------------|
| POST   | `/users`         | Cria um novo usuário         |
| GET    | `/users/{id}`    | Consulta usuário por ID      |


# Fluxo interno de criação do usuário

```
POST /users
├── Recebe name e email
├── Persiste usuário no banco
└── Retorna id e status SUCCESS
```

# Swagger UI
http://localhost:8082/swagger-ui.html

# Como executar
mysql -u root -p -e "CREATE DATABASE db_user;"
mvn spring-boot:run

# Configuração (application.properties)

server.port=8082

spring.application.name=user-service

spring.datasource.url=jdbc:mysql://localhost:3306/db_user
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

catalog.service.url=http://localhost:8081
inventory.service.url=http://localhost:8084
payment.service.url=http://localhost:8085
orders.service.url=http://localhost:8083
user.service.url=http://localhost:8082

springdoc.swagger-ui.path=/swagger-ui.html
