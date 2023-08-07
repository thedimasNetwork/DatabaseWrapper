package stellar.database;

import arc.util.Log;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import stellar.database.enums.MessageType;
import stellar.database.enums.PlayerStatus;
import stellar.database.gen.Tables;
import stellar.database.gen.tables.records.*;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static stellar.database.Config.getConnectionAsync;

/**
 * A utility class for asynchronously handling database operations.
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public class DatabaseAsync {
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
     * Asynchronously retrieves the DSL context for database queries.
     *
     * @return A CompletableFuture that holds the DSL context.
     */
    public static CompletableFuture<DSLContext> getContextAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return DSL.using(getConnectionAsync().get(), SQLDialect.MYSQL);
            } catch (Throwable t) {
                throw new RuntimeException("Failed to get a DSL context.", t);
            }
        });
    }
    // endregion

    // region players

    /**
     * Asynchronously retrieves a player's record by UUID from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the {@link UsersRecord} representing the player's data or null if not found.
     */
    public static CompletableFuture<UsersRecord> getPlayerAsync(String uuid) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                return context.selectFrom(Tables.users)
                        .where(Tables.users.uuid.eq(uuid))
                        .fetchOne();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching player by UUID.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves a player's record by ID from the database.
     *
     * @param id The ID of the player.
     * @return A CompletableFuture that holds the {@link UsersRecord} representing the player's data or null if not found.
     */
    public static CompletableFuture<UsersRecord> getPlayerAsync(int id) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                return context.selectFrom(Tables.users)
                        .where(Tables.users.id.eq(id))
                        .fetchOne();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching player by ID.", e);
            }
        });
    }

    /**
     * Asynchronously checks if a player with the given UUID exists in the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds true if the player exists, false otherwise.
     */
    public static CompletableFuture<Boolean> playerExistsAsync(String uuid) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                return context.fetchExists(Tables.users, Tables.users.uuid.eq(uuid));
            } catch (DataAccessException e) {
                throw new RuntimeException("Error checking player existence.", e);
            }
        });
    }

    /**
     * Asynchronously creates a new player record in the database.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @param admin  True if the player is an admin, false otherwise.
     * @return A CompletableFuture that holds the created {@link UsersRecord}.
     */
    public static CompletableFuture<UsersRecord> createPlayerAsync(String uuid, String ip, String name, String locale, boolean admin) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                UsersRecord record = context.newRecord(Tables.users)
                        .setUuid(uuid)
                        .setIp(ip)
                        .setName(name)
                        .setLocale(locale)
                        .setStatus(admin ? PlayerStatus.admin : PlayerStatus.basic);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating player.", e);
            }
        });
    }

    /**
     * Asynchronously creates a new player record along with playtime and stats records in the database.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @param admin  True if the player is an admin, false otherwise.
     * @return A CompletableFuture that holds the created {@link UsersRecord}.
     */
    public static CompletableFuture<UsersRecord> createFullPlayerAsync(String uuid, String ip, String name, String locale, boolean admin) {
        return CompletableFuture.allOf(
                createPlaytimeAsync(uuid),
                createStatsAsync(uuid)
        ).thenComposeAsync(ignored -> createPlayerAsync(uuid, ip, name, locale, admin));
    }

    /**
     * Asynchronously retrieves an array of IP addresses used by a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds an array of IP addresses used by the specified player.
     */
    public static CompletableFuture<String[]> getIpsAsync(String uuid) {
        return playerExistsAsync(uuid).thenComposeAsync(exists -> {
            if (!exists) {
                throw new IllegalArgumentException("Player does not exist!");
            }

            return getContextAsync();
        }).thenApplyAsync(context -> {
            try {
                return context
                        .select(Tables.logins.ip)
                        .from(Tables.logins)
                        .where(Tables.logins.uuid.eq(uuid))
                        .groupBy(Tables.logins.ip)
                        .fetchArray(0, String.class);
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching IPs.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves an array of names used by a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds an array of names used by the specified player.
     */
    public static CompletableFuture<String[]> getNamesAsync(String uuid) {
        return playerExistsAsync(uuid).thenComposeAsync(exists -> {
            if (!exists) {
                throw new IllegalArgumentException("Player does not exist!");
            }

            return getContextAsync();
        }).thenApplyAsync(context -> {
            try {
                return context
                        .select(Tables.logins.name)
                        .from(Tables.logins)
                        .where(Tables.logins.uuid.eq(uuid))
                        .groupBy(Tables.logins.name)
                        .fetchArray(0, String.class);
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching names.", e);
            }
        });
    }
    // endregion

    // region bans

    /**
     * Asynchronously retrieves the latest ban record for a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the {@link BansRecord} representing the latest ban or null if no ban is found.
     */
    public static CompletableFuture<BansRecord> latestBanAsync(String uuid) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                return context.selectFrom(Tables.bans)
                        .where(Tables.bans.target.eq(uuid))
                        .orderBy(Tables.bans.id.desc())
                        .limit(1)
                        .fetchOne();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching latest ban.", e);
            }
        });
    }

    /**
     * Asynchronously checks if a player is banned.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds true if the player is banned, false otherwise.
     */
    public static CompletableFuture<Boolean> isBannedAsync(String uuid) {
        return latestBanAsync(uuid).thenApplyAsync(record -> {
            if (record == null) {
                return false;
            }

            if (!record.isActive()) {
                return false;
            }

            LocalDateTime now = LocalDateTime.now();
            return record.getUntil() == null || !record.getUntil().isBefore(now);
        });
    }

    /**
     * Asynchronously bans a player.
     *
     * @param admin  The UUID of the admin performing the ban.
     * @param target The UUID of the player to be banned.
     * @param period The ban period in days, or -1 for a permanent ban.
     * @param reason The reason for the ban.
     * @return A CompletableFuture that holds the created {@link BansRecord}.
     */
    public static CompletableFuture<BansRecord> banAsync(String admin, String target, int period, String reason) {
        return playerExistsAsync(target).thenComposeAsync(exists -> {
            if (!exists) {
                throw new IllegalArgumentException("Target does not exist!");
            }
            return isBannedAsync(target);
        }).thenComposeAsync(isBanned -> {
            if (isBanned) {
                throw new IllegalArgumentException("Target is already banned!");
            }
            return getContextAsync();
        }).thenApplyAsync(context -> {
            try {
                LocalDateTime until = (period > -1) ? LocalDateTime.now().plusDays(period) : null;
                BansRecord record = context.newRecord(Tables.bans)
                        .setAdmin(admin)
                        .setTarget(target)
                        .setCreated(LocalDateTime.now())
                        .setUntil(until)
                        .setReason(reason);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error banning player.", e);
            }
        });
    }

    /**
     * Asynchronously unbans a player.
     *
     * @param target The UUID of the player to be unbanned.
     * @return A CompletableFuture that holds the updated {@link BansRecord}.
     */
    public static CompletableFuture<BansRecord> unbanAsync(String target) {
        return playerExistsAsync(target).thenComposeAsync(exists -> {
            if (!exists) {
                throw new IllegalArgumentException("Target does not exist!");
            }
            return isBannedAsync(target);
        }).thenAcceptAsync(isBanned -> {
            if (!isBanned) {
                throw new IllegalArgumentException("Target is already unbanned!");
            }
        }).thenComposeAsync(ignored -> latestBanAsync(target)).thenApplyAsync(record -> {
            if (record != null) {
                record.setActive(false).store();
            }
            return record;
        });
    }
    // endregion

    // region activity

    /**
     * Asynchronously retrieves the playtime of a player for a specific field.
     *
     * @param uuid  The UUID of the player.
     * @param field The playtime field to retrieve.
     * @return A CompletableFuture that holds the playtime value for the specified field in seconds.
     */
    public static CompletableFuture<Long> getPlaytimeAsync(String uuid, Field<Long> field) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                Record1<Long> timeFetch = context
                        .select(field)
                        .from(Tables.playtime)
                        .where(Tables.playtime.uuid.eq(uuid))
                        .fetchOne();
                long time = 0;
                if (timeFetch == null) {
                    Log.warn("Player @ does not exist", uuid);
                    createPlaytimeAsync(uuid).join();
                } else {
                    time = timeFetch.value1();
                }
                return time;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching playtime.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves the total playtime of a player.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the total playtime in seconds.
     */
    public static CompletableFuture<Long> getTotalPlaytimeAsync(String uuid) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                PlaytimeRecord timeFetch = context
                        .selectFrom(Tables.playtime)
                        .where(Tables.playtime.uuid.eq(uuid))
                        .fetchOne();
                long time = 0;
                if (timeFetch == null) {
                    Log.warn("Player @ does not exist", uuid);
                    createPlaytimeAsync(uuid).join();
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
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching total playtime.", e);
            }
        });
    }

    /**
     * Asynchronously creates a new playtime record for a player in the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the created {@link PlaytimeRecord}.
     */
    public static CompletableFuture<PlaytimeRecord> createPlaytimeAsync(String uuid) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                PlaytimeRecord record = context.newRecord(Tables.playtime)
                        .setUuid(uuid);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating playtime.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves the stats record of a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the {@link StatsRecord} representing the player's statistics or null if no statistics is found.
     */
    public static CompletableFuture<StatsRecord> getStatsAsync(String uuid) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                return context.selectFrom(Tables.stats)
                        .where(Tables.stats.uuid.eq(uuid))
                        .fetchOne();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching stats.", e);
            }
        });
    }

    /**
     * Asynchronously creates a new stats record for a player in the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the created {@link StatsRecord}.
     */
    public static CompletableFuture<StatsRecord> createStatsAsync(String uuid) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                StatsRecord record = context.newRecord(Tables.stats)
                        .setUuid(uuid);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating stats.", e);
            }
        });
    }
    // endregion

    // region messages & events

    /**
     * Asynchronously creates a new message record in the database.
     *
     * @param server The name or identifier of the server where the message originates.
     * @param from   The sender of the message.
     * @param target The target recipient of the message (player's UUID or team).
     * @param type   The type of the message ({@link MessageType}).
     * @param text   The content of the message.
     * @param locale The locale or language of the message.
     * @return A CompletableFuture that holds the created {@link MessagesRecord}.
     */
    public static CompletableFuture<MessagesRecord> createMessageAsync(String server, String from, String target, MessageType type, String text, String locale) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                MessagesRecord record = context.newRecord(Tables.messages)
                        .setServer(server)
                        .setFrom(from)
                        .setTarget(target)
                        .setType(type)
                        .setText(text)
                        .setLocale(locale);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating message.", e);
            }
        });
    }

    /**
     * Creates a new login record in the database.
     *
     * @param server The name or identifier of the server where the message originates.
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @return A CompletableFuture that holds the created {@link LoginsRecord}.
     */
    public static CompletableFuture<LoginsRecord> createLoginAsync(String server, String uuid, String ip, String name, String locale) {
        return getContextAsync().thenApplyAsync(context -> {
            try {
                LoginsRecord record = context.newRecord(Tables.logins)
                        .setServer(server)
                        .setUuid(uuid)
                        .setName(name)
                        .setLocale(locale);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating login.", e);
            }
        });
    }
    // endregion
}
