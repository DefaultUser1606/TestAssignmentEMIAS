package OrderService.Repositories;

import OrderService.DatabaseModels.Order;
import OrderService.DatabaseModels.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

}
