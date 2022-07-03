package by.it.academy.repositories.databases;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Log4j
public class Database {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;

    public Database() {
        setConfig();
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setConfig() {
        Properties properties = new Properties();

        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(resourceAsStream);

            driverClassName = properties.getProperty("db.driverClassName");
            url = properties.getProperty("db.url");
            userName = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            log.info("Set config database: " + e);
        }
    }

    public void setDatabaseConnection(String query) {

        try {
            Class.forName(driverClassName);

            this.connection = DriverManager.getConnection(url, userName, password);
            this.statement = connection.createStatement();
            this.resultSet = statement.executeQuery(query);

        } catch (SQLException | ClassNotFoundException sqlEx) {
            log.info("Set database connection: " + sqlEx);
        }
    }

    public void closeStreams() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }

            if (this.statement != null) {
                this.statement.close();
            }

            if (this.resultSet != null) {
                this.resultSet.close();
            }
        } catch (SQLException sqlEx) {
            log.info("Close streams: " + sqlEx);
        }
    }
}
