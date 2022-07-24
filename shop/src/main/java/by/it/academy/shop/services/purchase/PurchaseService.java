package by.it.academy.shop.services.purchase;

import by.it.academy.shop.dtos.purchase.requests.CreatePurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.PurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.UserPurchaseRequest;
import by.it.academy.shop.entities.purchase.Purchase;

import java.util.List;

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
    List<Purchase> showUserPurchase(UserPurchaseRequest showUserPurchaseRequest);

    /**
     * Совершение покупки (списание продукта и установка нового статуса покупки)
     */
    boolean makePurchase(PurchaseRequest idPurchaseRequest);
}
