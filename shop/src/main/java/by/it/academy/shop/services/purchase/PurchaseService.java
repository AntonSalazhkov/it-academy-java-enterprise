package by.it.academy.shop.services.purchase;

import by.it.academy.shop.dtos.purchase.requests.CreatePurchaseRequest;
import by.it.academy.shop.entities.purchase.Purchase;

import java.util.List;
import java.util.UUID;

/**
 * Сервис обработки покупки.
 */

public interface PurchaseService {

    /**
     * Добавление новой покупки
     */
    Purchase addPurchase(CreatePurchaseRequest addPurchaseRequest);

    /**
     * Просмотр покупки пользователем
     */
    List<Purchase> showUserPurchase(UUID id);

    /**
     * Совершение покупки (списание продукта и установка нового статуса покупки)
     */
    boolean makePurchase(UUID id);
}
