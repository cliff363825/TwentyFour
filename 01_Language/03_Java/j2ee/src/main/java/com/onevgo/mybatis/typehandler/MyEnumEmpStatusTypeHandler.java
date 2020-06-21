package com.onevgo.mybatis.typehandler;

import com.onevgo.mybatis.bean.EmpStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * 1. 实现 TypeHandler 接口，或者继承 BaseTypeHandler
 */
public class MyEnumEmpStatusTypeHandler implements TypeHandler<EmpStatus> {
    /**
     * 定义当前数据如何保存到数据库中
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, EmpStatus parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, Types.NULL);
        } else {
            ps.setString(i, parameter.getCode().toString());
        }
    }

    @Override
    public EmpStatus getResult(ResultSet rs, String columnName) throws SQLException {
        // 需要根据从数据库中拿到的枚举的状态码返回一个枚举对象
        int code = rs.getInt(columnName);
        System.out.println("MyEnumEmpStatusTypeHandler.getResult.1: " + code);
        EmpStatus empStatus = EmpStatus.getEmpStatusByCode(code);
        return empStatus;
    }

    @Override
    public EmpStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        // 需要根据从数据库中拿到的枚举的状态码返回一个枚举对象
        int code = rs.getInt(columnIndex);
        System.out.println("MyEnumEmpStatusTypeHandler.getResult.2: " + code);
        EmpStatus empStatus = EmpStatus.getEmpStatusByCode(code);
        return empStatus;
    }

    @Override
    public EmpStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 需要根据从数据库中拿到的枚举的状态码返回一个枚举对象
        int code = cs.getInt(columnIndex);
        System.out.println("MyEnumEmpStatusTypeHandler.getResult.3: " + code);
        System.out.println("从数据库中获取的状态码：" + code);
        EmpStatus empStatus = EmpStatus.getEmpStatusByCode(code);
        return empStatus;
    }
}
