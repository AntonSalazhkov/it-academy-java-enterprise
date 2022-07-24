package by.it.academy.shop.dtos.purchase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Запрос по отображению покупок конкретного пользователя.
 * Присутствует валидация по полю.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPurchaseRequest {

    @NotNull
    private UUID userId;
}
