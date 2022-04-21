package onlinestore.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    private static Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            properties.load(new FileInputStream("src/onlinestore/settings.properties"));
        } catch (IOException e) {
            System.out.println("Could not load properties.");
            e.printStackTrace();
        }
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"))) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            PreparedStatement pstmt = con.prepareStatement("select * from customer");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer temp = new Customer();
                temp.setId(rs.getInt("id"));
                temp.setFullName(rs.getString("full_name"));
                temp.setPassword(rs.getString("password"));
                temp.setSocialSecurityNumber(rs.getString("social_security_number"));
                temp.setAddress(rs.getString("address"));
                temp.setCity(rs.getString("city"));

                customerList.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static List<Shoe> getAllShoesInStock() {
        List<Shoe> out = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"))) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            PreparedStatement pstmt = con.prepareStatement("select * from shoe where stock > 0");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Shoe temp = new Shoe();
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("shoe_name"));
                temp.setPrice(rs.getInt("price_sek"));
                temp.setStock(rs.getInt("stock"));
                temp.setColor(rs.getString("color"));
                temp.setSize(rs.getInt("size"));
                out.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public static Order getActiveOrder(Customer c){
        Order out = new Order();
        try (Connection con = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"))) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            PreparedStatement pstmt = con.prepareStatement("select * from orders where fk_customer_id=?");
            pstmt.setInt(1, c.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order temp = new Order();
                temp.setId(rs.getInt("id"));
                temp.setCustomerId(c.getId());
                temp.setShoeList(getOrderProducts(temp));
                out = temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public static int getActiveOrderId(Customer c){
        int out = -1;
        try (Connection con = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"))) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            PreparedStatement pstmt = con.prepareStatement("select * from orders where fk_customer_id=?");
            pstmt.setInt(1, c.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                out = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public static List<Shoe> getOrderProducts(Order o) {
        List<Shoe> out = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"))) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            PreparedStatement pstmt = con.prepareStatement("select * from order_specification " +
                    "join shoe on shoe.id=fk_shoe_id where fk_orders_id=?");
            pstmt.setInt(1, o.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Shoe temp = new Shoe();
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("shoe_name"));
                temp.setPrice(rs.getInt("price_sek"));
                temp.setSize(rs.getInt("size"));
                temp.setColor(rs.getString("color"));
                temp.setStock(rs.getInt("stock"));
                out.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void addToCart(int custid, int orderid, int shoeid){
        try (Connection con = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"))) {
            CallableStatement pstmt = con.prepareCall("call addToCart(?, ?, ?)");
            pstmt.setInt(1, custid);
            pstmt.setInt(2, orderid);
            pstmt.setInt(3, shoeid);
            ResultSet rs = pstmt.executeQuery();
            while (rs != null && rs.next()) {
                String message = rs.getString("MESSAGE");
                System.out.println("Message: " + message + "\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
