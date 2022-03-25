package by.it.academy;

import java.sql.*;
import java.util.ArrayList;

public class Base {

    private String driverClassName;
    private String url;
    private String userName;
    private String password;

    public Base(ArrayList<String> baseData) {
        this.driverClassName = baseData.get(0);
        this.url = baseData.get(1);
        this.userName = baseData.get(2);
        this.password = baseData.get(3);
    }

    public void getOracleConnection() throws ClassNotFoundException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT customer_id, customer_name, city FROM customers";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "1234qwerasdz");
            con = DriverManager.getConnection(url, userName, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String city = rs.getString(3);
                System.out.printf("id: %d, name: %s, city: %s %n", id, name, city);
            }

        } catch (SQLException sqlEx) {
            System.out.println("Invalid database login information");
            sqlEx.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
            }
        }
    }
}
