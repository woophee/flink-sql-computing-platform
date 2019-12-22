package com.woophee.platform.server.master.dao.model;

import java.io.Serializable;

public class DefineScheduledJob implements Serializable {
    private Integer id;

    private String name;

    private Integer groupId;

    private String sqlText;

    private Integer parallelism;

    private String scheduledRule;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public DefineScheduledJob withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DefineScheduledJob withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public DefineScheduledJob withGroupId(Integer groupId) {
        this.setGroupId(groupId);
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getSqlText() {
        return sqlText;
    }

    public DefineScheduledJob withSqlText(String sqlText) {
        this.setSqlText(sqlText);
        return this;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText == null ? null : sqlText.trim();
    }

    public Integer getParallelism() {
        return parallelism;
    }

    public DefineScheduledJob withParallelism(Integer parallelism) {
        this.setParallelism(parallelism);
        return this;
    }

    public void setParallelism(Integer parallelism) {
        this.parallelism = parallelism;
    }

    public String getScheduledRule() {
        return scheduledRule;
    }

    public DefineScheduledJob withScheduledRule(String scheduledRule) {
        this.setScheduledRule(scheduledRule);
        return this;
    }

    public void setScheduledRule(String scheduledRule) {
        this.scheduledRule = scheduledRule == null ? null : scheduledRule.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", groupId=").append(groupId);
        sb.append(", sqlText=").append(sqlText);
        sb.append(", parallelism=").append(parallelism);
        sb.append(", scheduledRule=").append(scheduledRule);
        sb.append("]");
        return sb.toString();
    }
}