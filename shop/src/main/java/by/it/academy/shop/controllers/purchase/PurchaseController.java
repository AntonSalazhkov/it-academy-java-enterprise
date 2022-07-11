package by.it.academy.shop.controllers.purchase;

import by.it.academy.shop.dtos.purchase.requests.AddPurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.IdPurchaseRequest;
import by.it.academy.shop.dtos.purchase.requests.ShowUserPurchaseRequest;
import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.services.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер обработки покупок.
 * Метод addPurchase прослушивает адресс /add-purchase, в случае корректности введеных данных, добавит новую покупку в базу дынных.
 * По результатам добавления вернет новую покупку или вернет null в случае не успешного добавления.
 * <p>
 * Метод viewUserPurchase прослушивает адресс /view-user-purchase, принимает поступающий "id" пользователя в виде UUID,
 * производит поиск совпадения в базе данных.
 * По результатам поиска вернет все имеющиеся у пользователя покупки или пустой список в случае не успешного поиска.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @RequestMapping("/add-purchase")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase addPurchase(@RequestBody @Validated AddPurchaseRequest addPurchaseRequest) {
        return purchaseService.addPurchase(addPurchaseRequest);
    }

    @RequestMapping("/view-user-purchase")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Purchase> viewUserPurchase(@RequestBody ShowUserPurchaseRequest showUserPurchaseRequest) {
        return purchaseService.showUserPurchase(showUserPurchaseRequest);
    }

    @RequestMapping("/update-purchase")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean updatePurchase(@RequestBody IdPurchaseRequest idPurchaseRequest) {
        return purchaseService.updatePurchase(idPurchaseRequest);
    }
}
