package by.it.academy.shop.services.purchase;

import by.it.academy.shop.dtos.purchase.requests.CreatePurchaseRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.entities.purchase.PurchaseStatus;
import by.it.academy.shop.repositories.product.ProductRepository;
import by.it.academy.shop.repositories.purchase.PurchaseRepository;
import by.it.academy.shop.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Реализация сервиса обработки покупок.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseApiService implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Purchase addPurchase(CreatePurchaseRequest createPurchaseRequest) {
        Purchase purchase = buildPurchase(createPurchaseRequest);
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> showUserPurchase(UUID id) {
        List<Purchase> purchases = purchaseRepository
                .getByUserIdAndPurchaseStatus(id, PurchaseStatus.PROCESSING);
        if (!purchases.isEmpty()) {
            return purchases;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @Override
    public boolean makePurchase(UUID id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        int productQuantity = purchase.getProductQuantity();

        Product product = productRepository.findById(purchase.getProduct().getId()).orElseThrow(EntityNotFoundException::new);

        if (product.getQuantity() < productQuantity || purchase.getPurchaseStatus().equals(PurchaseStatus.BOUGHT)) {
            return false;
        } else {
            purchaseProcessing(product, productQuantity, purchase);
            return true;
        }
    }

    private void purchaseProcessing(Product product, int productQuantity, Purchase purchase) {

        product.setQuantity(product.getQuantity() - productQuantity);
        purchase.setPurchaseStatus(PurchaseStatus.BOUGHT);
        productRepository.save(product);
        purchaseRepository.save(purchase);
    }

    private Purchase buildPurchase(CreatePurchaseRequest request) {
        return Purchase.builder()
                .user(userRepository.findById(request.getUserId()).orElseThrow(EntityNotFoundException::new))
                .product(productRepository.findById(request.getProductId()).orElseThrow(EntityNotFoundException::new))
                .productQuantity(Integer.parseInt(request.getProductQuantity()))
                .localDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .purchaseStatus(PurchaseStatus.PROCESSING)
                .build();
    }
}
