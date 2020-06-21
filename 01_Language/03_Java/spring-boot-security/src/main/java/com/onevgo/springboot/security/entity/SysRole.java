package com.onevgo.springboot.security.entity;

import java.util.List;

public class SysRole {
    private String role;
    private String name;

    private List<SysPermission> permissionList;

    public SysRole() {
    }

    public SysRole(String role, String name) {
        this.role = role;
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
