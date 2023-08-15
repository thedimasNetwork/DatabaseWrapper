package stellar.database;

import arc.util.Log;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.lang.module.ModuleDescriptor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;


/**
 * A config class for handling database configuration.
 */
public class Config {
    private static Connection connection;

    private static String ip;
    private static int port;
    private static String name;
    private static String user;
    private static String password;
    private static HikariDataSource dataSource;
    private static HikariConfig hikariConfig;
    private static Version version;

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
     * Gets the connection URL for the database.
     *
     * @return The connection URL as a string.
     */
    static String getConnectionUrl() {
        return "jdbc:mysql://" + ip + ":" + port + "/" + name + "?autoReconnect=true";
    }

    /**
     * Gets the current version of the Database Wrapper.
     *
     * @return Current version
     */
    public static Version getVersion() {
        if (version == null) {
            version = new Version();
        }
        return version;
    }

    /**
     * Retrieves the database connection.
     *
     * @deprecated use {@link #getDataSource()} instead
     * @return The database connection.
     * @throws SQLException If a database error occurs.
     */
    @Deprecated(forRemoval = true)
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
     * Asynchronously retrieves the database connection.
     *
     * @deprecated use {@link #getDataSourceAsync()} instead
     * @return A CompletableFuture that holds the database connection.
     */
    @Deprecated(forRemoval = true)
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

    /**
     * Retrieves the database {@link HikariDataSource}.
     *
     * @return The database {@link HikariDataSource}.
     */
    static HikariDataSource getDataSource() {
        if (dataSource == null || dataSource.isClosed()) {
            hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(getConnectionUrl());
            hikariConfig.setUsername(user);
            hikariConfig.setPassword(password);

            dataSource = new HikariDataSource(hikariConfig);
        }
        return dataSource;
    }

    /**
     * Asynchronously retrieves the database {@link HikariDataSource}.
     *
     * @return CompletableFuture that holds the database {@link HikariDataSource}.
     */
    static CompletableFuture<HikariDataSource> getDataSourceAsync() {
        return CompletableFuture.supplyAsync(() -> {
            if (dataSource == null || dataSource.isClosed()) {
                hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl(getConnectionUrl());
                hikariConfig.setUsername(user);
                hikariConfig.setPassword(password);

                dataSource = new HikariDataSource(hikariConfig);
            }
            return dataSource;
        });
    }

}
