package com.woophee.flinksql.service.cli;

public class CliOptions {
    private final Integer jobId;

    public CliOptions(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobId() {
        return jobId;
    }
}
