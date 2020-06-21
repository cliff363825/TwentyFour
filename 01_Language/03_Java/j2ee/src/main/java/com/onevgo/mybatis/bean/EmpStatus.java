package com.onevgo.mybatis.bean;

/**
 * 希望数据库保存的是100，200这些状态码，而不是默认0，1或者枚举的名
 */
public enum EmpStatus {
    LOGIN(100, "用户登录"),
    LOGOUT(200, "用户登出"),
    REMOVE(300, "用户不存在");

    private final Integer code;
    private final String msg;

    EmpStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EmpStatus getEmpStatusByCode(Integer code) {
        for (EmpStatus empStatus : EmpStatus.values()) {
            if (empStatus.getCode().equals(code)) {
                return empStatus;
            }
        }
        return null;
    }
}
