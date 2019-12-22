package com.woophee.platform.server.master.service;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.common.model.flink.FlinkSubmitRequest;
import com.woophee.platform.server.common.model.flink.FlinkSubmitResult;

public interface FlinkJobService {
    RestResult<FlinkSubmitResult> submitJob(Integer clusterId, FlinkSubmitRequest flinkSubmitRequest);

    Response cancelJob(Integer clusterId, Integer jobId);

}
