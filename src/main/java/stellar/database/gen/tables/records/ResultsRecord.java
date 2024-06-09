/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables.records;


import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import stellar.database.gen.tables.Results;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ResultsRecord extends UpdatableRecordImpl<ResultsRecord> implements Record7<Integer, Integer, String[], Integer, OffsetDateTime, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>mindustry.results.id</code>.
     */
    public ResultsRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.results.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>mindustry.results.match</code>.
     */
    public ResultsRecord setMatch(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.results.match</code>.
     */
    public Integer getMatch() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>mindustry.results.players</code>.
     */
    public ResultsRecord setPlayers(String[] value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.results.players</code>.
     */
    public String[] getPlayers() {
        return (String[]) get(2);
    }

    /**
     * Setter for <code>mindustry.results.delta_elo</code>.
     */
    public ResultsRecord setDeltaElo(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.results.delta_elo</code>.
     */
    public Integer getDeltaElo() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>mindustry.results.started</code>.
     */
    public ResultsRecord setStarted(OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.results.started</code>.
     */
    public OffsetDateTime getStarted() {
        return (OffsetDateTime) get(4);
    }

    /**
     * Setter for <code>mindustry.results.finished</code>.
     */
    public ResultsRecord setFinished(OffsetDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.results.finished</code>.
     */
    public OffsetDateTime getFinished() {
        return (OffsetDateTime) get(5);
    }

    /**
     * Setter for <code>mindustry.results.reason</code>.
     */
    public ResultsRecord setReason(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.results.reason</code>.
     */
    public String getReason() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, String[], Integer, OffsetDateTime, OffsetDateTime, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, Integer, String[], Integer, OffsetDateTime, OffsetDateTime, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Results.results.id;
    }

    @Override
    public Field<Integer> field2() {
        return Results.results.match;
    }

    @Override
    public Field<String[]> field3() {
        return Results.results.players;
    }

    @Override
    public Field<Integer> field4() {
        return Results.results.deltaElo;
    }

    @Override
    public Field<OffsetDateTime> field5() {
        return Results.results.started;
    }

    @Override
    public Field<OffsetDateTime> field6() {
        return Results.results.finished;
    }

    @Override
    public Field<String> field7() {
        return Results.results.reason;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getMatch();
    }

    @Override
    public String[] component3() {
        return getPlayers();
    }

    @Override
    public Integer component4() {
        return getDeltaElo();
    }

    @Override
    public OffsetDateTime component5() {
        return getStarted();
    }

    @Override
    public OffsetDateTime component6() {
        return getFinished();
    }

    @Override
    public String component7() {
        return getReason();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getMatch();
    }

    @Override
    public String[] value3() {
        return getPlayers();
    }

    @Override
    public Integer value4() {
        return getDeltaElo();
    }

    @Override
    public OffsetDateTime value5() {
        return getStarted();
    }

    @Override
    public OffsetDateTime value6() {
        return getFinished();
    }

    @Override
    public String value7() {
        return getReason();
    }

    @Override
    public ResultsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ResultsRecord value2(Integer value) {
        setMatch(value);
        return this;
    }

    @Override
    public ResultsRecord value3(String[] value) {
        setPlayers(value);
        return this;
    }

    @Override
    public ResultsRecord value4(Integer value) {
        setDeltaElo(value);
        return this;
    }

    @Override
    public ResultsRecord value5(OffsetDateTime value) {
        setStarted(value);
        return this;
    }

    @Override
    public ResultsRecord value6(OffsetDateTime value) {
        setFinished(value);
        return this;
    }

    @Override
    public ResultsRecord value7(String value) {
        setReason(value);
        return this;
    }

    @Override
    public ResultsRecord values(Integer value1, Integer value2, String[] value3, Integer value4, OffsetDateTime value5, OffsetDateTime value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ResultsRecord
     */
    public ResultsRecord() {
        super(Results.results);
    }

    /**
     * Create a detached, initialised ResultsRecord
     */
    public ResultsRecord(Integer id, Integer match, String[] players, Integer deltaElo, OffsetDateTime started, OffsetDateTime finished, String reason) {
        super(Results.results);

        setId(id);
        setMatch(match);
        setPlayers(players);
        setDeltaElo(deltaElo);
        setStarted(started);
        setFinished(finished);
        setReason(reason);
    }
}
