package com.woophee.platform.server.master.dao.model;

import com.woophee.platform.server.common.model.JobType;

import java.io.Serializable;

public class DefineJob implements Serializable {
    private Integer id;

    private JobType type;

    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public DefineJob withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobType getType() {
        return type;
    }

    public DefineJob withType(JobType type) {
        this.setType(type);
        return this;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public DefineJob withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}