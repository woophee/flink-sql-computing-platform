package com.woophee.platform.server.master.controller;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.DefineRealtimeJob;
import com.woophee.platform.server.common.model.DefineRealtimeJobVO;
import com.woophee.platform.server.common.model.page.PageList;
import com.woophee.platform.server.master.service.DefineRealtimeJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/define-realtime-job/v1")
public class DefineRealtimeJobController {

    @Autowired
    private DefineRealtimeJobService defineRealtimeJobService;

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public Response create(@RequestBody DefineRealtimeJob defineRealtimeJob) {
        return defineRealtimeJobService.create(defineRealtimeJob);
    }

    @RequestMapping(value = "/job", method = RequestMethod.PUT)
    public Response update(@RequestBody DefineRealtimeJob defineRealtimeJob) {
        return defineRealtimeJobService.update(defineRealtimeJob);
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") Integer id) {
        return defineRealtimeJobService.delete(id);
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    public RestResult<DefineRealtimeJob> retrieve(@PathVariable("id") Integer id) {
        return defineRealtimeJobService.retrieve(id);
    }

    //分页查询当前job group下的所有job
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public PageList<DefineRealtimeJob> query(@RequestParam(required = false) Integer groupId,
                                             @RequestParam Integer pageIndex,
                                             @RequestParam Integer pageSize) {
        return defineRealtimeJobService.query(groupId, pageIndex, pageSize);
    }

    //分页查询当前job group下的所有job
    @RequestMapping(value = "/web-query", method = RequestMethod.GET)
    public PageList<DefineRealtimeJobVO> webQuery(@RequestParam(required = false) Integer groupId,
                                               @RequestParam Integer pageIndex,
                                               @RequestParam Integer pageSize) {
        return defineRealtimeJobService.webQuery(groupId, pageIndex, pageSize);
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.POST)
    public Response submit(@PathVariable("id") Integer id) {
        return defineRealtimeJobService.submit(id);
    }
}
