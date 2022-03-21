package OrderService.Repositories;

import OrderService.DatabaseModels.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {
    public SupplierOrder findByExternalId(long externalId);
    @Query(value = """
    SELECT CASE WHEN EXISTS(
    SELECT 1 FROM Supplier_Orders WHERE Stock_Transaction_Id IS NULL)
    THEN true ELSE false END""", nativeQuery = true)
    public boolean GetPendingByType(int type);
}
