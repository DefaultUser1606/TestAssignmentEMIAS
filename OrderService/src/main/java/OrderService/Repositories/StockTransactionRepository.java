package OrderService.Repositories;

import OrderService.DatabaseModels.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
    @Query("SELECT COALESCE(SUM(ST.Change), 0) FROM StockTransaction ST WHERE ST.Type = ?1")
    public int GetCurrentStock(int type);
}
