package stellar.database;

import arc.util.Log;
import arc.util.Nullable;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import stellar.database.gen.Tables;
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

    public static void load(String ip, int port, String name, String user, String password) {
        Database.ip = ip;
        Database.port = port;
        Database.name = name;
        Database.user = user;
        Database.password = password;
    }

    private static String getConnectionUrl() {
        return "jdbc:mysql://" + ip + ":" + port + "/" + name + "?autoReconnect=true";
    }

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

    public static DSLContext getContext() throws SQLException {
        if (context == null) {
            context = DSL.using(getConnection(), SQLDialect.MYSQL);
        }
        return context;
    }

    // region players
    @Nullable
    public static UsersRecord getPlayer(String uuid) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.users)
                .where(Tables.users.uuid.eq(uuid))
                .fetchOne();
    }

    @Nullable
    public static UsersRecord getPlayer(int id) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.users)
                .where(Tables.users.id.eq(id))
                .fetchOne();
    }

    public static boolean playerExists(String uuid) throws SQLException {
        return Database.getContext().fetchExists(Tables.users, Tables.users.uuid.eq(uuid));
    }
    // endregion

    // region bans
    public static BansRecord latestBan(String uuid) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.bans)
                .where(Tables.bans.target.eq(uuid))
                .orderBy(Tables.bans.id.desc())
                .limit(1)
                .fetchOne();
    }

    public static boolean isBanned(String uuid) throws SQLException {
        BansRecord record = latestBan(uuid);
        if (record == null) {
            return false;
        }

        if (!record.getActive()) {
            return false;
        }

        if (record.getUntil() == null) {
            return true;
        }

        return !record.getUntil().isBefore(LocalDateTime.now());
    }

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
    public static long getPlaytime(String uuid, Field<Long> field) throws SQLException {
        Record1<Long> timeFetch = Database.getContext()
                .select(field)
                .from(Tables.playtime)
                .where(Tables.playtime.uuid.eq(uuid))
                .fetchOne();
        long time = 0;
        if (timeFetch == null) {
            Log.warn("Player @ doesn't exists", uuid);
            Database.getContext().newRecord(Tables.playtime)
                    .setUuid(uuid)
                    .store();
        } else {
            time = timeFetch.value1();
        }

        return time;
    }

    public static long getTotalPlaytime(String uuid) throws SQLException {
        PlaytimeRecord timeFetch = Database.getContext()
                .selectFrom(Tables.playtime)
                .where(Tables.playtime.uuid.eq(uuid))
                .fetchOne();
        long time = 0;
        if (timeFetch == null) {
            Log.warn("Player @ doesn't exists", uuid);
            Database.getContext().newRecord(Tables.playtime)
                    .setUuid(uuid)
                    .store();
        } else {
            for (Field<?> field : timeFetch.fields()) {
                if (field.getType() == Long.class) {
                    System.out.println(field.getName());
                    time += (Long) field.getValue(timeFetch);
                }
            }
        }

        return time;
    }

    public static StatsRecord getStats(String uuid) throws SQLException {
        return Database.getContext()
                .selectFrom(Tables.stats)
                .where(Tables.stats.uuid.eq(uuid))
                .fetchOne();
    }
    // endregion
}
