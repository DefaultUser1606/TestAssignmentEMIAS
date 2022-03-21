package OrderService.WebModels;

public class ResponseWithMessage<T> {
    public final String Message;
    public final T ResultEntity;

    public ResponseWithMessage(final String message, final T resultEntity) {
        Message = message;
        ResultEntity = resultEntity;
    }
}
