package com.woophee.platform.server.common.model;

public class FlinkJobResult {
    private Integer id;
    private String name;
    private Integer parallelism;
    private String flinkId;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParallelism() {
        return parallelism;
    }

    public void setParallelism(Integer parallelism) {
        this.parallelism = parallelism;
    }

    public String getFlinkId() {
        return flinkId;
    }

    public void setFlinkId(String flinkId) {
        this.flinkId = flinkId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
