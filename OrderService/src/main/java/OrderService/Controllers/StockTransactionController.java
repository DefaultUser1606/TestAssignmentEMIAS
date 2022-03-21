package OrderService.Controllers;

import OrderService.DatabaseModels.StockTransaction;
import OrderService.DatabaseModels.SupplierOrder;
import OrderService.Repositories.SupplierOrderRepository;
import OrderService.Services.StockTransactionCreator;
import OrderService.WebModels.NewStockTransactionRequest;
import OrderService.WebModels.ResponseWithMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class StockTransactionController {
    private final StockTransactionCreator StockTransactionCreator;
    private final SupplierOrderRepository SupplierOrderRepository;

    StockTransactionController(
            final StockTransactionCreator stockTransactionCreator,
            final SupplierOrderRepository supplierOrderRepository) {
        StockTransactionCreator = stockTransactionCreator;
        SupplierOrderRepository = supplierOrderRepository;
    }

    @PostMapping("/stockTransactions")
    ResponseEntity<ResponseWithMessage<StockTransaction>> CreateStockTransactions(
            @RequestBody @Valid final NewStockTransactionRequest request) {
        SupplierOrder supplierOrder = SupplierOrderRepository.findByExternalId(request.ExternalId);
        if(supplierOrder == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseWithMessage<>(
                        "Supplier order with specified external id not found",
                        null));

        if(supplierOrder.getStockTransactionId() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseWithMessage<>(
                            "Supplier order with specified external id is already fulfilled",
                            null));

        StockTransaction createdStockTransaction = StockTransactionCreator.Create(
                new StockTransaction(request.Type, request.Change));
        supplierOrder.setStockTransactionId(createdStockTransaction.getId());
        SupplierOrderRepository.save(supplierOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseWithMessage<>(null, createdStockTransaction));
    }
}
