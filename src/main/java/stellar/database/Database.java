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
import stellar.database.gen.tables.records.UsersRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

        if (record.getActive() != 1) {
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

        BansRecord bansRecord = Database.getContext().newRecord(Tables.bans);
        bansRecord.setAdmin(admin);
        bansRecord.setTarget(target);
        bansRecord.setCreated(LocalDateTime.now());
        if (period > -1) { bansRecord.setUntil(LocalDateTime.now().plusDays(period)); }
        bansRecord.setReason(reason);
        bansRecord.store();
    }

    public static void unban(String target) throws SQLException {
        if (!Database.playerExists(target)) {
            throw new IllegalArgumentException("Target does not exist!");
        }
        if (!Database.isBanned(target)) {
            throw new IllegalArgumentException("Target is not banned!");
        }

        BansRecord bansRecord = Database.latestBan(target);
        bansRecord.setActive((byte) 0);
        bansRecord.store();
    }

    public static long getPlaytime(String uuid, Field<Long> field) throws SQLException{
        Record1<Long> timeFetch = Database.getContext()
                .select(field)
                .from(Tables.playtime)
                .where(Tables.playtime.uuid.eq(uuid))
                .fetchOne();
        long time = 0;
        if (timeFetch == null) {
            Log.warn("Player @ doesn't exists", uuid);
            PlaytimeRecord playtimeRecord = Database.getContext().newRecord(Tables.playtime);
            playtimeRecord.setUuid(uuid);
            playtimeRecord.store();
        } else {
            time = timeFetch.value1();
        }

        return time;
    }
}
