package by.it.academy.shop.repositories.purchase;

import by.it.academy.shop.entities.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Методы соединения с базой данных.
 * Обработка сущности Purchase.
 */

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    /**
     * Получение покупок из базы данных по Id пользователя.
     */
    List<Purchase> getByUserId(UUID id);
}
