# E-commerce — Java + MySQL + Swagger
 
Sistema de e-commerce distribuído construído com arquitetura de microsserviços, desenvolvido como entrega da **Prova Prática P1**.

## Estrutura do Repositório
 
```
ecommerce-microsservicos/
│
├── catalog-service/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── user-service/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── order-service/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── inventory-service/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── payment-service/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── postman/
│   └── ecommerce-collection.json
│
└── README.md
```

## Configuração dos Bancos de Dados
 
Crie um banco de dados separado para cada serviço. Execute os comandos abaixo no MySQL como root:
```
-- Database: db_user
CREATE DATABASE IF NOT EXISTS db_user;
USE db_user;
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Database: db_catalog
CREATE DATABASE IF NOT EXISTS db_catalog;
USE db_catalog;
CREATE TABLE catalog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    value DECIMAL(10,2) NOT NULL
);

-- Database: db_inventory
CREATE DATABASE IF NOT EXISTS db_inventory;
USE db_inventory;
CREATE TABLE inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    productid BIGINT NOT NULL,
    quantity INT NOT NULL
);

-- Database: db_order
CREATE DATABASE IF NOT EXISTS db_order;
USE db_order;
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    paymentAmount DECIMAL(10,2) NOT NULL
);

CREATE TABLE orders_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    orderid BIGINT NOT NULL,
    productid BIGINT NOT NULL,
    CONSTRAINT fk_order FOREIGN KEY (orderid) REFERENCES orders(id)
); 

-- Database: db_payment
CREATE DATABASE IF NOT EXISTS db_payment;
USE db_payment;
CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    orderid BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Database: db_inventory
CREATE DATABASE IF NOT EXISTS db_inventory;
USE db_inventory;

CREATE TABLE inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    productid BIGINT NOT NULL,
    quantity INT NOT NULL
);
```

## Regras Arquiteturais
 
- Cada serviço possui **seu próprio banco de dados** MySQL
- **Nenhum serviço acessa diretamente** o banco de outro
- Toda comunicação entre serviços é feita via **chamadas HTTP REST**
- Cada serviço pode ser **iniciado e parado de forma independente**
- Catalog Service **não conhece** estoque nem pedidos
- Inventory Service **rejeita** pedidos com quantidade superior ao disponível
 
---