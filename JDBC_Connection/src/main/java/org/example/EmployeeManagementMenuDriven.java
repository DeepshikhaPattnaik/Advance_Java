package org.example;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeManagementMenuDriven {

    static final String URL = "jdbc:mysql://localhost:3306/employee";
    static final String USER = "root";
    static final String PASSWORD = "#Deepshikha19";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {

            System.out.println("     EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("1. Register Employee");
            System.out.println("2. Get Employee Name By ID");
            System.out.println("3. Get All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. WHERE - Find Employee By City");
            System.out.println("7. HAVING - Department Employee Count");
            System.out.println("8. GROUP BY - Employees By Department");
            System.out.println("9. Exit");
            System.out.println("--------------------------------------");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    registerEmployee(sc);
                    break;

                case 2:
                    getNameById(sc);
                    break;

                case 3:
                    getAllEmployees();
                    break;

                case 4:
                    updateEmployee(sc);
                    break;

                case 5:
                    deleteEmployee(sc);
                    break;

                case 6:
                    findByCity(sc);
                    break;

                case 7:
                    havingExample();
                    break;

                case 8:
                    groupByExample();
                    break;

                case 9:
                    System.out.println("Program exited successfully.");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 9);

        sc.close();
    }

    // 1. REGISTER EMPLOYEE

    public static void registerEmployee(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql = "INSERT INTO emp_data" +
                    "(emp_id, emp_name, emp_salary, emp_role, " +
                    "emp_designation, emp_department, emp_email, emp_city) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            System.out.print("Enter Employee ID: ");
            int empId = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            String empName = sc.nextLine();

            System.out.print("Enter Salary: ");
            double empSalary = sc.nextDouble();

            sc.nextLine();

            System.out.print("Enter Role: ");
            String empRole = sc.nextLine();

            System.out.print("Enter Designation: ");
            String empDesignation = sc.nextLine();

            System.out.print("Enter Department: ");
            String empDepartment = sc.nextLine();

            System.out.print("Enter Email: ");
            String empEmail = sc.nextLine();

            System.out.print("Enter City: ");
            String empCity = sc.nextLine();

            pst.setInt(1, empId);
            pst.setString(2, empName);
            pst.setDouble(3, empSalary);
            pst.setString(4, empRole);
            pst.setString(5, empDesignation);
            pst.setString(6, empDepartment);
            pst.setString(7, empEmail);
            pst.setString(8, empCity);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Employee registered successfully!"
                );
            }

            pst.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error while registering employee."
            );

            e.printStackTrace();
        }
    }

    // 2. GET EMPLOYEE NAME BY ID

    public static void getNameById(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql =
                    "SELECT emp_name FROM emp_data WHERE emp_id = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            System.out.print("Enter Employee ID: ");
            int empId = sc.nextInt();

            pst.setInt(1, empId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "Employee Name: " +
                                rs.getString("emp_name")
                );

            } else {

                System.out.println(
                        "Employee not found!"
                );
            }

            rs.close();
            pst.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // 3. GET ALL EMPLOYEES

    public static void getAllEmployees() {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql = "SELECT * FROM emp_data";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println(
                    "\n========== ALL EMPLOYEES =========="
            );

            while (rs.next()) {

                System.out.println(
                        "ID: " +
                                rs.getInt("emp_id")
                );

                System.out.println(
                        "Name: " +
                                rs.getString("emp_name")
                );

                System.out.println(
                        "Salary: " +
                                rs.getDouble("emp_salary")
                );

                System.out.println(
                        "Role: " +
                                rs.getString("emp_role")
                );

                System.out.println(
                        "Designation: " +
                                rs.getString("emp_designation")
                );

                System.out.println(
                        "Department: " +
                                rs.getString("emp_department")
                );

                System.out.println(
                        "Email: " +
                                rs.getString("emp_email")
                );

                System.out.println(
                        "City: " +
                                rs.getString("emp_city")
                );

                System.out.println(
                        "--------------------------------"
                );
            }

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // 4. UPDATE EMPLOYEE

    public static void updateEmployee(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql =
                    "UPDATE emp_data SET " +
                            "emp_name = ?, " +
                            "emp_salary = ?, " +
                            "emp_city = ? " +
                            "WHERE emp_id = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            System.out.print("Enter Employee ID: ");
            int empId = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter New Name: ");
            String empName = sc.nextLine();

            System.out.print("Enter New Salary: ");
            double empSalary = sc.nextDouble();

            sc.nextLine();

            System.out.print("Enter New City: ");
            String empCity = sc.nextLine();

            pst.setString(1, empName);
            pst.setDouble(2, empSalary);
            pst.setString(3, empCity);
            pst.setInt(4, empId);

            int rows = pst.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee updated successfully!"
                );

            } else {

                System.out.println(
                        "Employee not found!"
                );
            }

            pst.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // 5. DELETE EMPLOYEE

    public static void deleteEmployee(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql =
                    "DELETE FROM emp_data WHERE emp_id = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            System.out.print("Enter Employee ID: ");
            int empId = sc.nextInt();

            pst.setInt(1, empId);

            int rows = pst.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee deleted successfully!"
                );

            } else {

                System.out.println(
                        "Employee not found!"
                );
            }

            pst.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }



    // 6. WHERE - FIND EMPLOYEE BY CITY

    public static void findByCity(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql =
                    "SELECT * FROM emp_data " +
                            "WHERE emp_city = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            sc.nextLine();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            pst.setString(1, city);

            ResultSet rs = pst.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "ID: " +
                                rs.getInt("emp_id")
                );

                System.out.println(
                        "Name: " +
                                rs.getString("emp_name")
                );

                System.out.println(
                        "Department: " +
                                rs.getString("emp_department")
                );

                System.out.println(
                        "City: " +
                                rs.getString("emp_city")
                );

                System.out.println(
                        "----------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "No employee found in this city."
                );
            }

            rs.close();
            pst.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // 7. HAVING CLAUSE

    public static void havingExample() {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql =
                    "SELECT emp_department, COUNT(*) AS total " +
                            "FROM emp_data " +
                            "GROUP BY emp_department " +
                            "HAVING COUNT(*) > 1";

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            System.out.println(
                    "\n===== DEPARTMENTS WITH MORE THAN 1 EMPLOYEE ====="
            );

            while (rs.next()) {

                System.out.println(
                        "Department: " +
                                rs.getString("emp_department")
                );

                System.out.println(
                        "Total Employees: " +
                                rs.getInt("total")
                );

                System.out.println(
                        "--------------------------------"
                );
            }

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // 8. GROUP BY CLAUSE

    public static void groupByExample() {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            String sql =
                    "SELECT emp_department, COUNT(*) AS total " +
                            "FROM emp_data " +
                            "GROUP BY emp_department";

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            System.out.println(
                    "\n========== EMPLOYEES BY DEPARTMENT =========="
            );

            while (rs.next()) {

                System.out.println(
                        "Department: " +
                                rs.getString("emp_department")
                );

                System.out.println(
                        "Total Employees: " +
                                rs.getInt("total")
                );

                System.out.println(
                        "--------------------------------"
                );
            }

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}