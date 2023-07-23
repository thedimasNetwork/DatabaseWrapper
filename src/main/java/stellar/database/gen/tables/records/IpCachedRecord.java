/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import stellar.database.gen.tables.IpCached;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IpCachedRecord extends UpdatableRecordImpl<IpCachedRecord> implements Record6<Integer, String, Boolean, Boolean, String, Short> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>mindustry.ip_cached.id</code>.
     */
    public IpCachedRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ip_cached.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>mindustry.ip_cached.ip</code>.
     */
    public IpCachedRecord setIp(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ip_cached.ip</code>.
     */
    public String getIp() {
        return (String) get(1);
    }

    /**
     * Setter for <code>mindustry.ip_cached.proxy</code>.
     */
    public IpCachedRecord setProxy(Boolean value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ip_cached.proxy</code>.
     */
    public Boolean getProxy() {
        return (Boolean) get(2);
    }

    /**
     * Setter for <code>mindustry.ip_cached.vpn</code>.
     */
    public IpCachedRecord setVpn(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ip_cached.vpn</code>.
     */
    public Boolean getVpn() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>mindustry.ip_cached.type</code>.
     */
    public IpCachedRecord setType(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ip_cached.type</code>.
     */
    public String getType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>mindustry.ip_cached.risk</code>.
     */
    public IpCachedRecord setRisk(Short value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.ip_cached.risk</code>.
     */
    public Short getRisk() {
        return (Short) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, Boolean, Boolean, String, Short> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, Boolean, Boolean, String, Short> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return IpCached.ipCached.id;
    }

    @Override
    public Field<String> field2() {
        return IpCached.ipCached.ip;
    }

    @Override
    public Field<Boolean> field3() {
        return IpCached.ipCached.proxy;
    }

    @Override
    public Field<Boolean> field4() {
        return IpCached.ipCached.vpn;
    }

    @Override
    public Field<String> field5() {
        return IpCached.ipCached.type;
    }

    @Override
    public Field<Short> field6() {
        return IpCached.ipCached.risk;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getIp();
    }

    @Override
    public Boolean component3() {
        return getProxy();
    }

    @Override
    public Boolean component4() {
        return getVpn();
    }

    @Override
    public String component5() {
        return getType();
    }

    @Override
    public Short component6() {
        return getRisk();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getIp();
    }

    @Override
    public Boolean value3() {
        return getProxy();
    }

    @Override
    public Boolean value4() {
        return getVpn();
    }

    @Override
    public String value5() {
        return getType();
    }

    @Override
    public Short value6() {
        return getRisk();
    }

    @Override
    public IpCachedRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public IpCachedRecord value2(String value) {
        setIp(value);
        return this;
    }

    @Override
    public IpCachedRecord value3(Boolean value) {
        setProxy(value);
        return this;
    }

    @Override
    public IpCachedRecord value4(Boolean value) {
        setVpn(value);
        return this;
    }

    @Override
    public IpCachedRecord value5(String value) {
        setType(value);
        return this;
    }

    @Override
    public IpCachedRecord value6(Short value) {
        setRisk(value);
        return this;
    }

    @Override
    public IpCachedRecord values(Integer value1, String value2, Boolean value3, Boolean value4, String value5, Short value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached IpCachedRecord
     */
    public IpCachedRecord() {
        super(IpCached.ipCached);
    }

    /**
     * Create a detached, initialised IpCachedRecord
     */
    public IpCachedRecord(Integer id, String ip, Boolean proxy, Boolean vpn, String type, Short risk) {
        super(IpCached.ipCached);

        setId(id);
        setIp(ip);
        setProxy(proxy);
        setVpn(vpn);
        setType(type);
        setRisk(risk);
    }
}
