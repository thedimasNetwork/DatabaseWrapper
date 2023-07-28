/**
 * A utility class for handling database operations related to player data and bans.
 */
package stellar.database;

import arc.util.Log;
import arc.util.Nullable;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import stellar.database.enums.MessageType;
import stellar.database.enums.PlayerStatus;
import stellar.database.gen.Tables;
import stellar.database.gen.tables.Messages;
import stellar.database.gen.tables.records.BansRecord;
import stellar.database.gen.tables.records.PlaytimeRecord;
import stellar.database.gen.tables.records.StatsRecord;
import stellar.database.gen.tables.records.UsersRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public class Database {
    private static Connection connection;
    private static DSLContext context;

    private static String ip;
    private static int port;
    private static String name;
    private static String user;
    private static String password;

    /**
     * Loads the database connection parameters.
     *
     * @param ip       The IP address of the database server.
     * @param port     The port number of the database server.
     * @param name     The name of the database.
     * @param user     The username for the database connection.
     * @param password The password for the database connection.
     */
    public static void load(String ip, int port, String name, String user, String password) {
        Database.ip = ip;
        Database.port = port;
        Database.name = name;
        Database.user = user;
        Database.password = password;
    }

    /**
     * Gets the connection URL for the database.
     *
     * @return The connection URL as a string.
     */
    private static String getConnectionUrl() {
        return "jdbc:mysql://" + ip + ":" + port + "/" + name + "?autoReconnect=true";
    }

    /**
     * Retrieves the database connection.
     *
     * @return The database connection.
     * @throws SQLException If a database error occurs.
     */
    public static Connection getConnection() throws SQLException {
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
     * Retrieves the DSL context for database queries.
     *
     * @return The DSL context.
     * @throws SQLException If a database error occurs.
     */
    public static DSLContext getContext() throws SQLException {
        if (context == null) {
            context = DSL.using(getConnection(), SQLDialect.MYSQL);
        }
        return context;
    }

    // region players

    /**
     * Retrieves a player's record by UUID from the database.
     *
     * @param uuid The UUID of the player.
     * @return The UsersRecord representing the player's data or null if not found.
     * @throws SQLException If a database error occurs.
     */
    @Nullable
    public static UsersRecord getPlayer(String uuid) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.users)
                .where(Tables.users.uuid.eq(uuid))
                .fetchOne();
    }

    /**
     * Retrieves a player's record by ID from the database.
     *
     * @param id The ID of the player.
     * @return The UsersRecord representing the player's data or null if not found.
     * @throws SQLException If a database error occurs.
     */
    @Nullable
    public static UsersRecord getPlayer(int id) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.users)
                .where(Tables.users.id.eq(id))
                .fetchOne();
    }

    /**
     * Checks if a player with the given UUID exists in the database.
     *
     * @param uuid The UUID of the player.
     * @return True if the player exists, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    public static boolean playerExists(String uuid) throws SQLException {
        return Database.getContext().fetchExists(Tables.users, Tables.users.uuid.eq(uuid));
    }

    /**
     * Creates a new player record in the database.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @param admin  True if the player is an admin, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    public static void createPlayer(String uuid, String ip, String name, String locale, boolean admin) throws SQLException {
        Database.getContext().newRecord(Tables.users)
                .setUuid(uuid)
                .setIp(ip)
                .setName(name)
                .setLocale(locale)
                .setStatus(admin ? PlayerStatus.admin : PlayerStatus.basic)
                .store();
    }

    /**
     * Creates a new player record along with playtime and stats records in the database.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @param admin  True if the player is an admin, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    public static void createFullPlayer(String uuid, String ip, String name, String locale, boolean admin) throws SQLException {
        Database.createPlayer(uuid, ip, name, locale, admin);
        Database.createPlaytime(uuid);
        Database.createStats(uuid);
    }
    // endregion

    // region bans

    /**
     * Retrieves the latest ban record for a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return The BansRecord representing the latest ban or null if no ban is found.
     * @throws SQLException If a database error occurs.
     */
    public static BansRecord latestBan(String uuid) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.bans)
                .where(Tables.bans.target.eq(uuid))
                .orderBy(Tables.bans.id.desc())
                .limit(1)
                .fetchOne();
    }

    /**
     * Checks if a player is banned.
     *
     * @param uuid The UUID of the player.
     * @return True if the player is banned, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    public static boolean isBanned(String uuid) throws SQLException {
        BansRecord record = latestBan(uuid);
        if (record == null) {
            return false;
        }

        if (!record.isActive()) {
            return false;
        }

        if (record.getUntil() == null) {
            return true;
        }

        return !record.getUntil().isBefore(LocalDateTime.now());
    }

    /**
     * Bans a player.
     *
     * @param admin  The UUID of the admin performing the ban.
     * @param target The UUID of the player to be banned.
     * @param period The ban period in days, or -1 for a permanent ban.
     * @param reason The reason for the ban.
     * @throws SQLException If a database error occurs.
     * @throws IllegalArgumentException If the target player does not exist or is already banned.
     */
    public static void ban(String admin, String target, int period, String reason) throws SQLException {
        if (!Database.playerExists(target)) {
            throw new IllegalArgumentException("Target does not exist!");
        }
        if (Database.isBanned(target)) {
            throw new IllegalArgumentException("Target is already banned!");
        }

        Database.getContext().newRecord(Tables.bans)
                .setAdmin(admin)
                .setTarget(target)
                .setCreated(LocalDateTime.now())
                .setUntil(period > -1 ? LocalDateTime.now().plusDays(period) : null)
                .setReason(reason)
                .store();
    }

    /**
     * Unbans a player.
     *
     * @param target The UUID of the player to be unbanned.
     * @throws SQLException If a database error occurs.
     * @throws IllegalArgumentException If the target player does not exist or is not banned.
     */
    public static void unban(String target) throws SQLException {
        if (!Database.playerExists(target)) {
            throw new IllegalArgumentException("Target does not exist!");
        }
        if (!Database.isBanned(target)) {
            throw new IllegalArgumentException("Target is not banned!");
        }

        Database.latestBan(target)
                .setActive(false)
                .store();
    }
    // endregion

    // region playtime & stats

    /**
     * Retrieves the playtime of a player for a specific field.
     *
     * @param uuid  The UUID of the player.
     * @param field The playtime field to retrieve.
     * @return The playtime value for the specified field.
     * @throws SQLException If a database error occurs.
     */
    public static long getPlaytime(String uuid, Field<Long> field) throws SQLException {
        Record1<Long> timeFetch = Database.getContext()
                .select(field)
                .from(Tables.playtime)
                .where(Tables.playtime.uuid.eq(uuid))
                .fetchOne();
        long time = 0;
        if (timeFetch == null) {
            Log.warn("Player @ doesn't exist", uuid);
            createPlaytime(uuid);
        } else {
            time = timeFetch.value1();
        }

        return time;
    }

    /**
     * Retrieves the total playtime of a player.
     *
     * @param uuid The UUID of the player.
     * @return The total playtime in milliseconds.
     * @throws SQLException If a database error occurs.
     */
    public static long getTotalPlaytime(String uuid) throws SQLException {
        PlaytimeRecord timeFetch = Database.getContext()
                .selectFrom(Tables.playtime)
                .where(Tables.playtime.uuid.eq(uuid))
                .fetchOne();
        long time = 0;
        if (timeFetch == null) {
            Log.warn("Player @ doesn't exist", uuid);
            createPlaytime(uuid);
        } else {
            for (Field<?> field : timeFetch.fields()) {
                if (field.getType() == Long.class) {
                    time += (Long) field.getValue(timeFetch);
                }
            }
        }

        return time;
    }

    /**
     * Creates a new playtime record for a player in the database.
     *
     * @param uuid The UUID of the player.
     * @throws SQLException If a database error occurs.
     */
    public static void createPlaytime(String uuid) throws SQLException {
        Database.getContext().newRecord(Tables.playtime)
                .setUuid(uuid)
                .store();
    }

    /**
     * Retrieves the stats record of a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return The StatsRecord representing the player's statistics.
     * @throws SQLException If a database error occurs.
     */
    public static StatsRecord getStats(String uuid) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.stats)
                .where(Tables.stats.uuid.eq(uuid))
                .fetchOne();
    }

    /**
     * Creates a new stats record for a player in the database.
     *
     * @param uuid The UUID of the player.
     * @throws SQLException If a database error occurs.
     */
    public static void createStats(String uuid) throws SQLException {
        Database.getContext().newRecord(Tables.stats)
                .setUuid(uuid)
                .store();
    }
    // endregion

    // region messages & events
    /**
     * Creates a new message record in the database.
     *
     * @param server The name or identifier of the server where the message originates.
     * @param from   The sender of the message.
     * @param target The target recipient of the message (player's UUID or team).
     * @param type   The type of the message ({@link MessageType}).
     * @param text   The content of the message.
     * @param locale The locale or language of the message.
     * @throws SQLException If a database error occurs.
     */
    public static void createMessage(String server, String from, String target, MessageType type, String text, String locale) throws SQLException {
        Database.getContext().newRecord(Tables.messages)
                .setServer(server)
                .setFrom(from)
                .setTarget(target)
                .setType(type)
                .setText(text)
                .setLocale(locale)
                .store();
    }
    // endregion
}
