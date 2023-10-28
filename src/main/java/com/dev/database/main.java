package com.dev.database;

import java.sql.*;

public class main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/hairshop";
        String username = "root";
        String password = "1325";

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            // Выполнение запроса для получения данных мастеров и их клиентов
            String selectQuery = "SELECT m.name AS master_name, m.surname AS master_surname, c.name AS client_name, c.surname AS client_surname " +
                    "FROM masters m " +
                    "JOIN clients c ON m.id = c.master_id";
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Вывод результатов запроса
            while (rs.next()) {
                String masterName = rs.getString("master_name");
                String masterSurname = rs.getString("master_surname");
                String clientName = rs.getString("client_name");
                String clientSurname = rs.getString("client_surname");
                System.out.println("Мастер: " + masterName + " " + masterSurname + ", Клиент: " + clientName + " " + clientSurname);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
