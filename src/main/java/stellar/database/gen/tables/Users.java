/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function13;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row13;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import stellar.database.gen.Indexes;
import stellar.database.gen.Keys;
import stellar.database.gen.Mindustry;
import stellar.database.gen.tables.records.UsersRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Users extends TableImpl<UsersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>mindustry.users</code>
     */
    public static final Users USERS = new Users();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UsersRecord> getRecordType() {
        return UsersRecord.class;
    }

    /**
     * The column <code>mindustry.users.id</code>.
     */
    public final TableField<UsersRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>mindustry.users.uuid</code>.
     */
    public final TableField<UsersRecord, String> UUID = createField(DSL.name("uuid"), SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>mindustry.users.ip</code>.
     */
    public final TableField<UsersRecord, String> IP = createField(DSL.name("ip"), SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>mindustry.users.name</code>.
     */
    public final TableField<UsersRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>mindustry.users.locale</code>.
     */
    public final TableField<UsersRecord, String> LOCALE = createField(DSL.name("locale"), SQLDataType.VARCHAR(30).nullable(false).defaultValue(DSL.inline("undefined", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>mindustry.users.translator</code>.
     */
    public final TableField<UsersRecord, String> TRANSLATOR = createField(DSL.name("translator"), SQLDataType.VARCHAR(20).nullable(false).defaultValue(DSL.inline("double", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>mindustry.users.admin</code>.
     */
    public final TableField<UsersRecord, Byte> ADMIN = createField(DSL.name("admin"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>mindustry.users.jsallowed</code>.
     */
    public final TableField<UsersRecord, Byte> JSALLOWED = createField(DSL.name("jsallowed"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>mindustry.users.donated</code>.
     */
    public final TableField<UsersRecord, Integer> DONATED = createField(DSL.name("donated"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.users.banned</code>.
     */
    public final TableField<UsersRecord, Byte> BANNED = createField(DSL.name("banned"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>mindustry.users.exp</code>.
     */
    public final TableField<UsersRecord, Integer> EXP = createField(DSL.name("exp"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.users.popup</code>.
     */
    public final TableField<UsersRecord, Byte> POPUP = createField(DSL.name("popup"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("1", SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>mindustry.users.discord</code>.
     */
    public final TableField<UsersRecord, Byte> DISCORD = createField(DSL.name("discord"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("1", SQLDataType.TINYINT)), this, "");

    private Users(Name alias, Table<UsersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Users(Name alias, Table<UsersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>mindustry.users</code> table reference
     */
    public Users(String alias) {
        this(DSL.name(alias), USERS);
    }

    /**
     * Create an aliased <code>mindustry.users</code> table reference
     */
    public Users(Name alias) {
        this(alias, USERS);
    }

    /**
     * Create a <code>mindustry.users</code> table reference
     */
    public Users() {
        this(DSL.name("users"), null);
    }

    public <O extends Record> Users(Table<O> child, ForeignKey<O, UsersRecord> key) {
        super(child, key, USERS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Mindustry.MINDUSTRY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.USERS_USERS_IP_INDEX);
    }

    @Override
    public Identity<UsersRecord, Integer> getIdentity() {
        return (Identity<UsersRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<UsersRecord> getPrimaryKey() {
        return Keys.KEY_USERS_PRIMARY;
    }

    @Override
    public List<UniqueKey<UsersRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_USERS_SECONDARY);
    }

    @Override
    public Users as(String alias) {
        return new Users(DSL.name(alias), this);
    }

    @Override
    public Users as(Name alias) {
        return new Users(alias, this);
    }

    @Override
    public Users as(Table<?> alias) {
        return new Users(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(String name) {
        return new Users(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(Name name) {
        return new Users(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(Table<?> name) {
        return new Users(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row13<Integer, String, String, String, String, String, Byte, Byte, Integer, Byte, Integer, Byte, Byte> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function13<? super Integer, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Byte, ? super Byte, ? super Integer, ? super Byte, ? super Integer, ? super Byte, ? super Byte, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function13<? super Integer, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Byte, ? super Byte, ? super Integer, ? super Byte, ? super Integer, ? super Byte, ? super Byte, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
