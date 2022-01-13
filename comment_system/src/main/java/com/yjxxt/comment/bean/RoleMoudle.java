package com.yjxxt.comment.bean;

public class RoleMoudle {
    private Integer id;

    private Integer roleId;

    private Integer moudleId;

    public RoleMoudle() {
    }
    public RoleMoudle(Integer roleId, Integer moudleId) {
        this.roleId = roleId;
        this.moudleId = moudleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMoudleId() {
        return moudleId;
    }

    public void setMoudleId(Integer moudleId) {
        this.moudleId = moudleId;
    }
}