package by.it.academy.shop.dtos.purchase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Обработка запроса по Id покупки.
 * Присутствует валидация по полю.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdPurchaseRequest {

    @NotNull
    private UUID id;
}
