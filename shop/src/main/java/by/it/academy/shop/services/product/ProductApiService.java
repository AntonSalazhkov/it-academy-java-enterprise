package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.AddProductRequest;
import by.it.academy.shop.dtos.product.requests.IdProductRequest;
import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;

import by.it.academy.shop.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * Реализация сервиса обработки продукта.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApiService implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        final Product product = buildProduct(addProductRequest);
        return productRepository.save(product);
    }

    @Override
    public Product showProductById(IdProductRequest idProductRequest) {
        return productRepository.findById(idProductRequest.getId()).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Product> showProduct(ShowProductRequest showProductRequest) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(productRepository, showProductRequest);
        return productSearch.getProducts();
    }

    @Transactional
    @Override
    public Product updateProduct(UpdateProductRequest updateProductRequest) {
        productRepository.findById(updateProductRequest.getId()).orElseThrow(EntityNotFoundException::new);
        Product product = buildUpdateProduct(updateProductRequest);

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public boolean clearStockProduct(IdProductRequest idProductRequest) {
        Product product = productRepository.findById(idProductRequest.getId()).orElseThrow(EntityNotFoundException::new);
        product.setInStock(0);
        productRepository.save(product);
        return true;
    }

    private Product buildProduct(AddProductRequest request) {
        return Product.builder()
                .imagePath(request.getImagePath())
                .name(request.getName())
                .productCategory(request.getProductCategory())
                .productType(request.getProductType())
                .productColour(request.getProductColour())
                .productDetails(request.getProductDetails())
                .sizeClothes(request.getSizeClothes())
                .price(Integer.parseInt(request.getPrice()))
                .inStock(Integer.parseInt(request.getInStock()))
                .build();
    }

    private Product buildUpdateProduct(UpdateProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .imagePath(request.getImagePath())
                .name(request.getName())
                .productCategory(request.getProductCategory())
                .productType(request.getProductType())
                .productColour(request.getProductColour())
                .productDetails(request.getProductDetails())
                .sizeClothes(request.getSizeClothes())
                .price(Integer.parseInt(request.getPrice()))
                .inStock(Integer.parseInt(request.getInStock()))
                .build();
    }
}
