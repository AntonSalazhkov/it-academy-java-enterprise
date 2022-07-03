package by.it.academy.repositories.databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import by.it.academy.entities.product.Product;
import by.it.academy.entities.product.ProductCategories;
import by.it.academy.entities.product.ProductColours;
import by.it.academy.entities.product.ProductType;

public class Database_emulation {

    private List<Product> products;

    public Database_emulation() {
        this.products = getProductsInDatabase();
    }

    public List<Product> getProducts() {
        return products;
    }

    private List<Product> getProductsInDatabase() {

        List<Product> products = new ArrayList<>();

        String pathFile = new File("").getAbsolutePath();

        pathFile = pathFile.substring(0, pathFile.length() - 4);

        pathFile = pathFile + File.separator + "webapps" + File.separator + "ROOT" + File.separator
                + "WEB-INF" + File.separator + "classes" + File.separator + "Base.txt";

        try (Scanner sc = new Scanner(new FileReader(pathFile))) {
            sc.useLocale(Locale.ENGLISH);

            while (sc.hasNext()) {
                products.add(new Product(sc.nextInt(), sc.next(), sc.next(), ProductCategories.valueOf(sc.next()), ProductType.valueOf(sc.next()),
                        ProductColours.valueOf(sc.next()), sc.next(), sc.next(), sc.nextInt(), sc.nextInt()));
            }

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
        return products;
    }
}
