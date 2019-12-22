package com.woophee.platform.server.master.dao.model;

import com.woophee.platform.server.common.model.JobType;

import java.io.Serializable;

public class DefineGroup implements Serializable {
    private Integer id;

    private String name;

    private JobType type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public DefineGroup withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DefineGroup withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public JobType getType() {
        return type;
    }

    public DefineGroup withType(JobType type) {
        this.setType(type);
        return this;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }
}