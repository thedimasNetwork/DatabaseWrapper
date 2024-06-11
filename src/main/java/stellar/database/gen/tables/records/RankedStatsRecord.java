/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables.records;


import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;

import stellar.database.gen.tables.RankedStats;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RankedStatsRecord extends UpdatableRecordImpl<RankedStatsRecord> implements Record10<String, OffsetDateTime, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>mindustry.ranked_stats.uuid</code>.
     */
    public RankedStatsRecord setUuid(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.uuid</code>.
     */
    public String getUuid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.created_at</code>.
     */
    public RankedStatsRecord setCreatedAt(OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.created_at</code>.
     */
    public OffsetDateTime getCreatedAt() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.matches</code>.
     */
    public RankedStatsRecord setMatches(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.matches</code>.
     */
    public Integer getMatches() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.wins</code>.
     */
    public RankedStatsRecord setWins(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.wins</code>.
     */
    public Integer getWins() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.losses</code>.
     */
    public RankedStatsRecord setLosses(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.losses</code>.
     */
    public Integer getLosses() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.draws</code>.
     */
    public RankedStatsRecord setDraws(Integer value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.draws</code>.
     */
    public Integer getDraws() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.lowest_elo</code>.
     */
    public RankedStatsRecord setLowestElo(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.lowest_elo</code>.
     */
    public Integer getLowestElo() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.highest_elo</code>.
     */
    public RankedStatsRecord setHighestElo(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.highest_elo</code>.
     */
    public Integer getHighestElo() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.current_elo</code>.
     */
    public RankedStatsRecord setCurrentElo(Integer value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.current_elo</code>.
     */
    public Integer getCurrentElo() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>mindustry.ranked_stats.start_elo</code>.
     */
    public RankedStatsRecord setStartElo(Integer value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ranked_stats.start_elo</code>.
     */
    public Integer getStartElo() {
        return (Integer) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row10<String, OffsetDateTime, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    public Row10<String, OffsetDateTime, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return RankedStats.rankedStats.uuid;
    }

    @Override
    public Field<OffsetDateTime> field2() {
        return RankedStats.rankedStats.createdAt;
    }

    @Override
    public Field<Integer> field3() {
        return RankedStats.rankedStats.matches;
    }

    @Override
    public Field<Integer> field4() {
        return RankedStats.rankedStats.wins;
    }

    @Override
    public Field<Integer> field5() {
        return RankedStats.rankedStats.losses;
    }

    @Override
    public Field<Integer> field6() {
        return RankedStats.rankedStats.draws;
    }

    @Override
    public Field<Integer> field7() {
        return RankedStats.rankedStats.lowestElo;
    }

    @Override
    public Field<Integer> field8() {
        return RankedStats.rankedStats.highestElo;
    }

    @Override
    public Field<Integer> field9() {
        return RankedStats.rankedStats.currentElo;
    }

    @Override
    public Field<Integer> field10() {
        return RankedStats.rankedStats.startElo;
    }

    @Override
    public String component1() {
        return getUuid();
    }

    @Override
    public OffsetDateTime component2() {
        return getCreatedAt();
    }

    @Override
    public Integer component3() {
        return getMatches();
    }

    @Override
    public Integer component4() {
        return getWins();
    }

    @Override
    public Integer component5() {
        return getLosses();
    }

    @Override
    public Integer component6() {
        return getDraws();
    }

    @Override
    public Integer component7() {
        return getLowestElo();
    }

    @Override
    public Integer component8() {
        return getHighestElo();
    }

    @Override
    public Integer component9() {
        return getCurrentElo();
    }

    @Override
    public Integer component10() {
        return getStartElo();
    }

    @Override
    public String value1() {
        return getUuid();
    }

    @Override
    public OffsetDateTime value2() {
        return getCreatedAt();
    }

    @Override
    public Integer value3() {
        return getMatches();
    }

    @Override
    public Integer value4() {
        return getWins();
    }

    @Override
    public Integer value5() {
        return getLosses();
    }

    @Override
    public Integer value6() {
        return getDraws();
    }

    @Override
    public Integer value7() {
        return getLowestElo();
    }

    @Override
    public Integer value8() {
        return getHighestElo();
    }

    @Override
    public Integer value9() {
        return getCurrentElo();
    }

    @Override
    public Integer value10() {
        return getStartElo();
    }

    @Override
    public RankedStatsRecord value1(String value) {
        setUuid(value);
        return this;
    }

    @Override
    public RankedStatsRecord value2(OffsetDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public RankedStatsRecord value3(Integer value) {
        setMatches(value);
        return this;
    }

    @Override
    public RankedStatsRecord value4(Integer value) {
        setWins(value);
        return this;
    }

    @Override
    public RankedStatsRecord value5(Integer value) {
        setLosses(value);
        return this;
    }

    @Override
    public RankedStatsRecord value6(Integer value) {
        setDraws(value);
        return this;
    }

    @Override
    public RankedStatsRecord value7(Integer value) {
        setLowestElo(value);
        return this;
    }

    @Override
    public RankedStatsRecord value8(Integer value) {
        setHighestElo(value);
        return this;
    }

    @Override
    public RankedStatsRecord value9(Integer value) {
        setCurrentElo(value);
        return this;
    }

    @Override
    public RankedStatsRecord value10(Integer value) {
        setStartElo(value);
        return this;
    }

    @Override
    public RankedStatsRecord values(String value1, OffsetDateTime value2, Integer value3, Integer value4, Integer value5, Integer value6, Integer value7, Integer value8, Integer value9, Integer value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RankedStatsRecord
     */
    public RankedStatsRecord() {
        super(RankedStats.rankedStats);
    }

    /**
     * Create a detached, initialised RankedStatsRecord
     */
    public RankedStatsRecord(String uuid, OffsetDateTime createdAt, Integer matches, Integer wins, Integer losses, Integer draws, Integer lowestElo, Integer highestElo, Integer currentElo, Integer startElo) {
        super(RankedStats.rankedStats);

        setUuid(uuid);
        setCreatedAt(createdAt);
        setMatches(matches);
        setWins(wins);
        setLosses(losses);
        setDraws(draws);
        setLowestElo(lowestElo);
        setHighestElo(highestElo);
        setCurrentElo(currentElo);
        setStartElo(startElo);
    }
}