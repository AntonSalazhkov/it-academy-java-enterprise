package by.it.academy.shop.services.purchase;

import by.it.academy.shop.dtos.purchase.requests.AddPurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.IdPurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.ShowUserPurchaseRequest;
import by.it.academy.shop.entities.purchase.Purchase;

import java.util.List;

/**
 * Сервис обработки покупки.
 */

public interface PurchaseService {

    /**
     * Добавление новой покупки
     */
    Purchase addPurchase(AddPurchaseRequest addPurchaseRequest);

    /**
     * Просмотр покупки пользователем
     */
    List<Purchase> showUserPurchase(ShowUserPurchaseRequest showUserPurchaseRequest);

    /**
     * Совершение покупки (списание продукта и установка нового статуса покупки)
     */
    boolean makePurchase(IdPurchaseRequest idPurchaseRequest);
}
