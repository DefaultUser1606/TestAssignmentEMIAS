package OrderService.Services;

import OrderService.DatabaseModels.StockTransaction;
import OrderService.Repositories.StockTransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class StockTransactionCreator {
    private StockTransactionRepository StockTransactionRepository;

    StockTransactionCreator(final StockTransactionRepository stockTransactionRepository) {
        StockTransactionRepository = stockTransactionRepository;
    }

    public StockTransaction Create(StockTransaction stockTransaction) {
        return StockTransactionRepository.save(stockTransaction);
    }
}
