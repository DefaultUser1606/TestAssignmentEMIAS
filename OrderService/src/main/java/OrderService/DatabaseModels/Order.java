package OrderService.DatabaseModels;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Orders")
@IdClass(OrderId.class)
public class Order {
    @Id
    private long ChildId;
    @Id
    private long StockTransactionId;

    public Order() {}

    public Order(long childId, long stockTransactionId) {
        ChildId = childId;
        StockTransactionId = stockTransactionId;
    }

    public long getChildId() {
        return ChildId;
    }

    public void setChildId(long childId) {
        ChildId = childId;
    }

    public long getStockTransactionId() {
        return StockTransactionId;
    }

    public void setStockTransactionId(long stockTransactionId) {
        StockTransactionId = stockTransactionId;
    }
}