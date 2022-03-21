package ToySupplier.DatabaseModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue
    private long Id;
    private int Type;
    private boolean Fulfilled;

    public Order() {}

    public Order(int type, boolean fulfilled) {
        Type = type;
        Fulfilled = fulfilled;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public boolean isFulfilled() {
        return Fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        Fulfilled = fulfilled;
    }
}
