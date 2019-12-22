package com.woophee.platform.server.common.model;

public class FlinkJobRequest {
    private Integer id;
    private Integer groupId;
    private String name;
    private Integer paramellism;
    private String sql;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParamellism() {
        return paramellism;
    }

    public void setParamellism(Integer paramellism) {
        this.paramellism = paramellism;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
