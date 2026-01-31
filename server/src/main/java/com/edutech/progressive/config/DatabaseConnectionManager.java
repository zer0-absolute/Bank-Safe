// package com.edutech.progressive.config;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.util.Properties;
// import java.io.InputStream;
// import java.io.IOException;

// public class DatabaseConnectionManager {
//     private static Properties properties = new Properties();

//     private static void loadProperties() {
//         try {
//             InputStream inputStream = DatabaseConnectionManager.class.getClassLoader()
//                     .getResourceAsStream("application.properties");
//             properties.load(inputStream);

//             Class.forName(properties.getProperty("db.driver"));

//             inputStream.close();
//         } catch (IOException | ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//     }
//     // static {
//     //     loadProperties();
//     // }

//     public static Connection getConnection() throws SQLException {
//         loadProperties();
//         String url = properties.getProperty("db.url");
//         String username = properties.getProperty("db.username");
//         String password = properties.getProperty("db.password");
//         return DriverManager.getConnection(url, username, password);
//     }
// }

// ---------------------------------------------------------

package com.edutech.progressive.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionManager {
    @Autowired
    private static Environment env;

    private static final Properties properties = new Properties();

    private static void loadProperties() {
        String url = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        properties.put("url", url);
        properties.put("username", username);
        properties.put("password", password);
    }

    public static Connection getConnection() throws SQLException {
        loadProperties();
        String url = (String) properties.get("url");
        String username = (String) properties.get("username");
        String password = (String) properties.get("password");
        return DriverManager.getConnection(url, username, password);
    }
}
// ---------------------------------------------------------

// package com.edutech.progressive.config;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.util.Properties;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.env.Environment;
// import org.springframework.stereotype.Component;

// @Component
// public class DatabaseConnectionManager {
// private static final Properties properties = new Properties();

// @Autowired
// private static Environment env;

// private static void loadProperties() {
// String url = env.getProperty("spring.datasource.url");
// String username = env.getProperty("spring.datasource.username");
// String password = env.getProperty("spring.datasource.password");

// properties.put("url", url);
// properties.put("username", username);
// properties.put("password", password);
// }

// public static Connection getConnection() throws SQLException {
// loadProperties();
// String url = (String) properties.get("url");
// String username = (String) properties.get("username");
// String password = (String) properties.get("password");
// return DriverManager.getConnection(url, username, password);
// }
// }
