/*
 * This file is generated by jOOQ.
*/
package com.onevgo.jooq.tables.records;


import com.onevgo.jooq.tables.TblEmployee;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;


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
public class TblEmployeeRecord extends UpdatableRecordImpl<TblEmployeeRecord> implements Record6<Integer, String, String, String, Integer, String> {

    private static final long serialVersionUID = 1047936539;

    /**
     * Setter for <code>default.tbl_employee.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>default.tbl_employee.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>default.tbl_employee.last_name</code>.
     */
    public void setLastName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>default.tbl_employee.last_name</code>.
     */
    public String getLastName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>default.tbl_employee.gender</code>.
     */
    public void setGender(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>default.tbl_employee.gender</code>.
     */
    public String getGender() {
        return (String) get(2);
    }

    /**
     * Setter for <code>default.tbl_employee.email</code>.
     */
    public void setEmail(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>default.tbl_employee.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>default.tbl_employee.d_id</code>.
     */
    public void setDId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>default.tbl_employee.d_id</code>.
     */
    public Integer getDId() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>default.tbl_employee.emp_status</code>.
     */
    public void setEmpStatus(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>default.tbl_employee.emp_status</code>.
     */
    public String getEmpStatus() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, Integer, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, Integer, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TblEmployee.TBL_EMPLOYEE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TblEmployee.TBL_EMPLOYEE.LAST_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TblEmployee.TBL_EMPLOYEE.GENDER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TblEmployee.TBL_EMPLOYEE.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return TblEmployee.TBL_EMPLOYEE.D_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TblEmployee.TBL_EMPLOYEE.EMP_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getLastName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getGender();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getDId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getEmpStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEmployeeRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEmployeeRecord value2(String value) {
        setLastName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEmployeeRecord value3(String value) {
        setGender(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEmployeeRecord value4(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEmployeeRecord value5(Integer value) {
        setDId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEmployeeRecord value6(String value) {
        setEmpStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEmployeeRecord values(Integer value1, String value2, String value3, String value4, Integer value5, String value6) {
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
     * Create a detached TblEmployeeRecord
     */
    public TblEmployeeRecord() {
        super(TblEmployee.TBL_EMPLOYEE);
    }

    /**
     * Create a detached, initialised TblEmployeeRecord
     */
    public TblEmployeeRecord(Integer id, String lastName, String gender, String email, Integer dId, String empStatus) {
        super(TblEmployee.TBL_EMPLOYEE);

        set(0, id);
        set(1, lastName);
        set(2, gender);
        set(3, email);
        set(4, dId);
        set(5, empStatus);
    }
}