/*
 * This file is generated by jOOQ.
*/
package com.onevgo.jooq.tables;


import com.onevgo.jooq.Default;
import com.onevgo.jooq.Keys;
import com.onevgo.jooq.tables.records.TblDeptRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TblDept extends TableImpl<TblDeptRecord> {

    private static final long serialVersionUID = -667305547;

    /**
     * The reference instance of <code>default.tbl_dept</code>
     */
    public static final TblDept TBL_DEPT = new TblDept();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TblDeptRecord> getRecordType() {
        return TblDeptRecord.class;
    }

    /**
     * The column <code>default.tbl_dept.id</code>.
     */
    public final TableField<TblDeptRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>default.tbl_dept.dept_name</code>. 部门名称
     */
    public final TableField<TblDeptRecord, String> DEPT_NAME = createField("dept_name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "部门名称");

    /**
     * Create a <code>default.tbl_dept</code> table reference
     */
    public TblDept() {
        this("tbl_dept", null);
    }

    /**
     * Create an aliased <code>default.tbl_dept</code> table reference
     */
    public TblDept(String alias) {
        this(alias, TBL_DEPT);
    }

    private TblDept(String alias, Table<TblDeptRecord> aliased) {
        this(alias, aliased, null);
    }

    private TblDept(String alias, Table<TblDeptRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Default.DEFAULT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TblDeptRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TBL_DEPT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TblDeptRecord> getPrimaryKey() {
        return Keys.KEY_TBL_DEPT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TblDeptRecord>> getKeys() {
        return Arrays.<UniqueKey<TblDeptRecord>>asList(Keys.KEY_TBL_DEPT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblDept as(String alias) {
        return new TblDept(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TblDept rename(String name) {
        return new TblDept(name, null);
    }
}