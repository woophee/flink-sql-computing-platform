package com.woophee.platform.server.master.controller;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.DefineScheduledJob;
import com.woophee.platform.server.common.model.page.PageList;
import com.woophee.platform.server.master.service.DefineScheduledJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/define-scheduled-job/v1")
public class DefineScheduledJobController {
    @Autowired
    private DefineScheduledJobService defineScheduledJobService;

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public Response create(@RequestBody DefineScheduledJob defineScheduledJob) {
        return defineScheduledJobService.create(defineScheduledJob);
    }

    @RequestMapping(value = "/job", method = RequestMethod.PUT)
    public Response update(@RequestBody DefineScheduledJob defineScheduledJob) {
        return defineScheduledJobService.update(defineScheduledJob);
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") Integer id) {
        return defineScheduledJobService.delete(id);
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    public RestResult<DefineScheduledJob> retrieve(@PathVariable("id") Integer id) {
        return defineScheduledJobService.retrieve(id);
    }

    //分页查询当前job group下的所有job
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public PageList<DefineScheduledJob> query(@RequestParam Integer groupId,
                                             @RequestParam Integer pageIndex,
                                             @RequestParam Integer pageSize) {
        return defineScheduledJobService.query(groupId, pageIndex, pageSize);
    }

}
