package com.ecommerce.orders.services;

import com.ecommerce.orders.dto.OrderDto;
import com.ecommerce.orders.models.Order;
import com.ecommerce.orders.models.OrderItem;
import com.ecommerce.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${catalog.service.url}")
    private String catalogUrl;

    @Value("${inventory.service.url}")
    private String inventoryUrl;

    @Value("${payment.service.url}")
    private String paymentUrl;

    public OrderDto.Response create(OrderDto.Request request) {

        List<OrderItem> itemsToSave = new ArrayList<>();
        double total = 0.0;

        for (OrderDto.ItemRequest itemReq : request.getItems()) {

            String productUrl = catalogUrl + "/product/" + itemReq.getProductid();
            OrderDto.ProductResponse product;
            try {
                product = restTemplate.getForObject(productUrl, OrderDto.ProductResponse.class);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao consultar produto " + itemReq.getProductid()
                        + " no Catalog Service: " + e.getMessage());
            }

            if (product == null) {
                throw new RuntimeException("Produto " + itemReq.getProductid() + " não encontrado no catálogo");
            }

            String invUrl = inventoryUrl + "/inventory/" + itemReq.getProductid();
            OrderDto.InventoryResponse inventory;
            try {
                inventory = restTemplate.getForObject(invUrl, OrderDto.InventoryResponse.class);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao consultar estoque do produto " + itemReq.getProductid()
                        + ": " + e.getMessage());
            }

            if (inventory == null || inventory.getQuantity() < itemReq.getQuantity()) {
                int disponivel = inventory != null ? inventory.getQuantity() : 0;
                throw new RuntimeException("Estoque insuficiente para o produto " + itemReq.getProductid()
                        + ". Disponível: " + disponivel + ", solicitado: " + itemReq.getQuantity());
            }

            double unitPrice = product.getValue();
            total += unitPrice * itemReq.getQuantity();

            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getProductid());
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(unitPrice);
            itemsToSave.add(item);
        }

        Order order = new Order();
        order.setUserId(request.getUserid());
        order.setStatus("CRIADO");
        order.setPaymentAmount(total);

        for (OrderItem item : itemsToSave) {
            item.setOrder(order);
        }
        order.setItems(itemsToSave);

        Order saved = repository.save(order);

        OrderDto.PaymentRequest paymentRequest = new OrderDto.PaymentRequest();
        paymentRequest.setOrderId(saved.getId());
        paymentRequest.setStatus("PENDENTE");

        OrderDto.PaymentResponse paymentResponse;
        try {
            paymentResponse = restTemplate.postForObject(
                    paymentUrl + "/payment",
                    paymentRequest,
                    OrderDto.PaymentResponse.class
            );
        } catch (Exception e) {
            saved.setStatus("CANCELADO");
            repository.save(saved);
            throw new RuntimeException("Erro ao processar pagamento: " + e.getMessage());
        }

        boolean aprovado = paymentResponse != null && "APROVADO".equals(paymentResponse.getStatus());
        saved.setStatus(aprovado ? "PAGO" : "CANCELADO");
        repository.save(saved);

        if (aprovado) {
            for (OrderItem item : itemsToSave) {
                String decrementUrl = inventoryUrl + "/inventory/" + item.getProductId()
                        + "/decrement?quantity=" + item.getQuantity();
                try {
                    restTemplate.put(decrementUrl, null);
                } catch (Exception e) {
                    System.err.println("Aviso: falha ao decrementar estoque do produto "
                            + item.getProductId() + ": " + e.getMessage());
                }
            }
        }

        OrderDto.Response response = new OrderDto.Response();
        response.setId(saved.getId());
        response.setStatus(saved.getStatus());
        response.setPaymentAmount(saved.getPaymentAmount());
        return response;
    }

    public OrderDto.Response findById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        OrderDto.Response r = new OrderDto.Response();
        r.setId(order.getId());
        r.setStatus(order.getStatus());
        r.setPaymentAmount(order.getPaymentAmount());
        return r;
    }
}