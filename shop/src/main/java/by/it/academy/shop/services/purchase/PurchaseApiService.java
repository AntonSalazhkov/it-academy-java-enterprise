package by.it.academy.shop.services.purchase;

import by.it.academy.shop.dtos.purchase.requests.AddPurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.IdPurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.ShowUserPurchaseRequest;
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
    public Purchase addPurchase(AddPurchaseRequest addPurchaseRequest) {
        Purchase purchase = buildPurchase(addPurchaseRequest);
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> showUserPurchase(ShowUserPurchaseRequest showUserPurchaseRequest) {
        List<Purchase> purchases = purchaseRepository
                .getByUserIdAndPurchaseStatus(showUserPurchaseRequest.getUserId(), PurchaseStatus.PROCESSING);
        if (!purchases.isEmpty()) {
            return purchases;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @Override
    public boolean makePurchase(IdPurchaseRequest idPurchaseRequest) {
        Purchase purchase = purchaseRepository.findById(idPurchaseRequest.getId()).orElseThrow(EntityNotFoundException::new);
        int productQuantity = purchase.getProductQuantity();

        Product product = productRepository.findById(purchase.getProduct().getId()).orElseThrow(EntityNotFoundException::new);

        if (product.getInStock() < productQuantity || purchase.getPurchaseStatus().equals(PurchaseStatus.BOUGHT)) {
            return false;
        } else {
            product.setInStock(product.getInStock() - productQuantity);
            purchase.setPurchaseStatus(PurchaseStatus.BOUGHT);
            productRepository.save(product);
            purchaseRepository.save(purchase);
            return true;
        }
    }

    private Purchase buildPurchase(AddPurchaseRequest request) {
        return Purchase.builder()
                .user(userRepository.findById(request.getUserId()).orElseThrow(EntityNotFoundException::new))
                .product(productRepository.findById(request.getProductId()).orElseThrow(EntityNotFoundException::new))
                .productQuantity(Integer.parseInt(request.getProductQuantity()))
                .localDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .purchaseStatus(PurchaseStatus.PROCESSING)
                .build();
    }
}
