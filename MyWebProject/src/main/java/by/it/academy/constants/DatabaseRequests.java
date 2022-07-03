package by.it.academy.constants;

public class DatabaseRequests {

    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";

    public static final String SELECT_PRODUCTS_BY_TYPE = "SELECT id, image_path, products_name, " +
            "categories.name, type.name, colours.name, product_details, size_clothes, price, in_stock " +
            "FROM products " +
            "INNER JOIN categories USING(categories_id) " +
            "INNER JOIN type USING(type_id) " +
            "INNER JOIN colours USING(colours_id) ";

    public static final String SELECT_ALL_WHERE_ID = "SELECT * FROM products where id = ";

    public static final String UPDATE_PRODUCTS = "UPDATE products SET in_stock = ";

    public static final String INSERT_INTO_PRODUCTS = "INSERT INTO products (image_path, products_name, categories, " +
            "type, colours, product_details, size_clothes, price, in_stock) VALUES ";

    public static final String DELETE_PRODUCTS = "DELETE FROM products where id = ";

    public static final String SELECT_ALL_USERS = "SELECT * FROM users";

    public static final String INSERT_INTO_USERS = "INSERT INTO users (login, email, password, user_type_id) VALUES ";
}
