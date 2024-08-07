/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen;


import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import stellar.database.gen.tables.IpCached;
import stellar.database.gen.tables.Users;


/**
 * A class modelling indexes of tables in mindustry.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index idx_18438Secondary = Internal.createIndex(DSL.name("idx_18438_secondary"), IpCached.ipCached, new OrderField[] { IpCached.ipCached.ip }, true);
    public static final Index idx_18494Secondary = Internal.createIndex(DSL.name("idx_18494_secondary"), Users.users, new OrderField[] { Users.users.id }, true);
    public static final Index idx_18494UsersIpIndex = Internal.createIndex(DSL.name("idx_18494_users_ip_index"), Users.users, new OrderField[] { Users.users.ip }, false);
}
