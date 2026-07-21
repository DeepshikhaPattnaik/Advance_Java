package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/deep";
        String username = "root";
        String password = "#Deepshikha19";

        try {
            // Step 1: Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully!");

            // Step 3: Create PreparedStatement
            String query = "INSERT INTO student_data VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter ID: ");
            ps.setInt(1, sc.nextInt());

            sc.nextLine();

            System.out.print("Enter Name: ");
            ps.setString(2, sc.nextLine());

            System.out.print("Enter Domain: ");
            ps.setString(3, sc.nextLine());

            // Step 4: Execute Query
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Data Inserted Successfully.");
            } else {
                System.out.println("Insertion Failed.");
            }

            // Step 5: Close Resources
            ps.close();
            con.close();
            sc.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Driver Not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database Error!");
            e.printStackTrace();
        }
    }
}