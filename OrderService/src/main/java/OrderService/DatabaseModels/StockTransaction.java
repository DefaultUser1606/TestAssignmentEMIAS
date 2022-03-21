package OrderService.DatabaseModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "StockTransactions")
public class StockTransaction {
    private @Id @GeneratedValue long Id;
    private int Type;
    private int Change;

    public StockTransaction() {}

    public StockTransaction(int type, int change) {
        Type = type;
        Change = change;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getChange() {
        return Change;
    }

    public void setChange(int change) {
        Change = change;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
