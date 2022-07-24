package by.it.academy.shop.controllers.purchase;

import by.it.academy.shop.dtos.purchase.requests.CreatePurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.PurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.UserPurchaseRequest;
import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.services.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер обработки покупок.
 * Метод addPurchase прослушивает адресс /purchases/create, в случае корректности введеных данных, добавит новую покупку в базу дынных.
 * По результатам добавления вернет новую покупку или вернет null в случае не успешного добавления.
 * <p>
 * Метод viewUserPurchase прослушивает адресс /purchases/basket, принимает поступающий "id" пользователя в виде UUID,
 * производит поиск имеющихся покупок у пользователя в базе данных.
 * По результатам поиска вернет все имеющиеся у пользователя покупки или сообщение о не нахождении у пользователя созданных покупок.
 * <p>
 * Метод makePurchase прослушивает адресс /purchases, принимает поступающий "id" покупки в виде UUID.
 * По результатам обработки установит покупке статут "купленно" и спишет с остатка продуктов на складе указанное количество
 * продуктов в покупке, при этом вернет true. В случае некорректных запросов вернет сообщение о не нахождении покупки или
 * false в случае не успешной транзакции, или в случае если количества продуктов в покупке превышает количество продуктов на складе.
 */

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @RequestMapping("/create")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase addPurchase(@RequestBody @Validated CreatePurchaseRequest addPurchaseRequest) {
        return purchaseService.addPurchase(addPurchaseRequest);
    }

    @RequestMapping("/basket")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Purchase> viewUserPurchase(@RequestBody UserPurchaseRequest showUserPurchaseRequest) {
        return purchaseService.showUserPurchase(showUserPurchaseRequest);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean makePurchase(@RequestBody PurchaseRequest idPurchaseRequest) {
        return purchaseService.makePurchase(idPurchaseRequest);
    }
}
