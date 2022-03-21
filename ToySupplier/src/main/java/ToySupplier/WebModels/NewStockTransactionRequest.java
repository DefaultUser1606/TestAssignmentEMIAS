package ToySupplier.WebModels;

public class NewStockTransactionRequest {
    public final int Type;
    public final int Change;
    public final long ExternalId;

    public NewStockTransactionRequest(final int type, final int change, final long externalId) {
        Type = type;
        Change = change;
        ExternalId = externalId;
    }
}
