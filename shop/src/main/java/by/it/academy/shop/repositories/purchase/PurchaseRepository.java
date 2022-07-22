package by.it.academy.shop.repositories.purchase;

import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.entities.purchase.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Методы соединения с базой данных.
 * Обработка сущности Purchase.
 */

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    /**
     * Получение покупок из базы данных по Id пользователя и находящихся в процессе обработки.
     */
    List<Purchase> getByUserIdAndPurchaseStatus(UUID id, PurchaseStatus purchaseStatus);
}
