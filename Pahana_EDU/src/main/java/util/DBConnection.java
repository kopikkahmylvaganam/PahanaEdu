package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    
    private static final String URL =
        "jdbc:mysql://localhost:3306/padana_edu?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Colombo";
    private static final String USER = "root";
    private static final String PASS = "Kopikkah98@"; // ் MySQL password

    // 1) Private constructorு
    private DBConnection() {
        try {
            // 2) Driver load (ஒருமுறை மட்டும்)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found in WEB-INF/lib.", e);
        }
    }

    // 3) Lazy, thread-safe Singleton (Holder idiom)
    private static class Holder {
        private static final DBConnection INSTANCE = new DBConnection();
    }

    // 4) ஒரே instance ஐப் பெற
    public static DBConnection getInstance() {
        return Holder.INSTANCE;
    }

    // 5) ஒவ்வொரு அழைப்புக்கும் புதிய Connection (auto-closeable)
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
