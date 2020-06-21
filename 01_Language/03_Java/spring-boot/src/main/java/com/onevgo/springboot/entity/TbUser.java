package com.onevgo.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_user", schema = "yii2")
public class TbUser {
    private int id;
    private String username;
    private String authKey;
    private String passwordHash;
    private String passwordResetToken;
    private String email;
    private int groupId;
    private short status;
    private int createdAt;
    private int updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "auth_key", nullable = false, length = 32)
    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    @Basic
    @Column(name = "password_hash", nullable = false, length = 255)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "password_reset_token", nullable = true, length = 255)
    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = false)
    public int getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUser tbUser = (TbUser) o;

        if (id != tbUser.id) return false;
        if (groupId != tbUser.groupId) return false;
        if (status != tbUser.status) return false;
        if (createdAt != tbUser.createdAt) return false;
        if (updatedAt != tbUser.updatedAt) return false;
        if (username != null ? !username.equals(tbUser.username) : tbUser.username != null) return false;
        if (authKey != null ? !authKey.equals(tbUser.authKey) : tbUser.authKey != null) return false;
        if (passwordHash != null ? !passwordHash.equals(tbUser.passwordHash) : tbUser.passwordHash != null)
            return false;
        if (passwordResetToken != null ? !passwordResetToken.equals(tbUser.passwordResetToken) : tbUser.passwordResetToken != null)
            return false;
        if (email != null ? !email.equals(tbUser.email) : tbUser.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (authKey != null ? authKey.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (passwordResetToken != null ? passwordResetToken.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + groupId;
        result = 31 * result + (int) status;
        result = 31 * result + createdAt;
        result = 31 * result + updatedAt;
        return result;
    }
}
