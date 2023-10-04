package com.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWorker {
    private static String url = "jdbc:postgresql://localhost:5433/aikam_shop";
    private static String username = "postgres";
    private static String password = "123";

    public static List<Object[]> searchByLastName(String lastName) {
        List<Object[]> result = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from searchByLastNameFunction(?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, lastName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String resultLastName = resultSet.getString("last_name");
                String resultFirstName = resultSet.getString("first_name");
                Object[] rowData = {"lastName", resultLastName, "firstName", resultFirstName};
                result.add(rowData);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new ErrorResponse("An error occurred while processing the request.");
        }
        return result;
    }

    public static List<Object[]> searchByProductsAndCount(String products, int minTimes) {
        List<Object[]> result = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from searchByProductsAndCountFunction(?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, products);
            statement.setInt(2, minTimes);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String resultLastName = resultSet.getString("last_name");
                String resultFirstName = resultSet.getString("first_name");
                Object[] rowData = {"lastName", resultLastName, "firstName", resultFirstName};
                result.add(rowData);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new ErrorResponse("An error occurred while processing the request.");
        }
        return result;
    }
}