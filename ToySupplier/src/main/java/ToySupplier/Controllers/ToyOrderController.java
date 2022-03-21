package ToySupplier.Controllers;

import ToySupplier.DatabaseModels.Order;
import ToySupplier.Repositories.OrderRepository;
import ToySupplier.WebModels.NewStockTransactionRequest;
import ToySupplier.WebModels.NewStockTransactionResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Min;
import java.util.concurrent.CompletableFuture;

@RestController
@Validated
public class ToyOrderController {
    private static final String ShipToysEndpoint = "http://localhost:8081/stockTransactions";

    private final RestTemplate RestTemplate;
    private final OrderRepository OrderRepository;

    ToyOrderController(
            final RestTemplateBuilder restTemplateBuilder,
            final OrderRepository orderRepository) {
        RestTemplate = restTemplateBuilder.build();
        OrderRepository = orderRepository;
    }

    @PostMapping("/ToySupplier/{toyType}")
    ResponseEntity<Long> CreateNewOrder(
            @PathVariable("toyType") @Min(1) final int toyType,
            @RequestParam @Min(1) int quantity) {
        final Order createdOrder = OrderRepository.save(new Order(toyType, false));
        CompletableFuture.runAsync(() -> {
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            createdOrder.setFulfilled(true);
            Order updatedOrder = OrderRepository.save(createdOrder);

            NewStockTransactionRequest stockRequest = new NewStockTransactionRequest(toyType, quantity, updatedOrder.getId());
            RestTemplate.postForObject(ShipToysEndpoint, stockRequest, NewStockTransactionResponse.class);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder.getId());
    }
}
