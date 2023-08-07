package stellar.database;

import arc.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;


/**
 * A config class for handling database configuration.
 */
class Config {
    static Connection connection;

    static String ip;
    static int port;
    static String name;
    static String user;
    static String password;

    /**
     * Loads the database connection parameters.
     *
     * @param ip       The IP address of the database server.
     * @param port     The port number of the database server.
     * @param name     The name of the database.
     * @param user     The username for the database connection.
     * @param password The password for the database connection.
     */
    static void load(String ip, int port, String name, String user, String password) {
        Config.ip = ip;
        Config.port = port;
        Config.name = name;
        Config.user = user;
        Config.password = password;
        System.setProperty("org.jooq.no-tips", "true");
        System.setProperty("org.jooq.no-logo", "true");
    }

    /**
     * Retrieves the database connection.
     *
     * @return The database connection.
     * @throws SQLException If a database error occurs.
     */
    static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(getConnectionUrl(), user, password);
            } catch (Throwable t) {
                Log.err(t);
            }
        } else if (connection.isClosed()) {
            connection = DriverManager.getConnection(getConnectionUrl(), user, password);
        }

        try { // checking if the connection is alive; may be not closed but timeout
            PreparedStatement ps = connection.prepareStatement("SELECT 1");
            ps.executeQuery();
        } catch (Exception e) {
            Log.warn("Database connection died due to inactivity");
            connection = DriverManager.getConnection(getConnectionUrl(), user, password);
        }

        return connection;
    }

    /**
     * Gets the connection URL for the database.
     *
     * @return The connection URL as a string.
     */
    static String getConnectionUrl() {
        return "jdbc:mysql://" + ip + ":" + port + "/" + name + "?autoReconnect=true";
    }

    /**
     * Asynchronously retrieves the database connection.
     *
     * @return A CompletableFuture that holds the database connection.
     */
    static CompletableFuture<Connection> getConnectionAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(getConnectionUrl(), user, password);
            } catch (Throwable t) {
                Log.err(t);
                throw new RuntimeException("Failed to get a database connection.", t);
            }
        });
    }
}
