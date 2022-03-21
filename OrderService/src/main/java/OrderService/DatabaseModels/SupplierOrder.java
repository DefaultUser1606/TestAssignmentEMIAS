package OrderService.DatabaseModels;

import javax.persistence.*;

@Entity
@Table(name = "SupplierOrders")
public class SupplierOrder {
    @Id
    @GeneratedValue
    private long Id;
    @Column(unique=true)
    private long externalId;
    private int Type;
    private Long StockTransactionId;

    public SupplierOrder() {}

    public SupplierOrder(long externalId, int type, Long stockTransactionId) {
        this.externalId = externalId;
        Type = type;
        StockTransactionId = stockTransactionId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public Long getStockTransactionId() {
        return StockTransactionId;
    }

    public void setStockTransactionId(Long stockTransactionId) {
        StockTransactionId = stockTransactionId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getExternalId() {
        return externalId;
    }

    public void setExternalId(long externalId) {
        this.externalId = externalId;
    }
}
