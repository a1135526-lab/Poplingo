package com.poplingo.poplingo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    // 資料庫網址：連線到本機的 poplingo_db (確保時區設定正確)
    private static final String URL = "jdbc:mysql://localhost:3306/poplingo_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    // XAMPP 預設的帳號是 root
    private static final String USER = "root";
    // ⚠️ XAMPP 預設的密碼是「空的」，所以裡面什麼都不要打！
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("資料庫連線失敗！請檢查 XAMPP 的 MySQL 是否有啟動。");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("🎉 恭喜！成功連線到 XAMPP 的 MySQL 資料庫！");
        }
    }
}