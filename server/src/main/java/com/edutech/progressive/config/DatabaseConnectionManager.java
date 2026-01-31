package com.edutech.progressive.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DatabaseConnectionManager {
    private static Properties properties = new Properties();

    private static void loadProperties() throws IOException {
        properties.load(new FileInputStream("/home/coder/app/server/src/main/resources/application.properties"));
    }

    public static Connection getConnection() throws SQLException {
        try {
            loadProperties();
        } catch (IOException e) {
            System.out.println(e);
        }
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        return DriverManager.getConnection(url, username, password);
    }
}

// ---------------------------------------------------------

// package com.edutech.progressive.config;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.util.Properties;

// import javax.annotation.PostConstruct;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.env.Environment;
// import org.springframework.stereotype.Component;

// @Component
// public class DatabaseConnectionManager {
// @Autowired
// private static Environment env;

// private static final Properties properties = new Properties();

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
// return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
// "root", "root");
// }
// }
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
