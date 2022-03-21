package OrderService.Controllers;

import OrderService.DatabaseModels.Order;
import OrderService.DatabaseModels.SupplierOrder;
import OrderService.Repositories.StockTransactionRepository;
import OrderService.DatabaseModels.Child;
import OrderService.Repositories.ChildRepository;
import OrderService.Repositories.SupplierOrderRepository;
import OrderService.WebModels.ChildBehaviourRatingResponse;
import OrderService.Services.OrderCreator;
import OrderService.WebModels.NewOrderRequest;
import OrderService.WebModels.ResponseWithMessage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class OrderController {
    private static final String ChildBehaviourRatingServiceURL = "http://localhost:8082/childBehaviourRating/{childId}";
    private static final String ToySupplierServiceURL = "http://localhost:8083/ToySupplier/{toyType}?quantity={quantity}";
    private static final int StockRefillThreshold = 10;

    private final ChildRepository ChildRepository;
    private final StockTransactionRepository StockTransactionRepository;
    private final SupplierOrderRepository SupplierOrderRepository;
    private final OrderCreator OrderCreator;
    private final RestTemplate RestTemplate;

    OrderController(
            final ChildRepository childRepository,
            final StockTransactionRepository stockTransactionRepository,
            final SupplierOrderRepository supplierOrderRepository,
            final OrderCreator orderCreator,
            final RestTemplateBuilder restTemplateBuilder) {
        ChildRepository = childRepository;
        StockTransactionRepository = stockTransactionRepository;
        SupplierOrderRepository = supplierOrderRepository;
        OrderCreator = orderCreator;
        RestTemplate = restTemplateBuilder.build();
    }

    @PostMapping("/orders")
    ResponseEntity<ResponseWithMessage<Order>> CreateNewOrder(
            @RequestBody @Valid final NewOrderRequest request) {
        List<Child> children = ChildRepository.Find(request.FirstName, request.Patronymic, request.LastName);

        if(children == null || children.size() < 1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseWithMessage<>("Child not found", null));

        if(children.size() > 1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseWithMessage<>("Multiple children found", null));

        Child child = children.get(0);

        ChildBehaviourRatingResponse childBehaviourRating = RestTemplate.getForObject(
                ChildBehaviourRatingServiceURL,
                ChildBehaviourRatingResponse.class,
                child.getId());

        if(childBehaviourRating == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseWithMessage<>("Could not retrieve child behaviour rating", null));

        if(childBehaviourRating.Message != null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseWithMessage<>(childBehaviourRating.Message, null));

        if(!childBehaviourRating.IsGood) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseWithMessage<>("Insufficient behaviour rating", null));
        }

        int currentStock = StockTransactionRepository.GetCurrentStock(request.GiftType);

        if(currentStock < StockRefillThreshold)
        {
            boolean pendingOrderExits = SupplierOrderRepository.GetPendingByType(request.GiftType);
            if(!pendingOrderExits){
                int toOrder = StockRefillThreshold - currentStock;
                OrderMoreToys(request.GiftType, toOrder);
            }
        }

        if(currentStock < 1)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseWithMessage<>("Out of stock", null));

        Order order = OrderCreator.CreateOrder(request.GiftType, child);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseWithMessage<>(null, order));
    }

    private void OrderMoreToys(final int toyType, final int toOrder) {
        Long responseId = RestTemplate.postForObject(
                ToySupplierServiceURL, null, Long.class, toyType, toOrder);
        if(responseId == null) {
            System.out.println("Could not reach supplier to order more toys");
            return;
        }
        SupplierOrderRepository.save(new SupplierOrder(responseId, toyType, null));
    }
}
