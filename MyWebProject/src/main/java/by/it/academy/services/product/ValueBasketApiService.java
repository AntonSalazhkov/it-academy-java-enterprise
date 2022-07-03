package by.it.academy.services.product;

import by.it.academy.constants.Messages;
import by.it.academy.entities.product.Product;

public class ValueBasketApiService {

    public String calculateTotalCost(Product currentProduct, String selectedProductsQuantity) {

        final int minimumPossibleNumber = 0;
        int totalCost = 0;
        int productQuantity = Integer.parseInt(selectedProductsQuantity);

        if (productQuantity > minimumPossibleNumber && productQuantity <= currentProduct.getInStock()) {

            totalCost = currentProduct.getPrice() * productQuantity;
            return totalCost + " $";
        } else {
            return Messages.INCORRECT_QUANTITY_PRODUCTS;
        }
    }
}
