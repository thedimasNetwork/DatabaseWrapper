/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;

import stellar.database.gen.tables.Users;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record11<Integer, String, String, String, String, String, Boolean, Boolean, Integer, Boolean, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>mindustry.users.id</code>.
     */
    public UsersRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>mindustry.users.uuid</code>.
     */
    public UsersRecord setUuid(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.uuid</code>.
     */
    public String getUuid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>mindustry.users.ip</code>.
     */
    public UsersRecord setIp(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.ip</code>.
     */
    public String getIp() {
        return (String) get(2);
    }

    /**
     * Setter for <code>mindustry.users.name</code>.
     */
    public UsersRecord setName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>mindustry.users.locale</code>.
     */
    public UsersRecord setLocale(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.locale</code>.
     */
    public String getLocale() {
        return (String) get(4);
    }

    /**
     * Setter for <code>mindustry.users.translator</code>.
     */
    public UsersRecord setTranslator(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.translator</code>.
     */
    public String getTranslator() {
        return (String) get(5);
    }

    /**
     * Setter for <code>mindustry.users.admin</code>.
     */
    public UsersRecord setAdmin(Boolean value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.admin</code>.
     */
    public Boolean getAdmin() {
        return (Boolean) get(6);
    }

    /**
     * Setter for <code>mindustry.users.jsallowed</code>.
     */
    public UsersRecord setJsallowed(Boolean value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.jsallowed</code>.
     */
    public Boolean getJsallowed() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>mindustry.users.donated</code>.
     */
    public UsersRecord setDonated(Integer value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.donated</code>.
     */
    public Integer getDonated() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>mindustry.users.popup</code>.
     */
    public UsersRecord setPopup(Boolean value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.popup</code>.
     */
    public Boolean getPopup() {
        return (Boolean) get(9);
    }

    /**
     * Setter for <code>mindustry.users.discord</code>.
     */
    public UsersRecord setDiscord(Boolean value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.users.discord</code>.
     */
    public Boolean getDiscord() {
        return (Boolean) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<Integer, String, String, String, String, String, Boolean, Boolean, Integer, Boolean, Boolean> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<Integer, String, String, String, String, String, Boolean, Boolean, Integer, Boolean, Boolean> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Users.users.id;
    }

    @Override
    public Field<String> field2() {
        return Users.users.uuid;
    }

    @Override
    public Field<String> field3() {
        return Users.users.ip;
    }

    @Override
    public Field<String> field4() {
        return Users.users.name;
    }

    @Override
    public Field<String> field5() {
        return Users.users.locale;
    }

    @Override
    public Field<String> field6() {
        return Users.users.translator;
    }

    @Override
    public Field<Boolean> field7() {
        return Users.users.admin;
    }

    @Override
    public Field<Boolean> field8() {
        return Users.users.jsallowed;
    }

    @Override
    public Field<Integer> field9() {
        return Users.users.donated;
    }

    @Override
    public Field<Boolean> field10() {
        return Users.users.popup;
    }

    @Override
    public Field<Boolean> field11() {
        return Users.users.discord;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUuid();
    }

    @Override
    public String component3() {
        return getIp();
    }

    @Override
    public String component4() {
        return getName();
    }

    @Override
    public String component5() {
        return getLocale();
    }

    @Override
    public String component6() {
        return getTranslator();
    }

    @Override
    public Boolean component7() {
        return getAdmin();
    }

    @Override
    public Boolean component8() {
        return getJsallowed();
    }

    @Override
    public Integer component9() {
        return getDonated();
    }

    @Override
    public Boolean component10() {
        return getPopup();
    }

    @Override
    public Boolean component11() {
        return getDiscord();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUuid();
    }

    @Override
    public String value3() {
        return getIp();
    }

    @Override
    public String value4() {
        return getName();
    }

    @Override
    public String value5() {
        return getLocale();
    }

    @Override
    public String value6() {
        return getTranslator();
    }

    @Override
    public Boolean value7() {
        return getAdmin();
    }

    @Override
    public Boolean value8() {
        return getJsallowed();
    }

    @Override
    public Integer value9() {
        return getDonated();
    }

    @Override
    public Boolean value10() {
        return getPopup();
    }

    @Override
    public Boolean value11() {
        return getDiscord();
    }

    @Override
    public UsersRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public UsersRecord value2(String value) {
        setUuid(value);
        return this;
    }

    @Override
    public UsersRecord value3(String value) {
        setIp(value);
        return this;
    }

    @Override
    public UsersRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public UsersRecord value5(String value) {
        setLocale(value);
        return this;
    }

    @Override
    public UsersRecord value6(String value) {
        setTranslator(value);
        return this;
    }

    @Override
    public UsersRecord value7(Boolean value) {
        setAdmin(value);
        return this;
    }

    @Override
    public UsersRecord value8(Boolean value) {
        setJsallowed(value);
        return this;
    }

    @Override
    public UsersRecord value9(Integer value) {
        setDonated(value);
        return this;
    }

    @Override
    public UsersRecord value10(Boolean value) {
        setPopup(value);
        return this;
    }

    @Override
    public UsersRecord value11(Boolean value) {
        setDiscord(value);
        return this;
    }

    @Override
    public UsersRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, Boolean value7, Boolean value8, Integer value9, Boolean value10, Boolean value11) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.users);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(Integer id, String uuid, String ip, String name, String locale, String translator, Boolean admin, Boolean jsallowed, Integer donated, Boolean popup, Boolean discord) {
        super(Users.users);

        setId(id);
        setUuid(uuid);
        setIp(ip);
        setName(name);
        setLocale(locale);
        setTranslator(translator);
        setAdmin(admin);
        setJsallowed(jsallowed);
        setDonated(donated);
        setPopup(popup);
        setDiscord(discord);
    }
}
