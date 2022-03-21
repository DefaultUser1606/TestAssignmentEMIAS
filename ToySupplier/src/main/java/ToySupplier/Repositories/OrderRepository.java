package ToySupplier.Repositories;

import ToySupplier.DatabaseModels.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
