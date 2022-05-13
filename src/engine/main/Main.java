package engine.main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        final String url = "jdbc:mysql://remotemysql.com:3306/zFr2acY6oX";
        final String username = "zFr2acY6oX";
        final String password = "Is6QT4Nk8I";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        new MainFrame();
    }
}
