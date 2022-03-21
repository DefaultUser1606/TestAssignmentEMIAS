package OrderService.WebModels;

import javax.validation.constraints.Min;

public class NewStockTransactionRequest {
    @Min(1)
    public final int Type;
    @Min(1)
    public final int Change;
    @Min(1)
    public final long ExternalId;

    public NewStockTransactionRequest(final int type, final int change, final long externalId) {
        Type = type;
        Change = change;
        ExternalId = externalId;
    }
}
