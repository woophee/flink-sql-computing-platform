package com.woophee.platform.server.master.controller;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.DefineGroup;
import com.woophee.platform.server.common.model.page.PageList;
import com.woophee.platform.server.master.service.DefineGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/define-group/v1")
public class DefineGroupController {

    @Autowired
    private DefineGroupService defineGroupService;

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public Response create(@RequestBody DefineGroup defineGroup) {
        return defineGroupService.create(defineGroup);
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public Response update(@RequestBody DefineGroup defineGroup) {
        return defineGroupService.update(defineGroup);
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") Integer id) {
        return defineGroupService.delete(id);
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public RestResult<DefineGroup> retrieve(@PathVariable("id") Integer id) {
        return defineGroupService.retrieve(id);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public PageList<DefineGroup> query(@RequestParam(required = false) Integer pageIndex,
                                       @RequestParam(required = false) Integer pageSize) {

        return defineGroupService.query(pageIndex, pageSize);
    }


}
