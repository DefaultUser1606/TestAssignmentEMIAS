package OrderService.Services;

import OrderService.DatabaseModels.Order;
import OrderService.DatabaseModels.StockTransaction;
import OrderService.Repositories.OrderRepository;
import OrderService.Repositories.StockTransactionRepository;
import OrderService.DatabaseModels.Child;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderCreator {
    private final OrderRepository OrderRepository;
    private final StockTransactionRepository StockTransactionRepository;

    OrderCreator(final OrderRepository orderRepository, final StockTransactionRepository stockTransactionRepository) {
        OrderRepository = orderRepository;
        StockTransactionRepository = stockTransactionRepository;
    }

    public Order CreateOrder(final int type, @NotNull final Child child) {
        StockTransaction stockTransaction = StockTransactionRepository.save(new StockTransaction(type, -1));
        return OrderRepository.save(new Order(child.getId(), stockTransaction.getId()));
    }
}
