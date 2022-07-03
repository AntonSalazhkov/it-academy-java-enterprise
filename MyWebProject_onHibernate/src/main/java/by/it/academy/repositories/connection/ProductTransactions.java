package by.it.academy.repositories.connection;

import by.it.academy.entities.product.Product;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.math.NumberUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Методы соединения с базой данных через JPA.
 * Обработка сущности Product.
 */

@Log4j
public class ProductTransactions {

    private EntityManager entityManager;

    public ProductTransactions() {
    }

    /**
     * Получение entity manager из текущей созданной сессии
     */
    public ProductTransactions(HttpServletRequest req) {
        entityManager = (EntityManager) req.getSession().getAttribute("sessionJPA");
    }


    /**
     * Получение List продуктов из базы данных
     */
    public List<Product> getProductsByJPA() {
        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
            final Root<Product> productRoot = productCriteriaQuery.from(Product.class);

            productCriteriaQuery.select(productRoot);
            products = entityManager.createQuery(productCriteriaQuery).getResultList();

            entityManager.getTransaction().commit();
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Exception get products in database (on getProductsByHibernate): " + e);
        }
        return products;
    }


    /**
     * Получение List продуктов из базы данных, с учетом совпадений с выборами пользователя
     */
    public List<Product> getProductsByJPA(String categoryType, List userChoices) {

        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
            final Root<Product> productRoot = productCriteriaQuery.from(Product.class);

            userChoices
                    .forEach(userChoice -> productCriteriaQuery.select(productRoot)
                            .where(criteriaBuilder.equal(productRoot.get(categoryType), userChoice)));

            products = entityManager.createQuery(productCriteriaQuery).getResultList();

            entityManager.getTransaction().commit();
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Exception get products in database (on getProductsByHibernate (x, y[])): " + e);
        }
        return products;
    }


    /**
     * Получение Product из базы данных по Id
     */
    public Product getProductsByJPAThroughId(String productId) {
        Product product = null;

        try {
            entityManager.getTransaction().begin();

            product = entityManager.find(Product.class, productId);

            entityManager.getTransaction().commit();
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Exception get products in database (on getProductsByHibernateThroughId): " + e);
        }
        return product;
    }


    /**
     * Обновление остатка товара на складе.
     * Проверка корректности ввода.
     */
    public boolean updateStockBalanceByJPA(Product currentProduct, String selectedProductQuantity) {
        final int minimumPossibleNumber = 0;

        if (NumberUtils.isNumber(selectedProductQuantity) && Integer.parseInt(selectedProductQuantity) > minimumPossibleNumber
                && Integer.parseInt(selectedProductQuantity) <= currentProduct.getInStock()) {

            int newBalance = currentProduct.getInStock() - Integer.parseInt(selectedProductQuantity);

            return newBalanceInStockByJPA(newBalance, currentProduct.getId());

        } else {
            return false;
        }
    }


    /**
     * Установка нового остатка товара на складе.
     */
    public boolean newBalanceInStockByJPA(int newBalance, String productId) {

        try {
            entityManager.getTransaction().begin();

            Product product = entityManager.find(Product.class, productId);

            product.setInStock(newBalance);

            entityManager.getTransaction().commit();
            return true;
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Exception new Balance product in database (on newBalanceInStockByHibernate): " + e);
            return false;
        }
    }


    /**
     * Добавление нового продукта в базу данных
     */
    public boolean addProductByJPA(Product product) {

        if (!Objects.equals("", product.getImagePath()) && !Objects.equals("", product.getName())
                && Objects.nonNull(product.getProductCategories()) && Objects.nonNull(product.getProductType())
                && Objects.nonNull(product.getProductColours()) && !Objects.equals("", product.getProductDetails())
                && !Objects.equals("", product.getSizeClothes())
                && !Objects.equals(0, product.getPrice()) && !Objects.equals(0, product.getInStock())) {

            try {
                entityManager.getTransaction().begin();

                entityManager.persist(product);

                entityManager.getTransaction().commit();
            } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
                log.info("Exception add product in database (on addProductByHibernate): " + e);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * Удаление продукта из базы данных по Id.
     */
    public void deleteProductByJPA(String productId) {

        try {
            entityManager.getTransaction().begin();

            Product product = entityManager.find(Product.class, productId);

            entityManager.remove(product);

            entityManager.getTransaction().commit();
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Exception delete product in database (on deleteProductByHibernate): " + e);
        }
    }


    /**
     * Поиск совпадений товаров в базе данных по частичному вводу названия пользователя
     */
    public List<Product> searchProductsByJPA(String userInput) {
        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
            final Root<Product> productRoot = productCriteriaQuery.from(Product.class);

            Predicate predicate = criteriaBuilder.like(productRoot.get("name"), "%" + userInput + "%");
            productCriteriaQuery.select(productRoot).where(predicate);

            products = entityManager.createQuery(productCriteriaQuery).getResultList();

            entityManager.getTransaction().commit();
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Exception search Product in database (on searchProductByHibernate): " + e);
        }
        return products;
    }
}
