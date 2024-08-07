/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables.records;


import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import stellar.database.gen.tables.HexMatches;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HexMatchesRecord extends UpdatableRecordImpl<HexMatchesRecord> implements Record5<Integer, OffsetDateTime, OffsetDateTime, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>mindustry.hex_matches.id</code>.
     */
    public HexMatchesRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.hex_matches.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>mindustry.hex_matches.started</code>.
     */
    public HexMatchesRecord setStarted(OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.hex_matches.started</code>.
     */
    public OffsetDateTime getStarted() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>mindustry.hex_matches.finished</code>.
     */
    public HexMatchesRecord setFinished(OffsetDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.hex_matches.finished</code>.
     */
    public OffsetDateTime getFinished() {
        return (OffsetDateTime) get(2);
    }

    /**
     * Setter for <code>mindustry.hex_matches.planet</code>.
     */
    public HexMatchesRecord setPlanet(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.hex_matches.planet</code>.
     */
    public String getPlanet() {
        return (String) get(3);
    }

    /**
     * Setter for <code>mindustry.hex_matches.map</code>.
     */
    public HexMatchesRecord setMap(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.hex_matches.map</code>.
     */
    public String getMap() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, OffsetDateTime, OffsetDateTime, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, OffsetDateTime, OffsetDateTime, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return HexMatches.hexMatches.id;
    }

    @Override
    public Field<OffsetDateTime> field2() {
        return HexMatches.hexMatches.started;
    }

    @Override
    public Field<OffsetDateTime> field3() {
        return HexMatches.hexMatches.finished;
    }

    @Override
    public Field<String> field4() {
        return HexMatches.hexMatches.planet;
    }

    @Override
    public Field<String> field5() {
        return HexMatches.hexMatches.map;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public OffsetDateTime component2() {
        return getStarted();
    }

    @Override
    public OffsetDateTime component3() {
        return getFinished();
    }

    @Override
    public String component4() {
        return getPlanet();
    }

    @Override
    public String component5() {
        return getMap();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public OffsetDateTime value2() {
        return getStarted();
    }

    @Override
    public OffsetDateTime value3() {
        return getFinished();
    }

    @Override
    public String value4() {
        return getPlanet();
    }

    @Override
    public String value5() {
        return getMap();
    }

    @Override
    public HexMatchesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public HexMatchesRecord value2(OffsetDateTime value) {
        setStarted(value);
        return this;
    }

    @Override
    public HexMatchesRecord value3(OffsetDateTime value) {
        setFinished(value);
        return this;
    }

    @Override
    public HexMatchesRecord value4(String value) {
        setPlanet(value);
        return this;
    }

    @Override
    public HexMatchesRecord value5(String value) {
        setMap(value);
        return this;
    }

    @Override
    public HexMatchesRecord values(Integer value1, OffsetDateTime value2, OffsetDateTime value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HexMatchesRecord
     */
    public HexMatchesRecord() {
        super(HexMatches.hexMatches);
    }

    /**
     * Create a detached, initialised HexMatchesRecord
     */
    public HexMatchesRecord(Integer id, OffsetDateTime started, OffsetDateTime finished, String planet, String map) {
        super(HexMatches.hexMatches);

        setId(id);
        setStarted(started);
        setFinished(finished);
        setPlanet(planet);
        setMap(map);
    }
}
