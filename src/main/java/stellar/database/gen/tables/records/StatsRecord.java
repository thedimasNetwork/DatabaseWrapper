/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record17;
import org.jooq.Row17;
import org.jooq.impl.UpdatableRecordImpl;

import stellar.database.gen.tables.Stats;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StatsRecord extends UpdatableRecordImpl<StatsRecord> implements Record17<String, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>mindustry.stats.uuid</code>.
     */
    public void setUuid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>mindustry.stats.uuid</code>.
     */
    public String getUuid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>mindustry.stats.waves</code>.
     */
    public void setWaves(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>mindustry.stats.waves</code>.
     */
    public Integer getWaves() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>mindustry.stats.survivals</code>.
     */
    public void setSurvivals(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>mindustry.stats.survivals</code>.
     */
    public Integer getSurvivals() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>mindustry.stats.attacks</code>.
     */
    public void setAttacks(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>mindustry.stats.attacks</code>.
     */
    public Integer getAttacks() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>mindustry.stats.pvp</code>.
     */
    public void setPvp(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>mindustry.stats.pvp</code>.
     */
    public Integer getPvp() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>mindustry.stats.hexes_captured</code>.
     */
    public void setHexesCaptured(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>mindustry.stats.hexes_captured</code>.
     */
    public Integer getHexesCaptured() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>mindustry.stats.hexes_destroyed</code>.
     */
    public void setHexesDestroyed(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>mindustry.stats.hexes_destroyed</code>.
     */
    public Integer getHexesDestroyed() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>mindustry.stats.hexes_lost</code>.
     */
    public void setHexesLost(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>mindustry.stats.hexes_lost</code>.
     */
    public Integer getHexesLost() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>mindustry.stats.hex_wins</code>.
     */
    public void setHexWins(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>mindustry.stats.hex_wins</code>.
     */
    public Integer getHexWins() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>mindustry.stats.hex_losses</code>.
     */
    public void setHexLosses(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>mindustry.stats.hex_losses</code>.
     */
    public Integer getHexLosses() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>mindustry.stats.hex_score</code>.
     */
    public void setHexScore(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>mindustry.stats.hex_score</code>.
     */
    public Integer getHexScore() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>mindustry.stats.built</code>.
     */
    public void setBuilt(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>mindustry.stats.built</code>.
     */
    public Integer getBuilt() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>mindustry.stats.broken</code>.
     */
    public void setBroken(Integer value) {
        set(12, value);
    }

    /**
     * Getter for <code>mindustry.stats.broken</code>.
     */
    public Integer getBroken() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>mindustry.stats.kills</code>.
     */
    public void setKills(Integer value) {
        set(13, value);
    }

    /**
     * Getter for <code>mindustry.stats.kills</code>.
     */
    public Integer getKills() {
        return (Integer) get(13);
    }

    /**
     * Setter for <code>mindustry.stats.deaths</code>.
     */
    public void setDeaths(Integer value) {
        set(14, value);
    }

    /**
     * Getter for <code>mindustry.stats.deaths</code>.
     */
    public Integer getDeaths() {
        return (Integer) get(14);
    }

    /**
     * Setter for <code>mindustry.stats.logins</code>.
     */
    public void setLogins(Integer value) {
        set(15, value);
    }

    /**
     * Getter for <code>mindustry.stats.logins</code>.
     */
    public Integer getLogins() {
        return (Integer) get(15);
    }

    /**
     * Setter for <code>mindustry.stats.messages</code>.
     */
    public void setMessages(Integer value) {
        set(16, value);
    }

    /**
     * Getter for <code>mindustry.stats.messages</code>.
     */
    public Integer getMessages() {
        return (Integer) get(16);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record17 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row17<String, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row17) super.fieldsRow();
    }

    @Override
    public Row17<String, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> valuesRow() {
        return (Row17) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Stats.stats.uuid;
    }

    @Override
    public Field<Integer> field2() {
        return Stats.stats.waves;
    }

    @Override
    public Field<Integer> field3() {
        return Stats.stats.survivals;
    }

    @Override
    public Field<Integer> field4() {
        return Stats.stats.attacks;
    }

    @Override
    public Field<Integer> field5() {
        return Stats.stats.pvp;
    }

    @Override
    public Field<Integer> field6() {
        return Stats.stats.hexesCaptured;
    }

    @Override
    public Field<Integer> field7() {
        return Stats.stats.hexesDestroyed;
    }

    @Override
    public Field<Integer> field8() {
        return Stats.stats.hexesLost;
    }

    @Override
    public Field<Integer> field9() {
        return Stats.stats.hexWins;
    }

    @Override
    public Field<Integer> field10() {
        return Stats.stats.hexLosses;
    }

    @Override
    public Field<Integer> field11() {
        return Stats.stats.hexScore;
    }

    @Override
    public Field<Integer> field12() {
        return Stats.stats.built;
    }

    @Override
    public Field<Integer> field13() {
        return Stats.stats.broken;
    }

    @Override
    public Field<Integer> field14() {
        return Stats.stats.kills;
    }

    @Override
    public Field<Integer> field15() {
        return Stats.stats.deaths;
    }

    @Override
    public Field<Integer> field16() {
        return Stats.stats.logins;
    }

    @Override
    public Field<Integer> field17() {
        return Stats.stats.messages;
    }

    @Override
    public String component1() {
        return getUuid();
    }

    @Override
    public Integer component2() {
        return getWaves();
    }

    @Override
    public Integer component3() {
        return getSurvivals();
    }

    @Override
    public Integer component4() {
        return getAttacks();
    }

    @Override
    public Integer component5() {
        return getPvp();
    }

    @Override
    public Integer component6() {
        return getHexesCaptured();
    }

    @Override
    public Integer component7() {
        return getHexesDestroyed();
    }

    @Override
    public Integer component8() {
        return getHexesLost();
    }

    @Override
    public Integer component9() {
        return getHexWins();
    }

    @Override
    public Integer component10() {
        return getHexLosses();
    }

    @Override
    public Integer component11() {
        return getHexScore();
    }

    @Override
    public Integer component12() {
        return getBuilt();
    }

    @Override
    public Integer component13() {
        return getBroken();
    }

    @Override
    public Integer component14() {
        return getKills();
    }

    @Override
    public Integer component15() {
        return getDeaths();
    }

    @Override
    public Integer component16() {
        return getLogins();
    }

    @Override
    public Integer component17() {
        return getMessages();
    }

    @Override
    public String value1() {
        return getUuid();
    }

    @Override
    public Integer value2() {
        return getWaves();
    }

    @Override
    public Integer value3() {
        return getSurvivals();
    }

    @Override
    public Integer value4() {
        return getAttacks();
    }

    @Override
    public Integer value5() {
        return getPvp();
    }

    @Override
    public Integer value6() {
        return getHexesCaptured();
    }

    @Override
    public Integer value7() {
        return getHexesDestroyed();
    }

    @Override
    public Integer value8() {
        return getHexesLost();
    }

    @Override
    public Integer value9() {
        return getHexWins();
    }

    @Override
    public Integer value10() {
        return getHexLosses();
    }

    @Override
    public Integer value11() {
        return getHexScore();
    }

    @Override
    public Integer value12() {
        return getBuilt();
    }

    @Override
    public Integer value13() {
        return getBroken();
    }

    @Override
    public Integer value14() {
        return getKills();
    }

    @Override
    public Integer value15() {
        return getDeaths();
    }

    @Override
    public Integer value16() {
        return getLogins();
    }

    @Override
    public Integer value17() {
        return getMessages();
    }

    @Override
    public StatsRecord value1(String value) {
        setUuid(value);
        return this;
    }

    @Override
    public StatsRecord value2(Integer value) {
        setWaves(value);
        return this;
    }

    @Override
    public StatsRecord value3(Integer value) {
        setSurvivals(value);
        return this;
    }

    @Override
    public StatsRecord value4(Integer value) {
        setAttacks(value);
        return this;
    }

    @Override
    public StatsRecord value5(Integer value) {
        setPvp(value);
        return this;
    }

    @Override
    public StatsRecord value6(Integer value) {
        setHexesCaptured(value);
        return this;
    }

    @Override
    public StatsRecord value7(Integer value) {
        setHexesDestroyed(value);
        return this;
    }

    @Override
    public StatsRecord value8(Integer value) {
        setHexesLost(value);
        return this;
    }

    @Override
    public StatsRecord value9(Integer value) {
        setHexWins(value);
        return this;
    }

    @Override
    public StatsRecord value10(Integer value) {
        setHexLosses(value);
        return this;
    }

    @Override
    public StatsRecord value11(Integer value) {
        setHexScore(value);
        return this;
    }

    @Override
    public StatsRecord value12(Integer value) {
        setBuilt(value);
        return this;
    }

    @Override
    public StatsRecord value13(Integer value) {
        setBroken(value);
        return this;
    }

    @Override
    public StatsRecord value14(Integer value) {
        setKills(value);
        return this;
    }

    @Override
    public StatsRecord value15(Integer value) {
        setDeaths(value);
        return this;
    }

    @Override
    public StatsRecord value16(Integer value) {
        setLogins(value);
        return this;
    }

    @Override
    public StatsRecord value17(Integer value) {
        setMessages(value);
        return this;
    }

    @Override
    public StatsRecord values(String value1, Integer value2, Integer value3, Integer value4, Integer value5, Integer value6, Integer value7, Integer value8, Integer value9, Integer value10, Integer value11, Integer value12, Integer value13, Integer value14, Integer value15, Integer value16, Integer value17) {
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
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StatsRecord
     */
    public StatsRecord() {
        super(Stats.stats);
    }

    /**
     * Create a detached, initialised StatsRecord
     */
    public StatsRecord(String uuid, Integer waves, Integer survivals, Integer attacks, Integer pvp, Integer hexesCaptured, Integer hexesDestroyed, Integer hexesLost, Integer hexWins, Integer hexLosses, Integer hexScore, Integer built, Integer broken, Integer kills, Integer deaths, Integer logins, Integer messages) {
        super(Stats.stats);

        setUuid(uuid);
        setWaves(waves);
        setSurvivals(survivals);
        setAttacks(attacks);
        setPvp(pvp);
        setHexesCaptured(hexesCaptured);
        setHexesDestroyed(hexesDestroyed);
        setHexesLost(hexesLost);
        setHexWins(hexWins);
        setHexLosses(hexLosses);
        setHexScore(hexScore);
        setBuilt(built);
        setBroken(broken);
        setKills(kills);
        setDeaths(deaths);
        setLogins(logins);
        setMessages(messages);
    }
}