package by.it.academy.shop.dtos.purchase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

/**
 * Обработка запроса по добавлению покупки.
 * Присутствует валидация по полям.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPurchaseRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID productId;

    @Pattern(regexp = "\\d+")
    private String productQuantity;
}
