package by.it.academy.services.product;

import by.it.academy.constants.Messages;
import by.it.academy.entities.product.Product;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Сервис расчета стоимости продуктов в корзине.
 */

public class ValueBasketApiService {

    public String calculateTotalCost(Product currentProduct, String selectedProductsQuantity) {

        final int minimumPossibleNumber = 0;
        int totalCost = 0;

        if (NumberUtils.isNumber(selectedProductsQuantity) && Integer.parseInt(selectedProductsQuantity) > minimumPossibleNumber
                && Integer.parseInt(selectedProductsQuantity) <= currentProduct.getInStock()) {

            totalCost = currentProduct.getPrice() * Integer.parseInt(selectedProductsQuantity);
            return totalCost + " $";
        } else {
            return Messages.INCORRECT_QUANTITY_PRODUCTS;
        }
    }
}
