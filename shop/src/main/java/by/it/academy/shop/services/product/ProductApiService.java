package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.AddProductRequest;
import by.it.academy.shop.dtos.product.requests.ShowDetailsRequest;
import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.entities.product.Product;

import by.it.academy.shop.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Реализация сервиса обработки продукта.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApiService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        final Product product = buildUser(request);
        return productRepository.save(product);
    }

    @Override
    public Product showProductById(ShowDetailsRequest showDetailsRequest) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(productRepository, showDetailsRequest);

        return productSearch.getProduct();
    }

    @Override
    public List<Product> showProduct(ShowProductRequest request) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(productRepository, request);

        return productSearch.getProducts();
    }

    private Product buildUser(AddProductRequest request) {
        return Product.builder()
                .imagePath(request.getImagePath())
                .name(request.getName())
                .productCategory(request.getProductCategory())
                .productType(request.getProductType())
                .productColour(request.getProductColour())
                .productDetails(request.getProductDetails())
                .sizeClothes(request.getSizeClothes())
                .price(request.getPrice())
                .inStock(request.getInStock())
                .build();
    }
}
