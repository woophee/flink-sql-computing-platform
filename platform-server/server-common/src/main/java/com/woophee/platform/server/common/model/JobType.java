package com.woophee.platform.server.common.model;

public enum JobType {
    REALTIME("实时任务"),
    SCHEDULED("定时任务");

    private String info;
    JobType(String info) {
        this.info = info;
    }
}
