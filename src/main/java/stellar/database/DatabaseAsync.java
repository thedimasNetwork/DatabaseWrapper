package stellar.database;

import arc.util.Log;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import stellar.database.enums.MessageType;
import stellar.database.enums.PlayerStatus;
import stellar.database.enums.PvpMode;
import stellar.database.gen.Tables;
import stellar.database.gen.tables.records.*;
import stellar.database.types.UnitSnapshot;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static org.jooq.util.postgres.PostgresDSL.arrayCat;
import static stellar.database.Config.getDataSourceAsync;

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
                return DSL.using(getDataSourceAsync().get(), SQLDialect.POSTGRES);
            } catch (Throwable t) {
                throw new RuntimeException("Failed to get a DSL context.", t);
            }
        });
    }

    /**
     * Applies a function to the DSL context asynchronously.
     * Should be used instead of <code>getContextAsync().thenApplyAsync(func)</code>
     *
     * @param <T> The type of the result returned by the function.
     * @param func The function to apply to the DSL context.
     * @return A CompletableFuture that holds the result of applying the function to the DSL context.
     */
    public static <T> CompletableFuture<T> applyContextAsync(Function<DSLContext, T> func) {
        return getContextAsync().thenApplyAsync(func);
    }
    // endregion

    // region players

    /**
     * Asynchronously retrieves a {@link UsersRecord} by UUID from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the {@link UsersRecord} representing the player's data or null if not found.
     */
    public static CompletableFuture<UsersRecord> getPlayerAsync(String uuid) {
        return applyContextAsync(context -> {
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
     * Asynchronously retrieves a {@link UsersRecord} by ID from the database.
     *
     * @param id The ID of the player.
     * @return A CompletableFuture that holds the {@link UsersRecord} representing the player's data or null if not found.
     */
    public static CompletableFuture<UsersRecord> getPlayerAsync(int id) {
        return applyContextAsync(context -> {
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
        return applyContextAsync(context -> {
            try {
                return context.fetchExists(Tables.users, Tables.users.uuid.eq(uuid));
            } catch (DataAccessException e) {
                throw new RuntimeException("Error checking player existence.", e);
            }
        });
    }

    /**
     * Asynchronously creates a new {@link UsersRecord} in the database and returns it.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @param admin  True if the player is an admin, false otherwise.
     * @return A CompletableFuture that holds the created {@link UsersRecord}.
     */
    public static CompletableFuture<UsersRecord> createPlayerAsync(String uuid, String ip, String name, String locale, boolean admin) {
        return applyContextAsync(context -> {
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
     * Asynchronously updates player's info in the database.
     *
     * @param uuid   The UUID of the player.
     * @param ip     The IP address of the player.
     * @param name   The name of the player.
     * @param locale The locale of the player.
     * @return A CompletableFuture that holds the updated {@link UsersRecord}.
     */
    public static CompletableFuture<UsersRecord> updatePlayerAsync(String uuid, String name, String locale, String ip) {
        return getPlayerAsync(uuid).thenApplyAsync(record -> {
            if (record == null) {
                throw new IllegalArgumentException("Player does not exist!");
            }

            try {
                record.setName(name)
                        .setLocale(locale)
                        .setIp(ip);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error updating player info.", e);
            }
        });
    }

    /**
     * Asynchronously creates a new {@link UsersRecord} along with {@link PlaytimeRecord} and stats {@link StatsRecord} in the database.
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
     * Asynchronously creates a new {@link IpCachedRecord} in the database and returns it.
     *
     * @param ip    The IP address for which the record is being created.
     * @param proxy True if the IP is associated with a proxy, false otherwise.
     * @param vpn   True if the IP is associated with a VPN, false otherwise.
     * @param type  The <a href="https://proxycheck.io/api/#type_responses">Type</a> of the IP address.
     * @param risk  The <a href="https://proxycheck.io/api/#risk_score">Risk Score</a> of the IP address.
     * @return A CompletableFuture that holds the created {@link IpCachedRecord}.
     * @see <a href="https://proxycheck.io/api/">Proxycheck API docs</a>
     */
    public static CompletableFuture<IpCachedRecord> createIpAsync(String ip, boolean proxy, boolean vpn, String type, int risk) {
        return applyContextAsync(context -> {
            try {
                IpCachedRecord record = context.newRecord(Tables.ipCached)
                        .setIp(ip)
                        .setProxy(proxy)
                        .setVpn(vpn)
                        .setType(type)
                        .setRisk((short) risk);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating IP record.", e);
            }
        });
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
                return context.select(Tables.logins.ip)
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
                return context.select(Tables.logins.name)
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
     * Asynchronously retrieves the latest {@link BansRecord} for a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the {@link BansRecord} representing the latest ban or null if no ban is found.
     */
    public static CompletableFuture<BansRecord> latestBanAsync(String uuid) {
        return applyContextAsync(context -> {
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

            return record.getUntil() == null || !record.getUntil().isBefore(OffsetDateTime.now());
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
                BansRecord record = context.newRecord(Tables.bans)
                        .setAdmin(admin)
                        .setTarget(target)
                        .setCreated(OffsetDateTime.now())
                        .setUntil(period > -1 ? OffsetDateTime.now().plusDays(period) : null)
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
        return applyContextAsync(context -> {
            try {
                Record1<Long> timeFetch = context.select(field)
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
        return applyContextAsync(context -> {
            try {
                PlaytimeRecord timeFetch = context.selectFrom(Tables.playtime)
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
     * Asynchronously creates a new {@link PlaytimeRecord} for a player in the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the created {@link PlaytimeRecord}.
     */
    public static CompletableFuture<PlaytimeRecord> createPlaytimeAsync(String uuid) {
        return applyContextAsync(context -> {
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
        return applyContextAsync(context -> {
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
     * Asynchronously creates a new {@link StatsRecord} for a player in the database and returns it.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the created {@link StatsRecord}.
     */
    public static CompletableFuture<StatsRecord> createStatsAsync(String uuid) {
        return applyContextAsync(context -> {
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
     * Asynchronously creates a new {@link MessagesRecord} in the database and returns it.
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
        return applyContextAsync(context -> {
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
        return applyContextAsync(context -> {
            try {
                LoginsRecord record = context.newRecord(Tables.logins)
                        .setServer(server)
                        .setUuid(uuid)
                        .setIp(ip)
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

    // region ranked

    /**
     * Asynchronously creates a new {@link EloHistoryRecord} with the default Elo rating of 800 in the database and returns it.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the created {@link EloHistoryRecord}.
     */
    public static CompletableFuture<EloHistoryRecord> initEloHistoryAsync(String uuid) {
        return applyContextAsync(context -> {
            try {
                EloHistoryRecord record = context.newRecord(Tables.eloHistory)
                        .setPlayer(uuid)
                        .setElo(800)
                        .setDelta(800);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error initializing elo history.", e);
            }
        });
    }

    /**
     * Asynchronously creates a new {@link EloHistoryRecord} in the database and returns it.
     *
     * @param uuid   The UUID of the player.
     * @param elo    The new Elo rating of the player.
     * @param delta  The change in Elo rating from the previous rating.
     * @param match  The ID of the match that caused this Elo change.
     * @param result The result of the match (0 - loss, 0.5 - draw, 0.75 - win (not 1st), 1 - win (1st)).
     * @return A CompletableFuture that holds the created {@link EloHistoryRecord}.
     */
    public static CompletableFuture<EloHistoryRecord> createEloHistoryAsync(String uuid, int elo, int delta, int match, float result) {
        return applyContextAsync(context -> {
            try {
                EloHistoryRecord record = context.newRecord(Tables.eloHistory)
                        .setPlayer(uuid)
                        .setElo(elo)
                        .setDelta(delta)
                        .setMatch(match)
                        .setResult(result);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating elo history.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves the Elo history for a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds an array of {@link EloHistoryRecord}s representing the player's Elo history, ordered by timestamp in descending order.
     */
    public static CompletableFuture<EloHistoryRecord[]> getEloHistoryAsync(String uuid) {
        return applyContextAsync(context -> {
            try {
                return context.selectFrom(Tables.eloHistory)
                        .where(Tables.eloHistory.player.eq(uuid))
                        .orderBy(Tables.eloHistory.timestamp.desc())
                        .fetchArray();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching elo history.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves the current Elo rating for a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds the current Elo rating of the player, or null if no rating is found.
     */
    public static CompletableFuture<Integer> getCurrentEloAsync(String uuid) {
        return applyContextAsync(context -> {
            try {
                Record1<Integer> fetch = context.select(Tables.eloHistory.elo)
                        .from(Tables.eloHistory)
                        .where(Tables.eloHistory.player.eq(uuid))
                        .orderBy(Tables.eloHistory.timestamp.desc())
                        .limit(1)
                        .fetchOne();
                return fetch != null ? fetch.value1() : null;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching current elo.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves the total number of matches played by a player with a specific result.
     *
     * @param uuid   The UUID of the player.
     * @param result The result of the match (0 - loss, 0.5 - draw, 0.75 - win (not 1st), 1 - win (1st)).
     * @return A CompletableFuture that holds the total number of matches played by the player with the specified result.
     * @throws IllegalArgumentException If the result is not 0, 0.5, 0.75, or 1.
     */
    public static CompletableFuture<Integer> getTotalMatchesAsync(String uuid, float result) {
        return applyContextAsync(context -> {
            if (result != 0 && result != 0.5f && result != 0.75f && result != 1) {
                throw new IllegalArgumentException("Result must be 0, 0.5, 0.75 or 1");
            }

            try {
                return context.fetchCount(Tables.eloHistory, Tables.eloHistory.player.eq(uuid).and(Tables.eloHistory.result.eq(result)));
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching total matches.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves an aggregated Elo statistic for a player from the database.
     *
     * @param uuid     The UUID of the player.
     * @param function A function that takes a {@link Field<Integer>} and returns an {@link AggregateFunction<Integer>} to be applied to the Elo ratings.
     * @return A CompletableFuture that holds the result of the aggregation function applied to the player's Elo ratings, or null if no data is found.
     */
    public static CompletableFuture<Integer> getAggregatedEloAsync(String uuid, Function<Field<Integer>, AggregateFunction<Integer>> function) {
        return applyContextAsync(context -> {
            try {
                Record1<Integer> fetch = context.select(function.apply(Tables.eloHistory.elo))
                        .from(Tables.eloHistory)
                        .where(Tables.eloHistory.player.eq(uuid))
                        .fetchOne();
                return fetch != null ? fetch.value1() : null;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching aggregated elo.", e);
            }
        });

    }

    /**
     * Asynchronously creates a new {@link MatchesRecord} in the database and returns it.
     *
     * @param started  The start time of the match.
     * @param finished The finish time of the match.
     * @param mode     The PvP mode of the match ({@link PvpMode}).
     * @param mapName  The name of the map played in the match.
     * @param teamA    The array of player UUIDs in team A.
     * @param teamB    The array of player UUIDs in team B.
     * @param teamC    The array of player UUIDs in team C (or empty array if not applicable).
     * @param teamD    The array of player UUIDs in team D (or empty array if not applicable).
     * @return A CompletableFuture that holds the created {@link MatchesRecord}.
     */
    public static CompletableFuture<MatchesRecord> createMatchAsync(OffsetDateTime started, OffsetDateTime finished, PvpMode mode, String mapName, String[] teamA, String[] teamB, String[] teamC, String[] teamD) {
        return applyContextAsync(context -> {
            try {
                MatchesRecord record = context.newRecord(Tables.matches)
                        .setStarted(started)
                        .setFinished(finished)
                        .setMode(mode)
                        .setMap(mapName)
                        .setTeamA(teamA)
                        .setTeamB(teamB)
                        .setTeamC(teamC)
                        .setTeamD(teamD);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating match.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves a {@link MatchesRecord} by ID from the database.
     *
     * @param id The ID of the match.
     * @return A CompletableFuture that holds the {@link MatchesRecord} representing the match or null if not found.
     */
    public static CompletableFuture<MatchesRecord> getMatchAsync(int id) {
        return applyContextAsync(context -> {
            try {
                return context.fetchOne(Tables.matches, Tables.matches.id.eq(id));
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching match.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves an array of {@link MatchesRecord}s for a player from the database.
     *
     * @param uuid The UUID of the player.
     * @return A CompletableFuture that holds an array of {@link MatchesRecord}s representing the matches the player participated in, ordered by finish time in descending order.
     */
    public static CompletableFuture<MatchesRecord[]> getMatchesAsync(String uuid) {
        return applyContextAsync(context -> {
            try {
                Field<String[]> combined = arrayCat(arrayCat(arrayCat(Tables.matches.teamA, Tables.matches.teamB), Tables.matches.teamC), Tables.matches.teamD);
                return context.selectFrom(Tables.matches)
                        .where(DSL.field("{0} = any({1})", Boolean.class, DSL.val(uuid), combined))
                        .orderBy(Tables.matches.finished.desc())
                        .fetchArray();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching matches.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves an array of recent {@link MatchesRecord}s from the database.
     *
     * @param offset The offset from which to start retrieving matches.
     * @param limit  The maximum number of matches to retrieve.
     * @return A CompletableFuture that holds an array of {@link MatchesRecord}s representing recent matches, ordered by finish time in descending order.
     */
    public static CompletableFuture<MatchesRecord[]> getRecentMatchesAsync(int offset, int limit) {
        return applyContextAsync(context -> {
            try {
                return context.selectFrom(Tables.matches)
                        .orderBy(Tables.matches.finished.desc())
                        .limit(limit)
                        .offset(offset)
                        .fetchArray();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching recent matches.", e);
            }
        });
    }
    // endregion

    // region hexes
    /**
     * Asynchronously creates a new {@link HexMatchesRecord} in the database and returns it.
     *
     * @param planet The name of the planet where the hex match takes place.
     * @param map    The name of the map for the hex match.
     * @return A CompletableFuture that holds the created {@link HexMatchesRecord}.
     */
    public static CompletableFuture<HexMatchesRecord> createHexMatchAsync(String planet, String map) {
        return applyContextAsync(context -> {
            try {
                HexMatchesRecord record = context.newRecord(Tables.hexMatches)
                        .setPlanet(planet)
                        .setMap(map);
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating hex match.", e);
            }
        });
    }

    /**
     * Asynchronously retrieves a {@link HexMatchesRecord} by ID from the database.
     *
     * @param id The ID of the hex match.
     * @return A CompletableFuture that holds the {@link HexMatchesRecord} representing the hex match or null if not found.
     */
    public static CompletableFuture<HexMatchesRecord> getHexMatchAsync(int id) {
        return applyContextAsync(context -> {
            try {
                return context.fetchOne(Tables.hexMatches, Tables.hexMatches.id.eq(id));
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching hex match.", e);
            }
        });
    }

    /**
     * Asynchronously checks if a hex match with the given ID exists in the database.
     *
     * @param id The ID of the hex match.
     * @return A CompletableFuture that holds true if the hex match exists, false otherwise.
     */
    public static CompletableFuture<Boolean> hexMatchExistsAsync(int id) {
        return applyContextAsync(context -> {
            try {
                return context.fetchExists(Tables.hexMatches, Tables.hexMatches.id.eq(id));
            } catch (DataAccessException e) {
                throw new RuntimeException("Error checking hex match existence.", e);
            }
        });
    }

    /**
     * Asynchronously finishes a hex match by setting its finished time to the current time.
     *
     * @param id The ID of the hex match to finish.
     * @return A CompletableFuture that holds the updated {@link HexMatchesRecord}.
     * @throws IllegalArgumentException if the match does not exist.
     */
    public static CompletableFuture<HexMatchesRecord> finishHexMatchAsync(int id) {
        return hexMatchExistsAsync(id).thenComposeAsync(exists -> {
            if (!exists) {
                throw new IllegalArgumentException("Match does not exist!");
            }
            return getContextAsync();
        }).thenApplyAsync(context -> {
            try {
                return context.update(Tables.hexMatches)
                        .set(Tables.hexMatches.finished, OffsetDateTime.now())
                        .where(Tables.hexMatches.id.eq(id))
                        .execute();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error finishing hex match.", e);
            }
        }).thenComposeAsync(ignored -> getHexMatchAsync(id));
    }

    /**
     * Asynchronously creates a new {@link HexSnapshotsRecord} in the database and returns it.
     *
     * @param match The ID of the hex match this snapshot belongs to.
     * @param units An array of {@link UnitSnapshot} objects representing the units in the snapshot.
     * @return A CompletableFuture that holds the created {@link HexSnapshotsRecord}.
     * @throws IllegalArgumentException if the specified match does not exist.
     */
    public static CompletableFuture<HexSnapshotsRecord> createHexSnapshotAsync(int match, UnitSnapshot[] units) {
        return hexMatchExistsAsync(match).thenComposeAsync(exists -> {
            if (!exists) {
                throw new IllegalArgumentException("Match does not exist!");
            }
            return getContextAsync();
        }).thenApplyAsync(context -> {
            try {
                HexSnapshotsRecord record = context.newRecord(Tables.hexSnapshots)
                        .setMatch(match)
                        .setUnits(List.of(units));
                record.store();
                return record;
            } catch (DataAccessException e) {
                throw new RuntimeException("Error creating hex snapshot.", e);
            }
        });
    }
    // endregion
}
