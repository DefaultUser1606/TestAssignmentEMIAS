package OrderService.WebModels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class NewOrderRequest {
    @NotBlank
    public final String FirstName;
    @NotBlank
    public final String Patronymic;
    @NotBlank
    public final String LastName;
    @Min(1)
    public final int GiftType;

    public NewOrderRequest(
            final String firstName,
            final String patronymic,
            final String lastName,
            final int giftType) {
        FirstName = firstName;
        Patronymic = patronymic;
        LastName = lastName;
        GiftType = giftType;
    }
}
