// * Create an application which can insert a record into a table (wrapped inside a container). Also the compilation should be done in the container itself.
// * The database need to be in other container (Use any sql DB, preferably PostGres)
// * Just make these 2 containers to interact with each other.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db:5432/testdb";
        String user = "postgres";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String createTable = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50))";
            conn.createStatement().execute(createTable);

            String insertSQL = "INSERT INTO users(name) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, "Mohit");
            pstmt.executeUpdate();

            System.out.println("Record Inserted Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}