package by.it.academy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Properties properties = new Properties();
        ArrayList<String> baseData = new ArrayList<>();


        try (InputStream resourceAsStream = new App().getResourceAsStream()) {
            properties.load(resourceAsStream);

            System.out.println("Configuration file data: \n");
            for (Map.Entry<Object, Object> prop : properties.entrySet()) {
                System.out.println(prop);
                baseData.add("" + prop.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error");
        }

        System.out.println("");

        Base base = new Base(baseData);
        base.getOracleConnection();

    }

    private InputStream getResourceAsStream() {
        return this.getClass().getClassLoader().getResourceAsStream("config.properties");
    }
}
