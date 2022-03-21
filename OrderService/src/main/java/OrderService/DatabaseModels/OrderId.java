package OrderService.DatabaseModels;

import java.io.Serializable;
import java.util.Objects;

public class OrderId implements Serializable {
    private long ChildId;
    private long StockTransactionId;

    OrderId() {}

    public OrderId(final long childId, final long stockTransactionId) {
        ChildId = childId;
        StockTransactionId = stockTransactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return ChildId == orderId.ChildId &&
                StockTransactionId == orderId.StockTransactionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ChildId, StockTransactionId);
    }
}
