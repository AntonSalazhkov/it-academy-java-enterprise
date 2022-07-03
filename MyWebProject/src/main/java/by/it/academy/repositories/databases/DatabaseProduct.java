package by.it.academy.repositories.databases;

import by.it.academy.constants.DatabaseRequests;
import by.it.academy.entities.product.Product;
import by.it.academy.entities.product.ProductCategories;
import by.it.academy.entities.product.ProductColours;
import by.it.academy.entities.product.ProductType;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j
public class DatabaseProduct extends Database {

    private List<Product> products;
    private Product product;

    public DatabaseProduct() {
        super();
        this.products = getProductsFromDatabase();
    }

    public DatabaseProduct(String categoryType, String[] userChoices) {
        super();
        this.products = getProductsFromDatabase(categoryType, userChoices);
    }

    public DatabaseProduct(int productId) {
        super();
        this.product = getProductsFromDatabaseById(productId);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct() {
        return product;
    }

    private List<Product> getProductsFromDatabase() {

        List<Product> products = new ArrayList<>();
        String query = DatabaseRequests.SELECT_ALL_PRODUCTS;

        super.setDatabaseConnection(query);

        try {
            while (getResultSet().next()) {
                products.add(
                        new Product(getResultSet().getInt(1), getResultSet().getString(2),
                                getResultSet().getString(3), getResultSet().getInt(4),
                                getResultSet().getInt(5), getResultSet().getInt(6),
                                getResultSet().getString(7), getResultSet().getString(8),
                                getResultSet().getInt(9), getResultSet().getInt(10)));
            }
        } catch (SQLException sqlEx) {
            log.info("Get products in database: " + sqlEx);
        } finally {
            super.closeStreams();
        }
        return products;
    }

    private List<Product> getProductsFromDatabase(String categoryType, String[] userChoices) {

        List<Product> products = new ArrayList<>();

        for (String userChoice : userChoices) {

            String query = DatabaseRequests.SELECT_PRODUCTS_BY_TYPE +
                    "where " + categoryType + ".name = '" + userChoice + "'";

            super.setDatabaseConnection(query);

            try {
                while (getResultSet().next()) {
                    products.add(
                            new Product(getResultSet().getInt(1), getResultSet().getString(2),
                                    getResultSet().getString(3), ProductCategories.valueOf(getResultSet().getString(4).toUpperCase()),
                                    ProductType.valueOf(getResultSet().getString(5).toUpperCase()),
                                    ProductColours.valueOf(getResultSet().getString(6).toUpperCase()),
                                    getResultSet().getString(7), getResultSet().getString(8),
                                    getResultSet().getInt(9), getResultSet().getInt(10)));
                }
            } catch (SQLException sqlEx) {
                log.info("Get products in database: " + sqlEx);
            } finally {
                super.closeStreams();
            }
        }
        return products;
    }

    private Product getProductsFromDatabaseById(int productId) {

        Product product = null;
        String query = DatabaseRequests.SELECT_ALL_WHERE_ID + productId;

        super.setDatabaseConnection(query);

        try {
            while (getResultSet().next()) {
                product = new Product(getResultSet().getInt(1), getResultSet().getString(2),
                        getResultSet().getString(3), getResultSet().getInt(4),
                        getResultSet().getInt(5), getResultSet().getInt(6),
                        getResultSet().getString(7), getResultSet().getString(8),
                        getResultSet().getInt(9), getResultSet().getInt(10));
            }
        } catch (SQLException sqlEx) {
            log.info("Get products in database: " + sqlEx);
        } finally {
            super.closeStreams();
        }
        return product;
    }

    public boolean updateStockBalance(Product currentProduct, String selectedQuantityProducts) {

        final int minimumPossibleNumber = 0;

        if (NumberUtils.isNumber(selectedQuantityProducts) && Integer.parseInt(selectedQuantityProducts) > minimumPossibleNumber
                && Integer.parseInt(selectedQuantityProducts) <= currentProduct.getInStock()) {

            int newBalance = currentProduct.getInStock() - Integer.parseInt(selectedQuantityProducts);

            String query = DatabaseRequests.UPDATE_PRODUCTS + newBalance + " where id = " + currentProduct.getId();

            super.setDatabaseConnection(query);
            super.closeStreams();
            return true;

        } else {
            return false;
        }
    }

    public boolean addProductToDatabase(Product product) {

        if (!Objects.equals("", product.getImagePath()) && !Objects.equals("", product.getName())
                && Objects.nonNull(product.getProductCategories()) && Objects.nonNull(product.getProductType())
                && Objects.nonNull(product.getProductColours()) && !Objects.equals("", product.getProductDetails())
                && !Objects.equals("", product.getSizeClothes())
                && !Objects.equals(0, product.getPrice()) && !Objects.equals(0, product.getInStock())) {

            String query = DatabaseRequests.INSERT_INTO_PRODUCTS + "('" + product.getImagePath()
                    + "','" + product.getName() + "'," + product.getProductCategories().ordinal() + ","
                    + product.getProductType().ordinal() + "," + product.getProductColours().ordinal()
                    + ",'" + product.getProductDetails() + "','" + product.getSizeClothes() + "',"
                    + product.getPrice() + "," + product.getInStock() + ")";

            super.setDatabaseConnection(query);
            super.closeStreams();
            return true;

        } else {
            return false;
        }
    }

    public void newBalanceInStock(int newBalance, int productId) {
        String query = DatabaseRequests.UPDATE_PRODUCTS + newBalance + " where id = " + productId;

        super.setDatabaseConnection(query);
        super.closeStreams();
    }

    public void deleteProduct(int currentProductId) {
        String query = DatabaseRequests.DELETE_PRODUCTS + currentProductId;

        super.setDatabaseConnection(query);
        super.closeStreams();
    }
}
