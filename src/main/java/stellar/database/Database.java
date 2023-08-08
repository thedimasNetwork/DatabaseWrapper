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
import stellar.database.gen.tables.records.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static stellar.database.Config.getConnection;

/**
 * A utility class for handling database operations.
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public class Database {
    // region loading

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
        Config.load(ip, port, name, user, password);
    }

    /**
     * Retrieves the DSL context for database queries.
     *
     * @return The DSL context.
     * @throws SQLException If a database error occurs.
     */
    public static DSLContext getContext() throws SQLException {
        return DSL.using(getConnection(), SQLDialect.MYSQL);
    }
    // endregion

    // region players

    /**
     * Retrieves a player's record by UUID from the database.
     *
     * @param uuid The UUID of the player.
     * @return The {@link UsersRecord} representing the player's data or null if not found.
     * @throws SQLException If a database error occurs.
     */
    @Nullable
    public static UsersRecord getPlayer(String uuid) throws SQLException {
        return getContext()
                .selectFrom(Tables.users)
                .where(Tables.users.uuid.eq(uuid))
                .fetchOne();
    }

    /**
     * Retrieves a player's record by ID from the database.
     *
     * @param id The ID of the player.
     * @return The {@link UsersRecord} representing the player's data or null if not found.
     * @throws SQLException If a database error occurs.
     */
    @Nullable
    public static UsersRecord getPlayer(int id) throws SQLException {
        return getContext()
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
        return getContext().fetchExists(Tables.users, Tables.users.uuid.eq(uuid));
    }

    /**
     * Creates a new player record in the database and returns it.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @param admin  True if the player is an admin, false otherwise.
     * @return The created {@link UsersRecord}.
     * @throws SQLException If a database error occurs.
     */
    public static UsersRecord createPlayer(String uuid, String ip, String name, String locale, boolean admin) throws SQLException {
        UsersRecord record = getContext().newRecord(Tables.users)
                .setUuid(uuid)
                .setIp(ip)
                .setName(name)
                .setLocale(locale)
                .setStatus(admin ? PlayerStatus.admin : PlayerStatus.basic);
        record.store();
        return record;
    }

    /**
     * Asynchronously updates player's info in the database.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @return The updated {@link UsersRecord}.
     * @throws SQLException             If a database error occurs.
     * @throws IllegalArgumentException If the player does not exist.
     */
    public static UsersRecord updatePlayer(String uuid, String name, String locale, String ip) throws SQLException {
        if (!Database.playerExists(uuid)) {
            throw new IllegalArgumentException("Player does not exists!");
        }

        UsersRecord record = Database.getPlayer(uuid).setName(name)
                .setLocale(locale)
                .setIp(ip);
        record.store();
        return record;
    }


    /**
     * Creates a new player record along with playtime and stats records in the database as returns it.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @param admin  True if the player is an admin, false otherwise.
     * @return The created {@link UsersRecord}.
     * @throws SQLException If a database error occurs.
     */
    public static UsersRecord createFullPlayer(String uuid, String ip, String name, String locale, boolean admin) throws SQLException {
        createPlaytime(uuid);
        createStats(uuid);
        return createPlayer(uuid, ip, name, locale, admin);
    }

    /**
     * Creates a new IP record in the database.
     *
     * @param ip    The IP address for which the record is being created.
     * @param proxy True if the IP is associated with a proxy, false otherwise.
     * @param vpn   True if the IP is associated with a VPN, false otherwise.
     * @param type  The <a href="https://proxycheck.io/api/#type_responses">Type</a> of the IP address.
     * @param risk  The <a href="https://proxycheck.io/api/#risk_score">Risk Score</a> of the IP address.
     * @return The created {@link IpCachedRecord}.
     * @throws SQLException If a database error occurs.
     * @see <a href="https://proxycheck.io/api/">Proxycheck API docs</a>
     */
    public static IpCachedRecord createIp(String ip, boolean proxy, boolean vpn, String type, int risk) throws SQLException {
        IpCachedRecord record = getContext().newRecord(Tables.ipCached)
                .setIp(ip)
                .setProxy(proxy)
                .setVpn(vpn)
                .setType(type)
                .setRisk((short) risk);
        record.store();
        return record;
    }

    /**
     * Retrieves an array of IP addresses used by a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return An array of IP addresses used by the specified player.
     * @throws SQLException             If a database error occurs.
     * @throws IllegalArgumentException If the player does not exist.
     */
    public static String[] getIps(String uuid) throws SQLException {
        if (!playerExists(uuid)) {
            throw new IllegalArgumentException("Player does not exist!");
        }

        return getContext()
                .select(Tables.logins.ip)
                .from(Tables.logins)
                .where(Tables.logins.uuid.eq(uuid))
                .groupBy(Tables.logins.ip)
                .fetchArray(0, String.class);
    }

    /**
     * Retrieves an array of names used by a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return An array of names used by the specified player.
     * @throws SQLException             If a database error occurs.
     * @throws IllegalArgumentException If the player does not exist.
     */
    public static String[] getNames(String uuid) throws SQLException {
        if (!playerExists(uuid)) {
            throw new IllegalArgumentException("Player does not exist!");
        }

        return getContext()
                .select(Tables.logins.name)
                .from(Tables.logins)
                .where(Tables.logins.uuid.eq(uuid))
                .groupBy(Tables.logins.name)
                .fetchArray(0, String.class);
    }
    // endregion

    // region bans

    /**
     * Retrieves the latest ban record for a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return The {@link BansRecord} representing the latest ban or null if no ban is found.
     * @throws SQLException If a database error occurs.
     */
    public static BansRecord latestBan(String uuid) throws SQLException {
        return getContext()
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
     * @return The created {@link BansRecord}
     * @throws SQLException             If a database error occurs.
     * @throws IllegalArgumentException If the target player does not exist or is already banned.
     */
    public static BansRecord ban(String admin, String target, int period, String reason) throws SQLException {
        if (!playerExists(target)) {
            throw new IllegalArgumentException("Target does not exist!");
        }
        if (isBanned(target)) {
            throw new IllegalArgumentException("Target is already banned!");
        }

        BansRecord record = getContext().newRecord(Tables.bans)
                .setAdmin(admin)
                .setTarget(target)
                .setCreated(LocalDateTime.now())
                .setUntil(period > -1 ? LocalDateTime.now().plusDays(period) : null)
                .setReason(reason);
        record.store();
        return record;
    }

    /**
     * Unbans a player.
     *
     * @param target The UUID of the player to be unbanned.
     * @return The updated {@link BansRecord}
     * @throws SQLException             If a database error occurs.
     * @throws IllegalArgumentException If the target player does not exist or is not banned.
     */
    public static BansRecord unban(String target) throws SQLException {
        if (!playerExists(target)) {
            throw new IllegalArgumentException("Target does not exist!");
        }
        if (!isBanned(target)) {
            throw new IllegalArgumentException("Target is not banned!");
        }

        BansRecord record = latestBan(target)
                .setActive(false);
        record.store();
        return record;
    }
    // endregion

    // region activity

    /**
     * Retrieves the playtime of a player for a specific field.
     *
     * @param uuid  The UUID of the player.
     * @param field The playtime field to retrieve.
     * @return The playtime value for the specified field in seconds.
     * @throws SQLException If a database error occurs.
     */
    public static long getPlaytime(String uuid, Field<Long> field) throws SQLException {
        Record1<Long> timeFetch = getContext()
                .select(field)
                .from(Tables.playtime)
                .where(Tables.playtime.uuid.eq(uuid))
                .fetchOne();
        long time = 0;
        if (timeFetch == null) {
            Log.warn("Player @ does not exist", uuid);
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
     * @return The total playtime in seconds.
     * @throws SQLException If a database error occurs.
     */
    public static long getTotalPlaytime(String uuid) throws SQLException {
        PlaytimeRecord timeFetch = getContext()
                .selectFrom(Tables.playtime)
                .where(Tables.playtime.uuid.eq(uuid))
                .fetchOne();
        long time = 0;
        if (timeFetch == null) {
            Log.warn("Player @ does not exist", uuid);
            createPlaytime(uuid);
        } else {
            for (Field<?> field : timeFetch.fields()) {
                if (field.getType() == Long.class) {
                    Long fieldValue = (Long) field.getValue(timeFetch);
                    if (fieldValue != null) {
                        time += fieldValue;
                    }
                }
            }
        }

        return time;
    }

    /**
     * Creates a new playtime record for a player in the database and returns it.
     *
     * @param uuid The UUID of the player.
     * @return The created {@link PlaytimeRecord} for the player.
     * @throws SQLException If a database error occurs.
     */
    public static PlaytimeRecord createPlaytime(String uuid) throws SQLException {
        PlaytimeRecord record = getContext().newRecord(Tables.playtime)
                .setUuid(uuid);
        record.store();
        return record;
    }

    /**
     * Retrieves the stats record of a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return The {@link StatsRecord} representing the player's statistics or null if no statistics is found.
     * @throws SQLException If a database error occurs.
     */
    public static StatsRecord getStats(String uuid) throws SQLException {
        return getContext()
                .selectFrom(Tables.stats)
                .where(Tables.stats.uuid.eq(uuid))
                .fetchOne();
    }

    /**
     * Creates a new stats record for a player in the database and returns it.
     *
     * @param uuid The UUID of the player.
     * @return The created {@link StatsRecord} for the player.
     * @throws SQLException If a database error occurs.
     */
    public static StatsRecord createStats(String uuid) throws SQLException {
        StatsRecord record = getContext().newRecord(Tables.stats)
                .setUuid(uuid);
        record.store();
        return record;
    }
    // endregion

    // region messages & events

    /**
     * Creates a new message record in the database and returns it.
     *
     * @param server The name or identifier of the server where the message originates.
     * @param from   The sender of the message.
     * @param target The target recipient of the message (player's UUID or team).
     * @param type   The type of the message ({@link MessageType}).
     * @param text   The content of the message.
     * @param locale The locale or language of the message.
     * @return The created {@link MessagesRecord}.
     * @throws SQLException If a database error occurs.
     */
    public static MessagesRecord createMessage(String server, String from, String target, MessageType type, String text, String locale) throws SQLException {
        MessagesRecord record = getContext().newRecord(Tables.messages)
                .setServer(server)
                .setFrom(from)
                .setTarget(target)
                .setType(type)
                .setText(text)
                .setLocale(locale);
        record.store();
        return record;
    }

    /**
     * Creates a new login record in the database.
     *
     * @param server The name or identifier of the server where the message originates.
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @return The created {@link LoginsRecord}.
     * @throws SQLException If a database error occurs.
     */
    public static LoginsRecord createLogin(String server, String uuid, String ip, String name, String locale) throws SQLException {
        LoginsRecord record = getContext().newRecord(Tables.logins)
                .setServer(server)
                .setUuid(uuid)
                .setIp(ip)
                .setName(name)
                .setLocale(locale);
        record.store();
        return record;
    }
    // endregion
}
