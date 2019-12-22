package com.woophee.platform.server.master.controller;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.FlinkCluster;
import com.woophee.platform.server.common.model.page.PageList;
import com.woophee.platform.server.master.service.FlinkClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/flink-cluster/v1")
public class FlinkClusterController {

    @Autowired
    private FlinkClusterService flinkClusterService;

    @RequestMapping(value = "/cluster", method = RequestMethod.POST)
    public Response create(@RequestBody FlinkCluster flinkCluster) {
        return flinkClusterService.create(flinkCluster);
    }

    @RequestMapping(value = "/cluster/", method = RequestMethod.PUT)
    public Response update(                           @RequestBody FlinkCluster flinkCluster) {
        return flinkClusterService.update(flinkCluster);
    }

    @RequestMapping(value = "/cluster/{id}", method = RequestMethod.GET)
    public RestResult<FlinkCluster> retrieve(@PathVariable("id") Integer id) {
        return flinkClusterService.retrieve(id);
    }

    @RequestMapping(value = "/cluster", method = RequestMethod.DELETE)
    public Response delete(@RequestParam Integer id) {
        return flinkClusterService.delete(id);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public PageList<FlinkCluster> query(@RequestParam Integer pageIndex,
                                        @RequestParam Integer pageSize) {
        return flinkClusterService.query(pageIndex, pageSize);
    }
}
