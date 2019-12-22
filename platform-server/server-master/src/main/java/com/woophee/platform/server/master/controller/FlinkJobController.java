package com.woophee.platform.server.master.controller;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.common.model.flink.FlinkSubmitRequest;
import com.woophee.platform.server.common.model.flink.FlinkSubmitResult;
import com.woophee.platform.server.master.service.FlinkJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/flink-job/v1")
public class FlinkJobController {

    @Autowired
    private FlinkJobService flinkJobService;

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.POST)
    public RestResult<FlinkSubmitResult> submit(@PathVariable("id") Integer clusterId,
                                                @RequestBody FlinkSubmitRequest flinkSubmitRequest) {
        return flinkJobService.submitJob(clusterId, flinkSubmitRequest);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public Response cancel(@RequestParam Integer clusterId, @RequestParam Integer jobId) {
        return flinkJobService.cancelJob(clusterId, jobId);
    }

}
