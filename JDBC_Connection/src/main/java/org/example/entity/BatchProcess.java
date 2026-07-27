package org.example.entity;

import java.sql.*;

public class BatchProcess
{
    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/deep";
        String username = "root";
        String password = "#Deepshikha19";

        Student st1 = new Student(01, "deepshikha", "st");

        try {
            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement prt = con.prepareStatement(
                    "INSERT INTO student_data (st_id, st_name, st_domain) VALUES (?, ?, ?)"
            );

            prt.setInt(1, 1);
            prt.setString(2, "deepshikha");
            prt.setString(3, "ST");

            prt.addBatch();

            int[] rows = prt.executeBatch();

            for (int data : rows) {
                System.out.println(data);
            }

            prt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
